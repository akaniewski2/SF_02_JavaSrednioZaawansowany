package pl.kurs.advanced.jpa.JPA.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.getAll", query = "Select s from Student s"),
        @NamedQuery(name = "Student.getByName", query = "Select s from Student s where s.name=:name")
}
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String telePhone;

    @Embedded
    private Address address;

    @ManyToMany
    private Set<Classes> classes;


    @ManyToOne
     //@JoinColumn(name = "university")
    private University university;

    @OneToOne(cascade = CascadeType.ALL)
    private Indeks indeks;

    public University getUniversity() {
        return university;
    }


    private Student() {
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public Student( String name) {
        this.name = name;
    }

    public Student( String name,String indexNumber) {
        this.name = name;
        this.indeks=new Indeks(indexNumber );
        this.classes= new HashSet<>();
    }

    public void addClasses(Classes classes){
        this.classes.add(classes);
    }



    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Indeks getIndeks() {
        return indeks;
    }

    public void setIndeks(Indeks indeks) {
        this.indeks = indeks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telePhone='" + telePhone + '\'' +
                ", address=" + address +
                ", indeks=" + indeks +
                '}';
    }

    public void setUniversity(University university) {
        this.university=university;
    }
}
