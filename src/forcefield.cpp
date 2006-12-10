/**********************************************************************
forcefield.cpp - Handle OBForceField class.
 
Copyright (C) 2006 by Tim Vandermeersch <tim.vandermeersch@gmail.com>

This file is part of the Open Babel project.
For more information, see <http://openbabel.sourceforge.net/>
 
This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation version 2 of the License.
 
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
***********************************************************************/
#include <openbabel/babelconfig.h>

#include <openbabel/forcefield.h>

#include <openbabel/mol.h>
#include <openbabel/atom.h>
#include <openbabel/bond.h>
#include <openbabel/obiter.h>

using namespace std;

namespace OpenBabel
{

  //
  // OBForceField member functions
  //

  OBForceField::OBForceField()
  {
  }

  OBForceField::~OBForceField()
  {
  }

  OBForceField &OBForceField::operator=(OBForceField &src)
  {
    _ffbondparams = src._ffbondparams;
    _ffangleparams = src._ffangleparams;
    _ffstretchbendparams = src._ffstretchbendparams;
    _fftorsionparams = src._fftorsionparams;
    _mol = src._mol;
    return(*this);
  }

  int OBForceField::GetParameterIdx(int a, int b, int c, int d, 
                                    std::vector<OBFFParameter> &parameter)
  {
    if (!b)
      for (int idx=0; idx < parameter.size(); idx++)
        if (a == parameter[idx].a)
          return idx;

    if (!c)
      for (int idx=0; idx < parameter.size(); idx++)
        if (((a == parameter[idx].a) && (b == parameter[idx].b)) || ((a == parameter[idx].b) && (b == parameter[idx].a)))
          return idx;

    if (!d)
      for (int idx=0; idx < parameter.size(); idx++)
        if (((a == parameter[idx].a) && (b == parameter[idx].b) && (c == parameter[idx].c)) || 
            ((a == parameter[idx].c) && (b == parameter[idx].b) && (c == parameter[idx].a)))
          return idx;
 
    for (int idx=0; idx < parameter.size(); idx++)
      if (((a == parameter[idx].a) && (b == parameter[idx].b) && (c == parameter[idx].c) && (d == parameter[idx].d)) || 
          ((a == parameter[idx].d) && (b == parameter[idx].c) && (c == parameter[idx].b) && (d == parameter[idx].a)))
        return idx;

    return -1;
  }

  double OBForceField::E_Bond_Harmonic(double bondunit, double bond_cubic, double bond_quartic)
  {
    OBAtom *a, *b;
    double e, energy, l, l_ref, force, delta, delta2;
    int idx;
    
    //char errbuf[3600]; // DEBUG
    //sprintf(errbuf, "bondunit=%f  bond_cubic=%f  bond_quartic=%f\n", bondunit, bond_cubic, bond_quartic); // DEBUG
 
    energy = 0.0f;

    FOR_BONDS_OF_MOL(bond, _mol) {
      a = bond->GetBeginAtom();
      b = bond->GetEndAtom();	
      l = bond->GetLength();
      
      idx = GetParameterIdx(atoi(a->GetType()), atoi(b->GetType()), 0, 0, _ffbondparams);
      if (idx < 0) {
        obErrorLog.ThrowError(__FUNCTION__, "Could not find all bond parameters", obError);
        exit(1);
      }
        
      l_ref = _ffbondparams[idx].dpar1;
      force = _ffbondparams[idx].dpar2;

      delta = l - l_ref;
      delta2 = delta * delta;
      e = bondunit * force * delta2 * (1.0f + bond_cubic * delta + bond_quartic * delta2);
      energy += e;
      //sprintf(errbuf, "%sforce=%f  l_ref=%f  l=%f  delta=%f  E=%f\n", errbuf, force, l_ref, l, delta, e); // DEBUG
    }
    //sprintf(errbuf, "%sBond stretching: E=%f\n", errbuf, energy); // DEBUG
    //obErrorLog.ThrowError(__FUNCTION__, errbuf, obError); // DEBUG

    return energy;
  }
  
