package StudyManager.demo.Controller;

import StudyManager.demo.Model.Lecturer;
import StudyManager.demo.Repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/lecturer")
public class LecturerController {

    private LecturerRepository lecturerRepository;

    @Autowired
    public LecturerController (LecturerRepository lecturerRepository){
        this.lecturerRepository = lecturerRepository;
    }

    @PostMapping("/Lecturer")
    public ResponseEntity<Lecturer> creatLecturer(@RequestBody Lecturer lecturer){
        try {
            Lecturer newLecturer = lecturerRepository.save(new Lecturer(lecturer.getEmployeeId(), lecturer.getFirstName(), lecturer.getLastName(),
                    lecturer.getNationalId()));
            return new ResponseEntity<>(newLecturer, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Lecturer")
    public ResponseEntity<List<Lecturer>> getAllLecturers(){
        try{
            List<Lecturer> lecturersList = lecturerRepository.findAll();
            if (lecturersList == null || lecturersList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lecturersList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Lecturer/{id}")
    public ResponseEntity<Lecturer> getLecturer(@PathVariable("id") Long id){
        try{
            Optional<Lecturer> lecturer = lecturerRepository.findById(id);
            if (lecturer.isPresent()){
                return new ResponseEntity<>(lecturer.get() , HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Lecturer/{id}")
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable("id") long id, @RequestBody Lecturer lecturer){
        Optional<Lecturer> lecturerData = lecturerRepository.findById(id);

        if (lecturerData.isPresent()){
            Lecturer lecturer1 = lecturerData.get();

            lecturer1.setFirstName(lecturer.getFirstName());
            lecturer1.setLastName(lecturer.getLastName());
            lecturer1.setNationalId(lecturer.getNationalId());
            lecturer1.setEmployeeId(lecturer.getEmployeeId());

            return new ResponseEntity<>(lecturerRepository.save(lecturer1), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Lecturer/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id){
        try {
            lecturerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
