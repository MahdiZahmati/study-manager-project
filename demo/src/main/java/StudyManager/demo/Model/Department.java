package StudyManager.demo.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "department")
public class Department implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String managerName;

    @ManyToMany
    @JoinTable(
            name = "student-department",
            joinColumns = @JoinColumn (name = "department_id"),
            inverseJoinColumns = @JoinColumn (name = "student_id")
    )
    private List<Student> studentList;

    @ManyToMany
    @JoinTable(
            name = "lecturer_department",
            joinColumns = @JoinColumn (name = "department_id"),
            inverseJoinColumns = @JoinColumn (name = "lecturer_id")
    )
    private List<Lecturer> lecturerList;
    @ManyToMany
    @JoinTable(
            name = "department_course",
            joinColumns = @JoinColumn (name = "department_id"),
            inverseJoinColumns = @JoinColumn (name = "course_id")
    )
    private List<Course> courseList;


    public Department(){

    }
    public Department(String name, String managerName){
        this.name = name;
        this.managerName = managerName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(List<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

}
