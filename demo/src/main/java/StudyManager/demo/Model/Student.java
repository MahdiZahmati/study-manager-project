package StudyManager.demo.Model;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String studentId;
    private String firstName;
    private String lastName;
    private String nationalId;
    private String address;

    @OneToMany(mappedBy = "student")
    private List<Course> courseList;

    public Student(){

    }

    public Student(String studentId, String firstName, String lastName, String nationalId, String address) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.address = address;
    }

    public Student(String studentId, String firstName, String lastName, String nationalId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
