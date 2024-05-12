package StudyManager.demo.Controller;

import StudyManager.demo.Model.Department;
import StudyManager.demo.Repository.DepartmentRepository;
import StudyManager.demo.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/department")
public class DepartmentController {

    private DepartmentRepository departmentRepository;
    private final CourseService courseService;

    @Autowired
    public DepartmentController (DepartmentRepository departmentRepository, CourseService courseService){
        this.departmentRepository = departmentRepository;
        this.courseService = courseService;
    }

    @PostMapping("/Department")
    public ResponseEntity<Department> creatDepartment(@RequestBody Department department){
        try {
            Department newDepartment = departmentRepository.save(new Department(department.getName(), department.getManagerName()));
            return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Department")
    public ResponseEntity<List<Department>> getAllDepartments(){
        try {
            List<Department> departmentList = departmentRepository.findAll();
            if (departmentList == null || departmentList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(departmentList, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Long id){
        try {
            Optional<Department> department = departmentRepository.findById(id);
            if (department.isPresent()){
                return new ResponseEntity<>(department.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department){
        Optional<Department> departmentData = departmentRepository.findById(id);

        if (departmentData.isPresent()){
            Department department1 = departmentData.get();

            department1.setName(department.getName());
            department1.setManagerName(department.getManagerName());

            return new ResponseEntity<>(departmentRepository.save(department1) , HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Department/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") Long id){
        try {
            departmentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
