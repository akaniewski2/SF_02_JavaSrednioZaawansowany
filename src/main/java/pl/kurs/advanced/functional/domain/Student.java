package pl.kurs.advanced.functional.domain;


import java.util.Objects;
import java.util.Optional;

//programowanie funkcyjne
//klasy typu immutable-niezmienna
//czyte funckje
//unikamy nulli
//funkje stoja na tym samym poziomie waznosci co inne obiekty
final public class Student implements Comparable<Student> {

    private String name;
    private int age;
    private Indeks index;


    public Student(String name, int age,String indexNumber) {
        this.name = name;
        this.age = age;
        this.index=new Indeks(indexNumber);
    }

    // taki konstruktor dopuszcza nulla dla indexu tak wiec getIndex musi by optionalem
    public Student(String name, int age/*,String indexNumber*/) {
        this.name = name;
        this.age = age;
     //   this.index=new Indeks(indexNumber);
    }

    public Optional<Indeks> getIndex() {
        return  Optional.ofNullable(this.index) ;
    }



//    public void setIndex(Indeks index) {
//        this.index = index;
//    }

    public String getName() {
        return name;
    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public int getAge() {
        return age;
    }

//    public void setAge(int age) {
//        this.age = age;
//    }

    public  String getStudentInformation() {
        return this.name+ " " + this.age;
    }


    //jesli chcemy zworocic studenta tworzymy nowy obiekt
    public Student changeIndexNumber(String newIndexNumber) {
        return new Student(this.name,this.age,newIndexNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", index=" + index +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return index.equals(student.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    @Override
    public int compareTo(Student o) {
       return this.name.compareTo(o.getName());
    }
}
