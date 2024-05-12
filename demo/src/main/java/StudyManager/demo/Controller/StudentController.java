package StudyManager.demo.Controller;

import StudyManager.demo.Model.Student;
import StudyManager.demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostMapping("/Student")
    public ResponseEntity<Student> creatStudent(@RequestBody Student student){
        try {
            Student newStudent = studentRepository.save(new Student(student.getStudentId(), student.getFirstName(), student.getLastName(),
                    student.getNationalId(), student.getAddress()));
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Student")
    public ResponseEntity<List<Student>> getAllStudents(){
        try{
            List<Student> studentList = studentRepository.findAll();
            if (studentList == null || studentList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id){
        try{
            Optional<Student> student = studentRepository.findById(id);
            if (student.isPresent()){
                return new ResponseEntity<>(student.get() , HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student){
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()){
            Student student1 = studentData.get();

            student1.setStudentId(student.getStudentId());
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setNationalId(student.getNationalId());
            student1.setAddress(student.getAddress());

            return new ResponseEntity<>(studentRepository.save(student1), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id){
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
