package ca.cmpt213.a5.model;

/**
 *  OfferingData class models the full information of a specific section (from department to section)
 *  @author Wenxiang He
 */


public class OfferingData {
//    public String semester;
//    public String subjectName;
//    public String catalogNumber;
//    public String location;
//    public int enrollmentCap;
//    public String component;
//    public int enrollmentTotal;
//    public String instructor;
    private String semester;
    private String subjectName;
    private String catalogNumber;
    private String location;
    private int enrollmentCap;
    private String component;
    private int enrollmentTotal;
    private String instructor;

    public String calSemesterType(String semester){
        int intValue = Integer.parseInt(semester);
        final int X_CALCULATOR = 1000;
        final int Y_CALCULATOR = 100;
        final int Z_CALCULATOR = 10;
        final int A_CALCULATOR = 1;
        int X = intValue / X_CALCULATOR;
        int Y = (intValue - X * X_CALCULATOR) / Y_CALCULATOR;
        int Z = (intValue - X * X_CALCULATOR - Y * Y_CALCULATOR) / Z_CALCULATOR;
        int A = (intValue - X * X_CALCULATOR - Y * Y_CALCULATOR - Z * Z_CALCULATOR) / A_CALCULATOR;
        String semesterType = "";
        if(A == 1){
            semesterType = "Spring";
        }
        else if(A == 4){
            semesterType = "Summer";
        }
        else if(A == 7){
            semesterType = "Fall";
        }
        else {
            semesterType = "Not given";
        }

        return semesterType;
    }

    public String getSemester() {
        return semester;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getLocation() {
        return location;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }

    public String getComponent() {
        return component;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public String getInstructor() {
        return instructor;
    }
}
