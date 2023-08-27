package ca.cmpt213.a5.model;

/**
 *  Semester class models the information of the Semester. It calculates year and semester based on semester code.
 *  @author Wenxiang He
 */

public class Semester {
    private int year;
    private String semester;

    // get year and semester according to semester code
    public Semester(String semesterCode) {
        this.year = calYear(semesterCode);
        this.semester = calSemester(semesterCode);
    }

    public int calYear(String semesterCode){
        int intValue = Integer.parseInt(semesterCode);
        final int X_CALCULATOR = 1000;
        final int Y_CALCULATOR = 100;
        final int Z_CALCULATOR = 10;
//        final int A_CALCULATOR = 1;
        int X = intValue / X_CALCULATOR;
        int Y = (intValue - X * X_CALCULATOR) / Y_CALCULATOR;
        int Z = (intValue - X * X_CALCULATOR - Y * Y_CALCULATOR) / Z_CALCULATOR;
//        int A = (intValue - X * X_CALCULATOR - Y * Y_CALCULATOR - Z * Z_CALCULATOR) / A_CALCULATOR;

        int year = 1900 + 100*X + 10*Y + 1*Z;
        return year;
    }
    public String calSemester(String semesterCode){
        int intValue = Integer.parseInt(semesterCode);
        final int X_CALCULATOR = 1000;
        final int Y_CALCULATOR = 100;
        final int Z_CALCULATOR = 10;
        final int A_CALCULATOR = 1;
        int X = intValue / X_CALCULATOR;
        int Y = (intValue - X * X_CALCULATOR) / Y_CALCULATOR;
        int Z = (intValue - X * X_CALCULATOR - Y * Y_CALCULATOR) / Z_CALCULATOR;
        int A = (intValue - X * X_CALCULATOR - Y * Y_CALCULATOR - Z * Z_CALCULATOR) / A_CALCULATOR;
        String semester = "";
        if(A == 1){
            semester = "Spring";
        }
        else if(A == 4){
            semester = "Summer";
        }
        else if(A == 7){
            semester = "Fall";
        }
        else {
            semester = "Not given";
        }

        return semester;
    }


    public int getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }
}
