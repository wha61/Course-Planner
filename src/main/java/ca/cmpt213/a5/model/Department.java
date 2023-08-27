package ca.cmpt213.a5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 *  OfferingSection class models the information of a specific department. It includes department id, department name, and a list of courses in this department.
 *  @author Wenxiang He
 */

public class Department {
    private int deptId;
    private String name;
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    public Department(String name, String catalogNumber, String location, String instructors, String semesterCode,String sectionType, int enrollmentCap, int enrollmentTotal) {
        this.name = name;

        addCourse(catalogNumber,location, instructors, semesterCode, sectionType,enrollmentCap, enrollmentTotal);
    }

    // check whether the new course is in the courses arraylist,
    // if it is already in the courses arraylist, add new attributes(courseOffering) to that corresponding courses.
    // else it is a new course, add it to the courses arraylist.
    public void addCourse(String catalogNumber, String location, String instructors, String semesterCode,String sectionType, int enrollmentCap, int enrollmentTotal) {
        // check whether the new course is in the courses arraylist,
        for(Course course: courses) {
            if(course.getCatalogNumber().equals(catalogNumber)){
                // it is already in the courses arraylist, add new attributes(courseOffering) to that corresponding courses.
                course.addOffering(location, instructors, semesterCode, sectionType,enrollmentCap, enrollmentTotal);
                return;
            }
        }
        // it is a new course, add it to the courses arraylist.
        Course newCourse = new Course(catalogNumber, location, instructors, semesterCode, sectionType, enrollmentCap, enrollmentTotal);
        courses.add(newCourse);
    }

    public int getNumberOfCourses(){
        return this.courses.size();
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}