  double OBForceField::E_Angle_Harmonic(double angleunit, double angle_cubic, double angle_quatric, double angle_pentic, double angle_sextic)
  {
    OBAtom *a, *b, *c;
    double e, energy, force, ang, ang_ref, delta, delta2, delta3, delta4;
    int idx;
    
    //char errbuf[10000]; // DEBUG
    //sprintf(errbuf, "angleunit=%f  angle_cubic=%f  angle_quatric=%f  angle_pentic=%f angle_sextic=%f\n", angleunit, angle_cubic, angle_quatric, angle_pentic, angle_sextic); // DEBUG
 
    energy = 0.0f;

    FOR_ANGLES_OF_MOL(angle, _mol) {
      b = _mol.GetAtom((*angle)[0] + 1);
      a = _mol.GetAtom((*angle)[1] + 1);
      c = _mol.GetAtom((*angle)[2] + 1);
      ang = a->GetAngle(b->GetIdx(), c->GetIdx());

      idx = GetParameterIdx(atoi(a->GetType()), atoi(b->GetType()), atoi(c->GetType()), 0, _ffangleparams);
      if (idx < 0) {
        obErrorLog.ThrowError(__FUNCTION__, "Could not find all angle parameters", obError);
        exit(1);
      }
      
      ang_ref = _ffangleparams[idx].dpar1;
      force   = _ffangleparams[idx].dpar2;
	
      delta = ang - ang_ref;
      delta2 = delta * delta;
      delta3 = delta2 * delta;
      delta4 = delta2 * delta2;
      e = angleunit * force * delta2 * (1.0f + angle_cubic * delta + angle_quatric * delta2 + angle_pentic * delta3 + angle_sextic * delta4);
      energy += e;
      //sprintf(errbuf, "%sforce=%f  ang_ref=%f  ang=%f  delta=%f  E=%f   (%d-%d-%d)\n", errbuf, force, ang_ref, ang, delta, e, a->GetIdx(), b->GetIdx(), c->GetIdx()); // DEBUG
    }
    //sprintf(errbuf, "%sAngle bending: E=%f\n", errbuf, energy); // DEBUG
    //obErrorLog.ThrowError(__FUNCTION__, errbuf, obError); // DEBUG
	
    return energy;
  }
 
  double OBForceField::E_StretchBend(double stretchbendunit) 
  {
    OBAtom *a, *b, *c;
    OBBond *b1, *b2;
    double e, energy, force, ang, ang_ref, l1, l2, l_ref1, l_ref2, delta_a, delta_b1, delta_b2;
    int idx;

    //char errbuf[100000]; // DEBUG
    //sprintf(errbuf, "stretchbendunit=%f\n", stretchbendunit); // DEBUG
 
    energy = 0.0f;

    FOR_ANGLES_OF_MOL(angle, _mol) {
      b = _mol.GetAtom((*angle)[0] + 1);
      a = _mol.GetAtom((*angle)[1] + 1);
      c = _mol.GetAtom((*angle)[2] + 1);
      ang = a->GetAngle(b->GetIdx(), c->GetIdx());
      b1 = _mol.GetBond(b->GetIdx(), a->GetIdx());
      b2 = _mol.GetBond(b->GetIdx(), c->GetIdx());
      l1 = b1->GetLength();
      l2 = b2->GetLength();

      idx = GetParameterIdx(atoi(b->GetType()), 0, 0, 0, _ffstretchbendparams);
      if (idx < 0) {
        obErrorLog.ThrowError(__FUNCTION__, "Could not find all stretch-bend parameters", obError);
        exit(1);
      }
      force = _ffstretchbendparams[idx].dpar1;
      
      idx = GetParameterIdx(atoi(a->GetType()), atoi(b->GetType()), atoi(c->GetType()), 0, _ffangleparams);
      ang_ref = _ffangleparams[idx].dpar1;
      
      idx = GetParameterIdx(atoi(b->GetType()), atoi(a->GetType()), 0, 0, _ffbondparams);
      l_ref1 = _ffbondparams[idx].dpar1;
 
      idx = GetParameterIdx(atoi(b->GetType()), atoi(c->GetType()), 0, 0, _ffbondparams);
      l_ref2 = _ffbondparams[idx].dpar1;
 
      delta_a = ang - ang_ref;
      delta_b1 = l1 - l_ref1;
      delta_b2 = l2 - l_ref2;
      e = stretchbendunit * force * delta_a * (delta_b1 + delta_b2);
      energy += e;
      //sprintf(errbuf, "%sforce=%f  ang_ref=%f  ang=%f  l_ref1=%f  l1=%f  l_ref2=%f  l2=%f  E=%f\n", errbuf, force, ang_ref, ang, l_ref1, l1, l_ref2, l2, e); // DEBUG
    }
    //sprintf(errbuf, "%sBend-stretch term: E=%f\n", errbuf, energy); // DEBUG
    //obErrorLog.ThrowError(__FUNCTION__, errbuf, obError); // DEBUG
	
    return energy;
  }
 
