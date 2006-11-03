/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.29
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */


public class OBIsotopeTable extends OBGlobalDataBase {
  private long swigCPtr;

  protected OBIsotopeTable(long cPtr, boolean cMemoryOwn) {
    super(net.sourceforge.openbabelJNI.SWIGOBIsotopeTableUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(OBIsotopeTable obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public void delete() {
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      net.sourceforge.openbabelJNI.delete_OBIsotopeTable(swigCPtr);
    }
    swigCPtr = 0;
    super.delete();
  }

  public OBIsotopeTable() {
    this(net.sourceforge.openbabelJNI.new_OBIsotopeTable(), true);
  }

  public long GetSize() {
    return net.sourceforge.openbabelJNI.OBIsotopeTable_GetSize(swigCPtr);
  }

  public void ParseLine(String arg0) {
    net.sourceforge.openbabelJNI.OBIsotopeTable_ParseLine(swigCPtr, arg0);
  }

  public double GetExactMass(long atomicNum, long isotope) {
    return net.sourceforge.openbabelJNI.OBIsotopeTable_GetExactMass__SWIG_0(swigCPtr, atomicNum, isotope);
  }

  public double GetExactMass(long atomicNum) {
    return net.sourceforge.openbabelJNI.OBIsotopeTable_GetExactMass__SWIG_1(swigCPtr, atomicNum);
  }

}
