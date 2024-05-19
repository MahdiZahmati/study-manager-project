package StudyManager.demo.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int units;

    @OneToMany(mappedBy = "course")
    private List<Assignment> assignmentList;

    @ManyToMany(mappedBy = "courseList")
    private List<Department> departmentList;

    public Course() {

    }

    public Course(String name, int units) {
        this.name = name;
        this.units = units;
    }


    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
