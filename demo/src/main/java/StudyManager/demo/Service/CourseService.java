package StudyManager.demo.Service;


import StudyManager.demo.Model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    public List<Course> getCourse() {
        return List.of(
                new Course(
                        1l,
                        "math",
                        3
                )
        );
    }
}
