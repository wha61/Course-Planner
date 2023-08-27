package ca.cmpt213.a5.controllers;


import ca.cmpt213.a5.observer.CourseWatcher;
import ca.cmpt213.a5.parser.CSVFileReader;
import ca.cmpt213.a5.model.AllDepartments;
import ca.cmpt213.a5.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {
    private List<Start> starts = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();


//    private CSVFileReader reader = new CSVFileReader();
//    private List<List<String>> data = reader.csvToArray("data/course_data_2018.csv");
//    private AllDepartments allDepartments = new AllDepartments(data);

    @GetMapping("/hello")
    public String getHelloMessage(){
        return  "Hello Spring Boot world !";
    }

    @PostMapping("/starts")
    @ResponseStatus(HttpStatus.CREATED)
    public Start createNewStart(@RequestBody Start start){
        // Set start to have next ID
        start.setId(nextId.incrementAndGet());

        starts.add(start);
        return start;
    }

    @GetMapping("/starts")
    public List<Start> getAllStarts(){
        return this.starts;
    }

    @GetMapping("/starts/{id}")
    public Start getOneStart(@PathVariable("id") long startId){
        for (Start start : starts){
            if(start.getId() == startId){
                return start;
            }
        }
//        return null;
        throw new IllegalArgumentException();
    }

    @PostMapping("starts/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Start editOneStart(@PathVariable("id") long startId, @RequestBody Start newStart){
        for (Start start : starts){
            if(start.getId() == startId){
                starts.remove(start);
                newStart.setId(startId);
                starts.add(newStart);
                return start;
            }
        }
//        return null;
        throw new IllegalArgumentException();
    }

    //create exception handle
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Request id not found!")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIDExceptionHandler(){
        //Nothing to do here.
    }

    // Read/about
    @GetMapping("/api/about")
    public About aboutMe() {
        About a = new About("Assignment 5: Course Planner", "Wenxiang He");
        return a;
    }

    // dump model
    private final CSVFileReader fileReader = new CSVFileReader();
    private List<List<String>> data = new ArrayList<>();
    {
        // exception handling
        try {
            data = fileReader.csvToArray("data/course_data_2022.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Incorrect path!!");
            System.exit(0);
        }
    }
    private final AllDepartments allDepartments = new AllDepartments(data);
    @GetMapping("/api/dump-model")
    public void printDumpModel() {
        System.out.println("All departments:");
        for (Department department: allDepartments.getDepartments()) {
            // print departments
            System.out.println("\t" + department.getName());
            for (Course course: department.getCourses()) {
                // print courses
                System.out.println("\t" + "\t" +department.getName() + " " + course.getCatalogNumber()); // cmpt 130
                for (CourseOffering offering: course.getCourseOfferings()) {
                    // print courseOfferings of one course
                    System.out.println("\t" +"\t" + "\t" + offering.getSemesterCode() + " in " + offering.getLocation() + " by " + offering.getInstructors());
                    for (OfferingSection section: offering.getOfferingSections()) {
                        // print exactly section of one courseOffering
                        System.out.println("\t" + "\t" + "\t" + "\t" + "Type=" + section.getType() + ", Enrollment=" + section.getEnrollmentTotal() + "/" + section.getEnrollmentCap());
                    }
                }
            }
        }
    }

    // Get departments
    @GetMapping("/api/departments")
    public List<Department> listOfAllApartments(){
        List<Department> departments = new ArrayList<>();
        for(int id = 0; id < allDepartments.getNumberOfDepartments(); id++){
            allDepartments.getDepartments().get(id).setDeptId(id);
            departments.add(id, allDepartments.getDepartments().get(id));
        }
        return departments;
    }

    // Get courses of CMPT
    @GetMapping("/api/departments/{DEPT}/courses")
    public List<Course> listDEPTCourses(@PathVariable("DEPT") int DEPT){

        for(int id = 0; id < allDepartments.getNumberOfDepartments(); id++){
            allDepartments.getDepartments().get(id).setDeptId(id);
        }
        // Here is not searching data set each time user makes a selection,
        // I search the data set to make sure the PathVariable is in range,
        // If in range, it loads corresponding data to meaningful collections and return it,
        // if not, throw 400 bad request exception.
        for(Department d: allDepartments.getDepartments()){
            if((d.getDeptId()) == DEPT){
                Department department = allDepartments.getDepartments().get(DEPT);
                List<Course> courses = new ArrayList<>();
                for(int id = 0; id < department.getNumberOfCourses(); id++){
                    department.getCourses().get(id).setCourseId(id);
                    courses.add(id, department.getCourses().get(id));
                }
                return courses;
            }
        }

        throw new IllegalArgumentException();

    }

    // Get sections of course
    @GetMapping("/api/departments/{DEPT}/courses/{COURSE}/offerings")
    public List<CourseOffering> listDEPTCourseSections(@PathVariable("DEPT") int DEPT, @PathVariable("COURSE") int COURSE){

        for(int DepId = 0; DepId < allDepartments.getNumberOfDepartments(); DepId++){
            allDepartments.getDepartments().get(DepId).setDeptId(DepId);
            for(int courseId = 0; courseId < allDepartments.getDepartments().get(DepId).getNumberOfCourses();courseId++){
                allDepartments.getDepartments().get(DepId).getCourses().get(courseId).setCourseId(courseId);
            }
        }
        // Here is not searching data set each time user makes a selection,
        // I search the data set to make sure the PathVariable is in range,
        // If in range, it loads corresponding data to meaningful collections and return it,
        // if not, throw 400 bad request exception.
        for(Department d: allDepartments.getDepartments()) {
            if(d.getDeptId() == DEPT) {
                for (Course c : allDepartments.getDepartments().get(DEPT).getCourses()) {
                    if (c.getCourseId() == COURSE) {
                        Department department = allDepartments.getDepartments().get(DEPT);
                        Course course = department.getCourses().get(COURSE);

                        List<CourseOffering> courseOfferings = new ArrayList<>();
                        for (int id = 0; id < course.getNumberOfCourseOffering(); id++) {
                            course.getCourseOfferings().get(id).setCourseOfferingId(id);
                            courseOfferings.add(id, course.getCourseOfferings().get(id));
                        }
                        return courseOfferings;
                    }
                }
            }
        }

        throw new IllegalArgumentException();

    }

    // Get specific course offering
    @GetMapping("/api/departments/{DEPT}/courses/{COURSE}/offerings/{OFFERING}")
    public List<OfferingSection> listDEPTCourseSectionOfferings(@PathVariable("DEPT") int DEPT, @PathVariable("COURSE") int COURSE,  @PathVariable("OFFERING") int OFFERING){

        for(int DepId = 0; DepId < allDepartments.getNumberOfDepartments(); DepId++){
            allDepartments.getDepartments().get(DepId).setDeptId(DepId);
            for(int courseId = 0; courseId < allDepartments.getDepartments().get(DepId).getNumberOfCourses();courseId++){
                allDepartments.getDepartments().get(DepId).getCourses().get(courseId).setCourseId(courseId);
                for(int courseOfferingId = 0; courseOfferingId < allDepartments.getDepartments().get(DepId).getCourses().get(courseId).getNumberOfCourseOffering();courseOfferingId++){
                    allDepartments.getDepartments().get(DepId).getCourses().get(courseId).getCourseOfferings().get(courseOfferingId).setCourseOfferingId(courseOfferingId);
                }
            }
        }
        // Here is not searching data set each time user makes a selection,
        // I search the data set to make sure the PathVariable is in range,
        // If in range, it loads corresponding data to meaningful collections and return it,
        // if not, throw 400 bad request exception.
        for(Department d: allDepartments.getDepartments()) {
            if(d.getDeptId() == DEPT) {
                for (Course c : allDepartments.getDepartments().get(DEPT).getCourses()) {
                    if (c.getCourseId() == COURSE) {
                        for (CourseOffering s : allDepartments.getDepartments().get(DEPT).getCourses().get(COURSE).getCourseOfferings()) {
                            if(s.getCourseOfferingId() == OFFERING){
                                Department department = allDepartments.getDepartments().get(DEPT);
                                Course course = department.getCourses().get(COURSE);
                                CourseOffering courseOffering = course.getCourseOfferings().get(OFFERING);

                                List<OfferingSection> offeringSections = new ArrayList<>();
                                for(int id = 0; id < courseOffering.getNumberOfOfferingSection(); id++){
                                    offeringSections.add(id, courseOffering.getOfferingSections().get(id));
                                }

                                return offeringSections;
                            }
                        }
                    }
                }
            }
        }

        throw new IllegalArgumentException();

    }


    @PostMapping("/api/addoffering")
    @ResponseStatus(HttpStatus.CREATED)
    public Department addOffering(@RequestBody OfferingData offeringData) {
        // addDepartment(String name, String catalogNumber, String location, String instructors, String semesterCode, String sectionType, int enrollmentCap, int enrollmentTotal) {
        Department newOffering = new Department(offeringData.getSubjectName(), offeringData.getCatalogNumber(), offeringData.getLocation(),
                offeringData.getInstructor(), offeringData.getSemester(), offeringData.calSemesterType(offeringData.getSemester()),
                offeringData.getEnrollmentCap(), offeringData.getEnrollmentTotal());
        allDepartments.addDepartment(offeringData.getSubjectName(), offeringData.getCatalogNumber(), offeringData.getLocation(),
                offeringData.getInstructor(), offeringData.getSemester(), offeringData.calSemesterType(offeringData.getSemester()),
                offeringData.getEnrollmentCap(), offeringData.getEnrollmentTotal());
        return newOffering;
    }


    @GetMapping("/api/watchers")
    public List<CourseWatcher> getCourseWatchers(){
        List<CourseWatcher> watchers = new ArrayList<>();
        return watchers;
    }

    @PostMapping("/api/watchers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCourseWatchers(){
        List<CourseWatcher> watchers = new ArrayList<>();
        watchers.add(new CourseWatcher());
    }



}
