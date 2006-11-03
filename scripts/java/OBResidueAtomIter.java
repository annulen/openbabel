/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.29
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */


public class OBResidueAtomIter {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected OBResidueAtomIter(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OBResidueAtomIter obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      net.sourceforge.openbabelJNI.delete_OBResidueAtomIter(swigCPtr);
    }
    swigCPtr = 0;
  }

  public OBResidueAtomIter() {
    this(net.sourceforge.openbabelJNI.new_OBResidueAtomIter__SWIG_0(), true);
  }

  public OBResidueAtomIter(OBResidue res) {
    this(net.sourceforge.openbabelJNI.new_OBResidueAtomIter__SWIG_1(OBResidue.getCPtr(res)), true);
  }

  public OBResidueAtomIter(OBResidueAtomIter ri) {
    this(net.sourceforge.openbabelJNI.new_OBResidueAtomIter__SWIG_3(OBResidueAtomIter.getCPtr(ri)), true);
  }

  public boolean good() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_good(swigCPtr);
  }

  public OBResidueAtomIter inc(int arg0) {
    return new OBResidueAtomIter(net.sourceforge.openbabelJNI.OBResidueAtomIter_inc(swigCPtr, arg0), true);
  }

  public OBAtom deref() {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_deref(swigCPtr);
    return (cPtr == 0) ? null : new OBAtom(cPtr, false);
  }

  public OBAtom __ref__() {
    return new OBAtom(net.sourceforge.openbabelJNI.OBResidueAtomIter___ref__(swigCPtr), false);
  }

  public void Clear() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_Clear(swigCPtr);
  }

  public void SetIdx(int idx) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetIdx(swigCPtr, idx);
  }

  public void SetHyb(int hyb) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetHyb(swigCPtr, hyb);
  }

  public void SetAtomicNum(int atomicnum) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetAtomicNum(swigCPtr, atomicnum);
  }

  public void SetIsotope(long iso) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetIsotope(swigCPtr, iso);
  }

  public void SetImplicitValence(int val) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetImplicitValence(swigCPtr, val);
  }

  public void IncrementImplicitValence() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_IncrementImplicitValence(swigCPtr);
  }

  public void DecrementImplicitValence() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_DecrementImplicitValence(swigCPtr);
  }

  public void SetFormalCharge(int fcharge) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetFormalCharge(swigCPtr, fcharge);
  }

  public void SetSpinMultiplicity(short spin) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetSpinMultiplicity(swigCPtr, spin);
  }

  public void SetType(String type) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetType__SWIG_0(swigCPtr, type);
  }

  public void SetType(SWIGTYPE_p_std__string type) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetType__SWIG_1(swigCPtr, SWIGTYPE_p_std__string.getCPtr(type));
  }

  public void SetPartialCharge(double pcharge) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetPartialCharge(swigCPtr, pcharge);
  }

  public void SetVector(vector3 v) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetVector__SWIG_0(swigCPtr, vector3.getCPtr(v));
  }

  public void SetVector(double x, double y, double z) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetVector__SWIG_1(swigCPtr, x, y, z);
  }

  public void SetVector() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetVector__SWIG_2(swigCPtr);
  }

  public void SetCoordPtr(SWIGTYPE_p_p_double c) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetCoordPtr(swigCPtr, SWIGTYPE_p_p_double.getCPtr(c));
  }

  public void SetResidue(OBResidue res) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetResidue(swigCPtr, OBResidue.getCPtr(res));
  }

  public void SetAromatic() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetAromatic(swigCPtr);
  }

  public void UnsetAromatic() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_UnsetAromatic(swigCPtr);
  }

  public void SetClockwiseStereo() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetClockwiseStereo(swigCPtr);
  }

  public void SetAntiClockwiseStereo() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetAntiClockwiseStereo(swigCPtr);
  }

  public void SetPositiveStereo() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetPositiveStereo(swigCPtr);
  }

  public void SetNegativeStereo() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetNegativeStereo(swigCPtr);
  }

  public void UnsetStereo() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_UnsetStereo(swigCPtr);
  }

  public void SetInRing() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetInRing(swigCPtr);
  }

  public void SetChiral() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetChiral(swigCPtr);
  }

  public void ClearCoordPtr() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_ClearCoordPtr(swigCPtr);
  }

  public int GetFormalCharge() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetFormalCharge(swigCPtr);
  }

  public long GetAtomicNum() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetAtomicNum(swigCPtr);
  }

  public int GetIsotope() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetIsotope(swigCPtr);
  }

  public int GetSpinMultiplicity() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetSpinMultiplicity(swigCPtr);
  }

  public double GetAtomicMass() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetAtomicMass(swigCPtr);
  }

  public double GetExactMass() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetExactMass(swigCPtr);
  }

  public long GetIdx() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetIdx(swigCPtr);
  }

  public long GetCoordinateIdx() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetCoordinateIdx(swigCPtr);
  }

  public long GetCIdx() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetCIdx(swigCPtr);
  }

  public long GetValence() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetValence(swigCPtr);
  }

  public long GetHyb() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetHyb(swigCPtr);
  }

  public long GetImplicitValence() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetImplicitValence(swigCPtr);
  }

  public long GetHvyValence() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetHvyValence(swigCPtr);
  }

  public long GetHeteroValence() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetHeteroValence(swigCPtr);
  }

  public String GetType() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetType(swigCPtr);
  }

  public double GetX() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetX(swigCPtr);
  }

  public double x() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_x(swigCPtr);
  }

  public double GetY() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetY(swigCPtr);
  }

  public double y() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_y(swigCPtr);
  }

  public double GetZ() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetZ(swigCPtr);
  }

  public double z() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_z(swigCPtr);
  }

  public SWIGTYPE_p_double GetCoordinate() {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_GetCoordinate(swigCPtr);
    return (cPtr == 0) ? null : new SWIGTYPE_p_double(cPtr, false);
  }

  public vector3 GetVector() {
    return new vector3(net.sourceforge.openbabelJNI.OBResidueAtomIter_GetVector(swigCPtr), false);
  }

  public double GetPartialCharge() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetPartialCharge(swigCPtr);
  }

  public OBResidue GetResidue() {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_GetResidue(swigCPtr);
    return (cPtr == 0) ? null : new OBResidue(cPtr, false);
  }

  public boolean GetNewBondVector(vector3 v, double length) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetNewBondVector(swigCPtr, vector3.getCPtr(v), length);
  }

  public OBBond GetBond(OBAtom arg0) {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_GetBond(swigCPtr, OBAtom.getCPtr(arg0));
    return (cPtr == 0) ? null : new OBBond(cPtr, false);
  }

  public OBAtom GetNextAtom() {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_GetNextAtom(swigCPtr);
    return (cPtr == 0) ? null : new OBAtom(cPtr, false);
  }

  public SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator BeginBonds() {
    return new SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator(net.sourceforge.openbabelJNI.OBResidueAtomIter_BeginBonds(swigCPtr), true);
  }

  public SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator EndBonds() {
    return new SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator(net.sourceforge.openbabelJNI.OBResidueAtomIter_EndBonds(swigCPtr), true);
  }

  public OBBond BeginBond(SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator i) {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_BeginBond(swigCPtr, SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator.getCPtr(i));
    return (cPtr == 0) ? null : new OBBond(cPtr, false);
  }

  public OBBond NextBond(SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator i) {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_NextBond(swigCPtr, SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator.getCPtr(i));
    return (cPtr == 0) ? null : new OBBond(cPtr, false);
  }

  public OBAtom BeginNbrAtom(SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator arg0) {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_BeginNbrAtom(swigCPtr, SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator.getCPtr(arg0));
    return (cPtr == 0) ? null : new OBAtom(cPtr, false);
  }

  public OBAtom NextNbrAtom(SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator arg0) {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_NextNbrAtom(swigCPtr, SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator.getCPtr(arg0));
    return (cPtr == 0) ? null : new OBAtom(cPtr, false);
  }

  public double GetDistance(int index) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetDistance__SWIG_0(swigCPtr, index);
  }

  public double GetDistance(OBAtom arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetDistance__SWIG_1(swigCPtr, OBAtom.getCPtr(arg0));
  }

  public double GetAngle(int b, int c) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetAngle__SWIG_0(swigCPtr, b, c);
  }

  public double GetAngle(OBAtom b, OBAtom c) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_GetAngle__SWIG_1(swigCPtr, OBAtom.getCPtr(b), OBAtom.getCPtr(c));
  }

  public void NewResidue() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_NewResidue(swigCPtr);
  }

  public void DeleteResidue() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_DeleteResidue(swigCPtr);
  }

  public void AddBond(OBBond bond) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_AddBond(swigCPtr, OBBond.getCPtr(bond));
  }

  public void InsertBond(SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator i, OBBond bond) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_InsertBond(swigCPtr, SWIGTYPE_p_std__vectorTOpenBabel__OBEdgeBase_p_t__iterator.getCPtr(i), OBBond.getCPtr(bond));
  }

  public boolean DeleteBond(OBBond arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_DeleteBond(swigCPtr, OBBond.getCPtr(arg0));
  }

  public void ClearBond() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_ClearBond(swigCPtr);
  }

  public long CountFreeOxygens() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_CountFreeOxygens(swigCPtr);
  }

  public long ImplicitHydrogenCount() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_ImplicitHydrogenCount(swigCPtr);
  }

  public long ExplicitHydrogenCount(boolean ExcludeIsotopes) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_ExplicitHydrogenCount__SWIG_0(swigCPtr, ExcludeIsotopes);
  }

  public long ExplicitHydrogenCount() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_ExplicitHydrogenCount__SWIG_1(swigCPtr);
  }

  public long MemberOfRingCount() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_MemberOfRingCount(swigCPtr);
  }

  public long MemberOfRingSize() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_MemberOfRingSize(swigCPtr);
  }

  public long CountRingBonds() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_CountRingBonds(swigCPtr);
  }

  public double SmallestBondAngle() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_SmallestBondAngle(swigCPtr);
  }

  public double AverageBondAngle() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_AverageBondAngle(swigCPtr);
  }

  public long BOSum() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_BOSum(swigCPtr);
  }

  public long KBOSum() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_KBOSum(swigCPtr);
  }

  public boolean HtoMethyl() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HtoMethyl(swigCPtr);
  }

  public boolean SetHybAndGeom(int arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_SetHybAndGeom(swigCPtr, arg0);
  }

  public void ForceNoH() {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_ForceNoH(swigCPtr);
  }

  public boolean HasNoHForced() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasNoHForced(swigCPtr);
  }

  public boolean HasResidue() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasResidue(swigCPtr);
  }

  public boolean IsHydrogen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsHydrogen(swigCPtr);
  }

  public boolean IsCarbon() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsCarbon(swigCPtr);
  }

  public boolean IsNitrogen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsNitrogen(swigCPtr);
  }

  public boolean IsOxygen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsOxygen(swigCPtr);
  }

  public boolean IsSulfur() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsSulfur(swigCPtr);
  }

  public boolean IsPhosphorus() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsPhosphorus(swigCPtr);
  }

  public boolean IsAromatic() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsAromatic(swigCPtr);
  }

  public boolean IsInRing() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsInRing(swigCPtr);
  }

  public boolean IsInRingSize(int arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsInRingSize(swigCPtr, arg0);
  }

  public boolean IsHeteroatom() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsHeteroatom(swigCPtr);
  }

  public boolean IsNotCorH() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsNotCorH(swigCPtr);
  }

  public boolean IsConnected(OBAtom arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsConnected(swigCPtr, OBAtom.getCPtr(arg0));
  }

  public boolean IsOneThree(OBAtom arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsOneThree(swigCPtr, OBAtom.getCPtr(arg0));
  }

  public boolean IsOneFour(OBAtom arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsOneFour(swigCPtr, OBAtom.getCPtr(arg0));
  }

  public boolean IsCarboxylOxygen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsCarboxylOxygen(swigCPtr);
  }

  public boolean IsPhosphateOxygen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsPhosphateOxygen(swigCPtr);
  }

  public boolean IsSulfateOxygen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsSulfateOxygen(swigCPtr);
  }

  public boolean IsNitroOxygen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsNitroOxygen(swigCPtr);
  }

  public boolean IsAmideNitrogen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsAmideNitrogen(swigCPtr);
  }

  public boolean IsPolarHydrogen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsPolarHydrogen(swigCPtr);
  }

  public boolean IsNonPolarHydrogen() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsNonPolarHydrogen(swigCPtr);
  }

  public boolean IsAromaticNOxide() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsAromaticNOxide(swigCPtr);
  }

  public boolean IsChiral() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsChiral(swigCPtr);
  }

  public boolean IsAxial() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsAxial(swigCPtr);
  }

  public boolean IsClockwise() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsClockwise(swigCPtr);
  }

  public boolean IsAntiClockwise() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsAntiClockwise(swigCPtr);
  }

  public boolean IsPositiveStereo() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsPositiveStereo(swigCPtr);
  }

  public boolean IsNegativeStereo() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsNegativeStereo(swigCPtr);
  }

  public boolean HasChiralitySpecified() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasChiralitySpecified(swigCPtr);
  }

  public boolean HasChiralVolume() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasChiralVolume(swigCPtr);
  }

  public boolean IsHbondAcceptor() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsHbondAcceptor(swigCPtr);
  }

  public boolean IsHbondDonor() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsHbondDonor(swigCPtr);
  }

  public boolean IsHbondDonorH() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_IsHbondDonorH(swigCPtr);
  }

  public boolean HasAlphaBetaUnsat(boolean includePandS) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasAlphaBetaUnsat__SWIG_0(swigCPtr, includePandS);
  }

  public boolean HasAlphaBetaUnsat() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasAlphaBetaUnsat__SWIG_1(swigCPtr);
  }

  public boolean HasBondOfOrder(long arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasBondOfOrder(swigCPtr, arg0);
  }

  public int CountBondsOfOrder(long arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_CountBondsOfOrder(swigCPtr, arg0);
  }

  public boolean HasNonSingleBond() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasNonSingleBond(swigCPtr);
  }

  public boolean HasSingleBond() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasSingleBond(swigCPtr);
  }

  public boolean HasDoubleBond() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasDoubleBond(swigCPtr);
  }

  public boolean HasAromaticBond() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasAromaticBond(swigCPtr);
  }

  public boolean MatchesSMARTS(String arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_MatchesSMARTS(swigCPtr, arg0);
  }

  public void setVisit(boolean value) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_Visit_set(swigCPtr, value);
  }

  public boolean getVisit() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_Visit_get(swigCPtr);
  }

  public OBGraphBase GetParent() {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_GetParent(swigCPtr);
    return (cPtr == 0) ? null : new OBGraphBase(cPtr, false);
  }

  public void SetParent(OBGraphBase arg0) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetParent(swigCPtr, OBGraphBase.getCPtr(arg0));
  }

  public void AddEdge(OBEdgeBase b) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_AddEdge(swigCPtr, OBEdgeBase.getCPtr(b));
  }

  public void Error(int f) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_Error(swigCPtr, f);
  }

  public void SetMatch(OBNodeBase arg0) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetMatch(swigCPtr, OBNodeBase.getCPtr(arg0));
  }

  public boolean Eval(OBNodeBase arg0) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_Eval(swigCPtr, OBNodeBase.getCPtr(arg0));
  }

  public OBNodeBase GetMatch() {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_GetMatch(swigCPtr);
    return (cPtr == 0) ? null : new OBNodeBase(cPtr, false);
  }

  public OBBase DoTransformations(SWIGTYPE_p_std__mapTstd__string_std__string_t arg0) {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_DoTransformations(swigCPtr, SWIGTYPE_p_std__mapTstd__string_std__string_t.getCPtr(arg0));
    return (cPtr == 0) ? null : new OBBase(cPtr, false);
  }

  public String ClassDescription() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_ClassDescription(swigCPtr);
  }

  public boolean HasData(long type) {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_HasData__SWIG_2(swigCPtr, type);
  }

  public void DeleteData(long type) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_DeleteData__SWIG_0(swigCPtr, type);
  }

  public void DeleteData(OBGenericData arg0) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_DeleteData__SWIG_1(swigCPtr, OBGenericData.getCPtr(arg0));
  }

  public void DeleteData(vectorData arg0) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_DeleteData__SWIG_2(swigCPtr, vectorData.getCPtr(arg0));
  }

  public void SetData(OBGenericData d) {
    net.sourceforge.openbabelJNI.OBResidueAtomIter_SetData(swigCPtr, OBGenericData.getCPtr(d));
  }

  public long DataSize() {
    return net.sourceforge.openbabelJNI.OBResidueAtomIter_DataSize(swigCPtr);
  }

  public OBGenericData GetData(long type) {
    long cPtr = net.sourceforge.openbabelJNI.OBResidueAtomIter_GetData__SWIG_0(swigCPtr, type);
    return (cPtr == 0) ? null : new OBGenericData(cPtr, false);
  }

  public vectorData GetData() {
    return new vectorData(net.sourceforge.openbabelJNI.OBResidueAtomIter_GetData__SWIG_3(swigCPtr), false);
  }

  public SWIGTYPE_p_std__vectorTOpenBabel__OBGenericData_p_t__iterator BeginData() {
    return new SWIGTYPE_p_std__vectorTOpenBabel__OBGenericData_p_t__iterator(net.sourceforge.openbabelJNI.OBResidueAtomIter_BeginData(swigCPtr), true);
  }

  public SWIGTYPE_p_std__vectorTOpenBabel__OBGenericData_p_t__iterator EndData() {
    return new SWIGTYPE_p_std__vectorTOpenBabel__OBGenericData_p_t__iterator(net.sourceforge.openbabelJNI.OBResidueAtomIter_EndData(swigCPtr), true);
  }

}
