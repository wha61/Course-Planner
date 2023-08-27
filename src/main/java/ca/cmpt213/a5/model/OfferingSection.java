package ca.cmpt213.a5.model;

/**
 *  OfferingSection class models the information of a specific section of one course offering. It includes course type, enrollmentCap and enrollmentCap.
 *  @author Wenxiang He
 */

public class OfferingSection {
//    public String type;
//    public int enrollmentCap;
//    public int enrollmentTotal;
    private String type;
    private int enrollmentCap;
    private int enrollmentTotal;

    public OfferingSection(String type, int enrollmentCap, int enrollmentTotal) {
        this.type = type;
        this.enrollmentCap = enrollmentCap;
        this.enrollmentTotal = enrollmentTotal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }

    public void setEnrollmentCap(int enrollmentCap) {
        this.enrollmentCap = enrollmentCap;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public void setEnrollmentTotal(int enrollmentTotal) {
        this.enrollmentTotal = enrollmentTotal;
    }


}
