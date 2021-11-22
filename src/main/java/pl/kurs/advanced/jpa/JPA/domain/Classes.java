package pl.kurs.advanced.jpa.JPA.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Classes /*zajecia*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "classes")
    private Set<Student> students;

    public Classes() {
    }

    public Classes(String name) {
        this.name = name;
        this.students = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }


}
