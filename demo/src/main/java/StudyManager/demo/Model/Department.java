package StudyManager.demo.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String managerName;

    @OneToMany(mappedBy = "department")
    private List<Lecturer> lecturerList;

    @OneToMany(mappedBy = "department")
    private List<Student> studentList;

    @OneToMany(mappedBy = "department")
    private List<Course> courseList;


    public Department(){

    }
    public Department(String name, String managerName){
        this.name = name;
        this.managerName = managerName;
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
