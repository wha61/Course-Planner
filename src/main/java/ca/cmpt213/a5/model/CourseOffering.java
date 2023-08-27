package ca.cmpt213.a5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 *  CourseOffering class models the information of a specific course offering in one course. It includes location, instructors,
 *  term, semesterCode, year and a list of courses sections in this course offering.
 *  @author Wenxiang He
 */

public class CourseOffering {

//    public long courseOfferingId;
//    public String location;
//    public String instructors;
//    public String term;
//    public long semesterCode;
//    public int year;

    private int courseOfferingId;
    private String location;
    private String instructors;
    private String term;
    private String semesterCode;
    private int year;
    @JsonIgnore
    private List<OfferingSection> offeringSections = new ArrayList<>();



    public CourseOffering(String location, String instructors, String semesterCode,String sectionType, int enrollmentCap, int enrollmentTotal) {
        this.location = location;
        //If instructor is “(null)” or “<null>” then set instructor to “” (empty string).
        if(instructors.equals("<null>") || instructors.equals("(null)")){
            this.instructors = "";
        }
        else {
            this.instructors = instructors;
        }
        Semester s = new Semester(semesterCode);
        this.term = s.getSemester();
        this.semesterCode = semesterCode;
        this.year = s.getYear();

        addOfferingSection(sectionType, enrollmentCap, enrollmentTotal);
    }

    // if there is a new section, add it
    public void addOfferingSection(String sectionType, int enrollmentCap, int enrollmentTotal) {
        for(OfferingSection offeringSection: offeringSections) {
            if(offeringSection.getType().equals(sectionType)){
                offeringSection.setEnrollmentCap(offeringSection.getEnrollmentCap() + enrollmentCap);
                offeringSection.setEnrollmentTotal(offeringSection.getEnrollmentTotal() + enrollmentTotal);
                return;
            }
        }
        OfferingSection newOfferingSection = new OfferingSection(sectionType, enrollmentCap, enrollmentTotal);
        offeringSections.add(newOfferingSection);
    }

    public int getNumberOfOfferingSection(){
        return this.getOfferingSections().size();
    }

    public int getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(int courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructors() {
        return instructors;
    }

    public void setInstructors(String instructors) {
        this.instructors = instructors;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    @JsonIgnore
    public List<OfferingSection> getOfferingSections() {
        return offeringSections;
    }

    public void setOfferingSections(List<OfferingSection> offeringSections) {
        this.offeringSections = offeringSections;
    }

}
