package ca.cmpt213.a5.observer;

import ca.cmpt213.a5.model.Course;
import ca.cmpt213.a5.model.Department;
import ca.cmpt213.a5.wrappers.ApiCourseWrapper;
import ca.cmpt213.a5.wrappers.ApiDepartmentWrapper;

import java.util.List;

public class CourseWatcher implements CourseObserver{
//    public long id;
//    public ApiDepartmentWrapper department;
//    public ApiCourseWrapper course;
//    public List<String> events;
    private int id;
    private Department department;
    private Course course;
    private List<String> events;

    public CourseWatcher() {
    }

    public CourseWatcher(int id, Department department, Course course, List<String> events) {
        this.id = id;
        this.department = department;
        this.course = course;
        this.events = events;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    @Override
    public void stateChanged(){
        String s = "+1";
        events.add(s);
    };

    public void addEvent(){

    }

}