  double OBForceField::E_Torsion(double torsionunit) 
  {
    OBAtom *a, *b, *c, *d;
    std::vector<quad<OBAtom*, OBAtom*, OBAtom*, OBAtom*> > atoms;
    double e, energy, tor, v1, v2, v3, cosine, cosine2, cosine3, sine, sine2, sine3, phi1, phi2, phi3;
    int idx;

    //char errbuf[30000]; // DEBUG
    //sprintf(errbuf, "torsionunit=%f\n", torsionunit); // DEBUG
 
    energy = 0.0f;

    FOR_TORSIONS_OF_MOL(t, _mol) {
      a = _mol.GetAtom((*t)[0] + 1);
      b = _mol.GetAtom((*t)[1] + 1);
      c = _mol.GetAtom((*t)[2] + 1);
      d = _mol.GetAtom((*t)[3] + 1);
      tor = _mol.GetTorsion(a->GetIdx(), b->GetIdx(), c->GetIdx(), d->GetIdx());
 
      idx = GetParameterIdx(atoi(a->GetType()), atoi(b->GetType()), atoi(c->GetType()), atoi(d->GetType()), _fftorsionparams);
      if (idx < 0) {
        obErrorLog.ThrowError(__FUNCTION__, "Could not find all torsion parameters", obError);
        exit(1);
      }
      
      v1 = _fftorsionparams[idx].dpar1;
      v2 = _fftorsionparams[idx].dpar2;
      v3 = _fftorsionparams[idx].dpar3;
      
      cosine = cos(DEG_TO_RAD * tor);
      cosine2 = cos(DEG_TO_RAD * 2 * tor);
      cosine3 = cos(DEG_TO_RAD * 3 * tor);
      phi1 = 1.0f + cosine;
      phi2 = 1.0f - cosine2;
      phi3 = 1.0f + cosine3;
      e = torsionunit * (v1 * phi1 + v2 * phi2 + v3 * phi3);
      energy += e;
      //sprintf(errbuf, "%sv1=%f  v2=%f  v3=%f  tor=%f  cosine=%f  cosine2=%f  E=%f\n", errbuf, v1, v2, v3, tor, cosine, cosine2, e); // DEBUG
    }
    //sprintf(errbuf, "%sTorsions: E=%f\n", errbuf, energy); // DEBUG
    //obErrorLog.ThrowError(__FUNCTION__, errbuf, obError); // DEBUG

    return energy;
  }

