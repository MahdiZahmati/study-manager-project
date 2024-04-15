package StudyManager.demo.Service;

import StudyManager.demo.Model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents(){
        return List.of(
                new Student(
                        1l,
                        "40123892",
                        "mahdi",
                        "zahmati",
                        "401558654"
                )
        );
    }
}
