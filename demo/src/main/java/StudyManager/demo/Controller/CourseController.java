package StudyManager.demo.Controller;


import StudyManager.demo.Model.Course;
import StudyManager.demo.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {

    private CourseRepository courseRepository;

    @Autowired
    public CourseController (CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @PostMapping("/Course")
    public ResponseEntity<Course> creatCourse(@RequestBody Course course){
        try{
            Course newCourse = courseRepository.save(new Course(course.getName(), course.getUnits()));
            return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Course")
    public ResponseEntity<List<Course>> getAllCourses(){
        try {
            List<Course> courseList = courseRepository.findAll();
            if (courseList == null || courseList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courseList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Course/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") Long id){
        try{
            Optional<Course> course = courseRepository.findById(id);
            if(course.isPresent()){
                return new ResponseEntity<>(course.get(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Course/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") Long id, @RequestBody Course course){
        Optional<Course> courseData = courseRepository.findById(id);

        if (courseData.isPresent()){
            Course course1 = courseData.get();

            course1.setName(course.getName());
            course1.setUnits(course.getUnits());

            return new ResponseEntity<>(courseRepository.save(course1), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Course/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") Long id){
        try {
            courseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
