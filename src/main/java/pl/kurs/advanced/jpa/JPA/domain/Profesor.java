package pl.kurs.advanced.jpa.JPA.domain;


import javax.persistence.*;

@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String telePhone;


    @Embedded
    private Address address;


    private Profesor() {
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public Profesor(  String name) {

        this.name = name;
    }

    public Profesor(  String name, String telePhone) {

        this.name = name;
        this.telePhone=telePhone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telePhone='" + telePhone + '\'' +
                ", address=" + address +
                '}';
    }
}
