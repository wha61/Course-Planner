package ca.cmpt213.a5.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 *  Course class models the information of all departments. It includes a list of departments.
 *  It also get data read from .csv file and save it in the models (AllDepartments->Departments->Course->CourseOffering->CourseSection)
 *  @author Wenxiang He
 */

public class AllDepartments {
    private List<Department> departments = new ArrayList<>();

    // here the model get fill with data read from .csv file
    public AllDepartments(List<List<String>> data) {

        //String name, String catalogNumber, String location, String instructors, int semesterCode,String sectionType, int enrollmentCap, int enrollmentTotal
        for (int i = 1; i < data.size(); i++) {
            List<String> oneLine = data.get(i);
            String name = oneLine.get(1).trim();
            String catalogNumber = oneLine.get(2).trim();
            String location = oneLine.get(3).trim();
            // in case there are more than one instructor
            int size = oneLine.size();
            String instructors = getInstructors(size, oneLine);

            String semesterCode = oneLine.get(0).trim();
            String sectionType = oneLine.get(oneLine.size() - 1).trim();
            int enrollmentCap = Integer.parseInt(oneLine.get(4).trim());
            int enrollmentTotal = Integer.parseInt(oneLine.get(5).trim());

            addDepartment(name, catalogNumber, location, instructors, semesterCode, sectionType, enrollmentCap, enrollmentTotal);
        }

    }
    // check whether the new department is in the departments arraylist,
    // if it is already in the departments arraylist, add new attributes(course) to that corresponding department.
    // else it is a new department, add it to the departments arraylist.
    public void addDepartment(String name, String catalogNumber, String location, String instructors, String semesterCode, String sectionType, int enrollmentCap, int enrollmentTotal) {
        // check whether the new department is in the departments arraylist,
        for (Department department : departments) {
            if (department.getName().equals(name)) {
                // it is already in the departments arraylist, add new attributes(course) to that corresponding department.
                department.addCourse(catalogNumber, location, instructors, semesterCode, sectionType, enrollmentCap, enrollmentTotal);
                return;
            }
        }
        // it is a new department, add it to the departments arraylist.
        Department newDepartment = new Department(name, catalogNumber, location, instructors, semesterCode, sectionType, enrollmentCap, enrollmentTotal);
        departments.add(newDepartment);
    }
    public String getInstructors(int size, List<String> oneLine) {
        final int INITIAL_ONE_LINE_SIZE = 8;
        String instructors = "";
        if (size == INITIAL_ONE_LINE_SIZE) {
            instructors = oneLine.get(6).trim();
        } else {
            for (int index = 6; index < size - 1; index++) {
                instructors += (oneLine.get(index) + ",");
            }
            instructors = instructors.substring(1, instructors.length() - 1);
            instructors = instructors.trim();
        }
        return instructors;
    }

    public int getNumberOfDepartments(){
        return this.departments.size();
    }
    @JsonIgnore
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }


}
