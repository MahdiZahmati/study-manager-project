package StudyManager.demo.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lecturer")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;
    private String firstName;
    private String lastName;
    private String nationalId;

    @ManyToMany(mappedBy = "lecturerList")
    private List<Department> departmentList;


    public Lecturer(){

    }
    public Lecturer(String employeeId, String firstName, String lastName, String nationalId){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
