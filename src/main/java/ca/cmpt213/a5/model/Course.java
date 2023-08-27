package ca.cmpt213.a5.model;

import ca.cmpt213.a5.observer.CourseObserver;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 *  Course class models the information of a specific course in one department. It includes courseId, catalogNumber, and a list of courses offerings in this course.
 *  It includes a list of CourseObserver.
 *  @author Wenxiang He
 */

public class Course {
    //    public long courseId;
//    public String catalogNumber;
    private int courseId;
    private String catalogNumber;
    @JsonIgnore
    private List<CourseOffering> courseOfferings = new ArrayList<>();
    private List<CourseObserver> observers = new ArrayList<>();


    public Course(String catalogNumber, String location, String instructors, String semesterCode, String sectionType, int enrollmentCap, int enrollmentTotal) {
        this.catalogNumber = catalogNumber;

        addOffering(location, instructors, semesterCode, sectionType, enrollmentCap, enrollmentTotal);
    }

    // check whether the new courseOffering is in the courseOfferings arraylist,
    // if it is already in the courseOfferings arraylist, add new attributes to that courseOffering.
    // else it is a new courseOffering, add it to the courseOfferings arraylist.
    public void addOffering(String location, String instructors, String semesterCode, String sectionType, int enrollmentCap, int enrollmentTotal) {
        // check whether the new courseOffering is in the courseOfferings arraylist,
        for (CourseOffering courseOffering : courseOfferings) {
            if (courseOffering.getSemesterCode().equals(semesterCode)) {
                courseOffering.addOfferingSection(sectionType, enrollmentCap, enrollmentTotal);
                return;
            }
        }
        // it is a new courseOffering, add it to the courseOfferings arraylist.
        CourseOffering newCourseOffering = new CourseOffering(location, instructors, semesterCode, sectionType, enrollmentCap, enrollmentTotal);
        courseOfferings.add(newCourseOffering);
    }

    public int getNumberOfCourseOffering() {
        return this.courseOfferings.size();
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    @JsonIgnore
    public List<CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }

    public void setOfferings(List<CourseOffering> courseOfferings) {
        this.courseOfferings = courseOfferings;
    }

    public void setCourseOfferings(List<CourseOffering> courseOfferings) {
        this.courseOfferings = courseOfferings;
    }



    public void addObservers(CourseObserver observer){
        observers.add(observer);
    }
    private void notifyObservers(){
        for(CourseObserver observer : observers){
            observer.stateChanged();
        }
    }
}