  //
  //  a
  //   \
  //    b---d      plane = a-b-c
  //   /
  //  c
  //
  double OBForceField::E_OutPlaneBend(double outplanebendunit) 
  {
    OBAtom *a, *b, *c, *d;
    double e, energy, force, angle, angle2;
    //char errbuf[10000];
    //sprintf(errbuf, "outplanebendunit=%f\n", outplanebendunit); // DEBUG

    FOR_ATOMS_OF_MOL(atom, _mol) {
      b = (OBAtom*) &*atom;

      for (int idx=0; idx < _ffoutplanebendparams.size(); idx++) {
        if (atoi(b->GetType()) == _ffoutplanebendparams[idx].a) {
          a = NULL;
          c = NULL;
          d = NULL;

          FOR_NBORS_OF_ATOM(nbr, b) {
            if (a ==NULL)
              a = (OBAtom*) &*nbr;
            else if (c == NULL)
              c = (OBAtom*) &*nbr;
            else
              d = (OBAtom*) &*nbr;
          }
	  
          if (atoi(d->GetType()) == _ffoutplanebendparams[idx].b) {
            force = _ffoutplanebendparams[idx].dpar1;
            angle = PointPlaneAngle(a->GetVector(), b->GetVector(), c->GetVector(), d->GetVector());
            angle2 = angle * angle;
            e = outplanebendunit * force * angle2;
            energy +=e;

            //sprintf(errbuf, "%sout-of-plane: a-b-c-d  %d-%d-%d-%d  angle=%f  E=%f\n", errbuf, a->GetIdx(), b->GetIdx(), c->GetIdx(), d->GetIdx(), angle, e); // DEBUG
          }
          if (atoi(a->GetType()) == _ffoutplanebendparams[idx].b) {
            // a <-> d
            force = _ffoutplanebendparams[idx].dpar1;
            angle = PointPlaneAngle(d->GetVector(), b->GetVector(), c->GetVector(), a->GetVector());
            angle2 = angle * angle;
            e = outplanebendunit * force * angle2;
            energy +=e;

            //sprintf(errbuf, "%sout-of-plane: a-b-c-d  %d-%d-%d-%d  angle=%f  E=%f\n", errbuf, d->GetIdx(), b->GetIdx(), c->GetIdx(), a->GetIdx(), angle, e); // DEBUG
          }
          if (atoi(c->GetType()) == _ffoutplanebendparams[idx].b) {
            // c <-> d
            force = _ffoutplanebendparams[idx].dpar1;
            angle = PointPlaneAngle(a->GetVector(), b->GetVector(), d->GetVector(), c->GetVector());
            angle2 = angle * angle;
            e = outplanebendunit * force * angle2;
            energy +=e;


            //sprintf(errbuf, "%sout-of-plane: a-b-c-d  %d-%d-%d-%d  angle=%f  E=%f\n", errbuf, a->GetIdx(), b->GetIdx(), d->GetIdx(), c->GetIdx(), angle, e); // DEBUG
          }
        }
      }
    }
    //sprintf(errbuf, "%sOut-of-plane bending: E=%f\n", errbuf, energy); // DEBUG
    //obErrorLog.ThrowError(__FUNCTION__, errbuf, obError); // DEBUG

    return energy;
  }
  
  double OBForceField::PointPlaneAngle(const vector3 &a, const vector3 &b, const vector3 &c, const vector3 &d)
  {
    vector3 ab, bc, bd, normal;
    double angle;

    ab = a - b;
    bc = b - c;
    bd = b - d;
 
    normal = cross(ab, bc);
    angle = 90.0f - vectorAngle(normal, bd);

    return angle;
  }

