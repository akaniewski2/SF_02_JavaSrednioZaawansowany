package pl.kurs.advanced.jpa.JPA.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class University {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "university",fetch = FetchType.EAGER)
    private Set<Student> students;


    private University() {
    }


    public University(String name) {
        this.students=new HashSet<>();
        this.name=name;
    }


    public void addStudent(Student student) {
        students.add(student);
    }


    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    public int getId() {
        return id;
    }
}
