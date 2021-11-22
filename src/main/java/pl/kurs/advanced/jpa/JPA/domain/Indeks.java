package pl.kurs.advanced.jpa.JPA.domain;

import javax.persistence.*;

@Entity
public class Indeks {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String number;

    @OneToOne(mappedBy = "indeks")
    private Student ownerStudent;

    private Indeks() {
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Indeks(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Indeks{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", ownerStudent=" + ownerStudent +
                '}';
    }

    public Student getOwnerStudent() {
        return ownerStudent;
    }

    public void setOwnerStudent(Student ownerStudent) {
        this.ownerStudent = ownerStudent;
    }
}