  double OBForceField::E_VDWBuckingham(double a_expterm, double b_expterm, double c_expterm)
  {
    OBAtom *a, *b;
    double e, energy, ra, rb, rab, rr, rrab, rrab2, rrab4, rrab6, abrr, eps, epsa, epsb;
    int idx;

    //char errbuf[100000]; // DEBUG
    //sprintf(errbuf, "a_expterm=%f  b_expterm=%f c_expter=%fm\n", a_expterm, b_expterm, c_expterm); // DEBUG
 
    energy = 0.0f;

    FOR_PAIRS_OF_MOL(p, _mol) {
      a = _mol.GetAtom(p->first);
      b = _mol.GetAtom(p->second);
      rab = a->GetDistance(b);

      idx = GetParameterIdx(atoi(a->GetType()), atoi(b->GetType()), 0, 0, _ffvdwprparams);
      if (idx >= 0) {
        rr = _ffvdwprparams[idx].dpar1;
        eps = _ffvdwprparams[idx].dpar2;
      } else {
        idx = GetParameterIdx(atoi(a->GetType()), 0, 0, 0, _ffvdwparams);
        ra = _ffvdwparams[idx].dpar1;
        epsa = _ffvdwparams[idx].dpar2;
        idx = GetParameterIdx(atoi(b->GetType()), 0, 0, 0, _ffvdwparams);
        rb = _ffvdwparams[idx].dpar1;
        epsb = _ffvdwparams[idx].dpar2;
	
        //sprintf(errbuf, "%s\n\n     *** v1=%f  v2=%f  v3=%f\n\n", errbuf, v1, v2, v3); // DEBUG
        rr = ra + rb;
        eps = (epsa + epsb) / 2; // is this correct??
      }

      rrab = rr / rab;
      rrab2 = rrab * rrab;
      rrab4 = rrab2 * rrab2;
      rrab6 = rrab4 * rrab2;
      abrr = rab / rr;
      e = eps * (a_expterm * exp(-b_expterm * abrr) - c_expterm * rrab6);
      energy += e;
      //sprintf(errbuf, "%sepsa=%f  epsb=%f epsilon=%f rab=%f  ra=%f  rb=%f  rr=%f  E=%f\n", errbuf, epsa, epsb, eps, rab, ra, rb, rr, e); // DEBUG
    }
    //sprintf(errbuf, "%sVan der Waals interactions: E=%f\n", errbuf, energy); // DEBUG
    //obErrorLog.ThrowError(__FUNCTION__, errbuf, obError); // DEBUG

    return energy;
  }

  double OBForceField::E_DipoleDipole(double dielectric)
  {
    OBAtom *a, *b, *c, *d;
    double e, energy, dipole, dipole2, r2, ri2, rk2, rirkr3, dotp, doti, dotk, fik;
    double debye, electric, f;
    vector3 va, vb, vc, vd, ab, cd, q, r;
    int idx;
    
    //char errbuf[3600]; // DEBUG
    //sprintf(errbuf, "dielectric=%f\n", dielectric); // DEBUG
	
    energy = 0.0f;
    debye = 4.8033324f;
    electric = 332.05382f;
    f = electric / (debye * debye * dielectric);

    FOR_BONDS_OF_MOL(bond, _mol) {
      a = bond->GetBeginAtom();
      b = bond->GetEndAtom();	
      
      idx = GetParameterIdx(atoi(a->GetType()), atoi(b->GetType()), 0, 0, _ffdipoleparams);
      if (idx > 0) {
        dipole = _ffdipoleparams[idx].dpar1;

        FOR_BONDS_OF_MOL(bond2, _mol) {
          if (&*bond == &*bond2)
            continue;
          if (bond2->GetIdx() < bond->GetIdx())
            continue;

          c = bond2->GetBeginAtom();
          d = bond2->GetEndAtom();	
          
          idx = GetParameterIdx(atoi(c->GetType()), atoi(d->GetType()), 0, 0, _ffdipoleparams);
          if (idx > 0) {
            dipole2 = _ffdipoleparams[idx].dpar1;

            //
            //    dipole1      dipole2
            //       i-----------j           i = center of bond 1
            //        \ a1    a2/            j = center of bond 2
            //         \       /             
            //          \     /              
            //           \a3 /
            //            \ /
            //             k
            //
            va = a->GetVector();
            vb = b->GetVector();
            vc = c->GetVector();
            vd = d->GetVector();
            
            ab = va - vb;
            cd = vc - vd;
            ri2 = dot(ab, ab);
            rk2 = dot(cd, cd);
            q = (va + vb) / 2;
            r = (vc + vd) / 2;
            r2 = dot(r, r);
            rirkr3 = sqrt(ri2 * rk2 * r2) * r2;
            dotp = dot(ab, cd);
            doti = dot(ab, r);
            dotk = dot(cd, r);
            fik = f * dipole * dipole2;
	    
            e = fik * (dotp - 3.0f * doti * dotk/r2) / rirkr3;
            energy += e;
            //sprintf(errbuf, "%sdipole=%f  dipole2=%f  r=%f cos_a1=%f  cos_a2=%f  cos_a3=%f  E=%f\n", errbuf, dipole, dipole2, r, cos_a1, cos_a2, cos_a3, e); // DEBUG
          }
        }
      }
    }
    //sprintf(errbuf, "%sDipole-dipole: E=%f\n", errbuf, energy); // DEBUG
    //obErrorLog.ThrowError(__FUNCTION__, errbuf, obError); // DEBUG

    return energy;
  }

  //
  // OBForceFieldMM2 member functions
  //

  OBForceFieldMM2::OBForceFieldMM2()
  {
    ParseParamFile();
  }

  OBForceFieldMM2::~OBForceFieldMM2()
  {
  }

  bool OBForceFieldMM2::Setup(OBMol &mol)
  {
    _mol = mol;
    SetMM2Types();
  }
 
  bool OBForceFieldMM2::ParseParamFile()
  {
    vector<string> vs;
    char buffer[80];
    char filename[80];
    int currently_parsing;
    
    OBFFParameter parameter;
    
    // open data/mm2.prm
    string buffer2, subbuffer;
    ifstream ifs1, ifs2, *ifsP;
    buffer2 = BABEL_DATADIR;
    buffer2 += FILE_SEP_CHAR;
    subbuffer = buffer2;
    subbuffer += BABEL_VERSION;
    subbuffer += FILE_SEP_CHAR;
    subbuffer += "mm2.prm";
    buffer2 += "mm2.prm";

    ifs1.open(subbuffer.c_str());
    ifsP= &ifs1;
    if (!(*ifsP))
      {
        ifs2.open(buffer2.c_str());
        ifsP = &ifs2;
      }
    
    while (ifsP->getline(buffer, 80)) {
	
      tokenize(vs, buffer);

      if (EQn(buffer, "bondunit", 8)) {
        bondunit = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "bond-cubic", 10)) {
        bond_cubic = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "bond-quartic", 12)) {
        bond_quartic = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "angleunit", 9)) {
        angleunit = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "angle-sextic", 12)) {
        angle_sextic = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "strbndunit", 10)) {
        stretchbendunit = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "torsionunit", 11)) {
        torsionunit = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "opbendunit", 10)) {
        outplanebendunit = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "a-expterm", 9)) {
        a_expterm = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "b-expterm", 9)) {
        b_expterm = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "c-expterm", 9)) {
        c_expterm = atof(vs[1].c_str());
        continue;
      }
      if (EQn(buffer, "vdwtype", 7))
        continue;
      if (EQn(buffer, "vdw-14-scale", 12))
        continue;
      if (EQn(buffer, "dielectric", 10)) {
        dielectric = atof(vs[1].c_str());
        continue;
      }
	
      parameter.clear();
	
      if (EQn(buffer, "bond3", 5))
        continue;
      if (EQn(buffer, "bond4", 5))
        continue;
      if (EQn(buffer, "bond", 4)) {
        parameter.a = atoi(vs[1].c_str());
        parameter.b = atoi(vs[2].c_str());
        parameter.dpar2 = atof(vs[3].c_str());
        parameter.dpar1 = atof(vs[4].c_str());
        _ffbondparams.push_back(parameter);
        continue;
      }
      if (EQn(buffer, "angle3", 6))
        continue;
      if (EQn(buffer, "angle4", 6))
        continue;
      if (EQn(buffer, "angle", 5)) {
        parameter.a = atoi(vs[1].c_str());
        parameter.b = atoi(vs[2].c_str());
        parameter.c = atoi(vs[3].c_str());
        parameter.dpar2 = atof(vs[4].c_str());
        parameter.dpar1 = atof(vs[5].c_str());
        _ffangleparams.push_back(parameter);
        continue;
      }
      if (EQn(buffer, "strbnd", 6)) {
        parameter.a = atoi(vs[1].c_str());	
        parameter.dpar1 = atof(vs[2].c_str());	
        _ffstretchbendparams.push_back(parameter);
        continue;
      }
      if (EQn(buffer, "torsion4", 8))
        continue;
      if (EQn(buffer, "torsion", 7)) {
        parameter.a = atoi(vs[1].c_str());
        parameter.b = atoi(vs[2].c_str());
        parameter.c = atoi(vs[3].c_str());
        parameter.d = atoi(vs[4].c_str());
        parameter.dpar1 = atof(vs[5].c_str());
        parameter.dpar2 = atof(vs[8].c_str());
        parameter.dpar3 = atof(vs[11].c_str());
        _fftorsionparams.push_back(parameter);
        continue;
      }
      if (EQn(buffer, "opbend", 6)) {
        parameter.a = atoi(vs[1].c_str());	
        parameter.b = atoi(vs[2].c_str());	
        parameter.dpar1 = atof(vs[3].c_str());	
        _ffoutplanebendparams.push_back(parameter);
        continue;
      }	
      if (EQn(buffer, "vdwpr", 5)) {
        parameter.a = atoi(vs[1].c_str());
        parameter.b = atoi(vs[2].c_str());
        parameter.dpar1 = atof(vs[3].c_str());
        parameter.dpar2 = atof(vs[4].c_str());
        _ffvdwprparams.push_back(parameter);
        continue;
      }	
      if (EQn(buffer, "vdw", 3)) {
        parameter.a = atoi(vs[1].c_str());
        parameter.dpar1 = atof(vs[2].c_str());
        parameter.dpar2 = atof(vs[3].c_str());
        _ffvdwparams.push_back(parameter);
        continue;
      }	
      if (EQn(buffer, "dipole", 6)) {
        parameter.a = atoi(vs[1].c_str());
        parameter.b = atoi(vs[2].c_str());
        parameter.dpar1 = atof(vs[3].c_str());
        parameter.dpar2 = atof(vs[4].c_str());
        _ffdipoleparams.push_back(parameter);
        continue;
      }
    }
	
    if (ifs1)
      ifs1.close();
    if (ifs2)
      ifs2.close();
 
    return 0;
  }
  
  bool OBForceFieldMM2::SetMM2Types()
  {
    string atomtype;

    ttab.SetFromType("INT");
    ttab.SetToType("MM2");
 
    FOR_ATOMS_OF_MOL(atom, _mol) {
      ttab.Translate(atomtype, atom->GetType());
      atom->SetType(atomtype);
    }  
  }
  
  double OBForceFieldMM2::Energy()
  {
    double energy;

    energy = E_Bond_Harmonic(bondunit, bond_cubic, bond_quartic);
    energy += E_Angle_Harmonic(angleunit, 0.0f, 0.0f, 0.0f, angle_sextic);
    energy += E_StretchBend(stretchbendunit);
    energy += E_Torsion(torsionunit);
    energy += E_OutPlaneBend(outplanebendunit);
    energy += E_VDWBuckingham(a_expterm, b_expterm, c_expterm);
    energy += E_DipoleDipole(dielectric);

    return energy;
  }

} // end namespace OpenBabel

//! \file forcefield.cpp
//! \brief Handle OBForceField class
