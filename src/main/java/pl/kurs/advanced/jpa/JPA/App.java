package pl.kurs.advanced.jpa.JPA;


import org.h2.command.dml.Select;
import org.h2.mvstore.tx.Transaction;
import org.hibernate.type.SpecialOneToOneType;
import pl.kurs.advanced.jpa.JPA.domain.Indeks;
import pl.kurs.advanced.jpa.JPA.domain.Profesor;
import pl.kurs.advanced.jpa.JPA.domain.Student;
import pl.kurs.advanced.jpa.JPA.domain.University;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App {

    //singleton
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("JpaPersistenceXml_DEV");
    //to nie jest juz singleton clasa odpowiada za komunikacje z JPA +  jednorazowe polaczenie z baza danych -
    static EntityManager em = factory.createEntityManager();
    //hibernate operuje na  import org.hibernate.Session; ,sessionFactory


    public static void main(String[] args) throws InterruptedException {
        //createDataJPA();
//        createDataJPQL();
//          createDataJPQL2();
//        createDataJPQL3();
  //      createDataJPQL4();
        createDataJPQL5();

    }



    public static void createDataJPQL5() {
        // Lazy / Eager
        Student student;
        Student student2;
        University university = null;

        em.getTransaction().begin();

        student = em.merge(new Student("Pawel", "32354"));
        student2 = em.merge(new Student("Jan", "456789"));

        university = em.merge(new University("UG"));
        university.addStudent(student);
        university.addStudent(student2);

        em.merge(university);


        em.getTransaction().commit();

        em.clear();


        em.createQuery("from University").getResultList().forEach(System.out::println);



    }


    public static void createDataJPQL4() {
    // count,groupby ,namaedQuery
        Student student;
        University university;

        em.getTransaction().begin();


        student = em.merge(new Student("Pawel", "12354"));
        student = em.merge(new Student("Pawel", "22354"));
        student = em.merge(new Student("Pawel", "32354"));
        student = em.merge(new Student("Jan", "456789"));


        em.getTransaction().commit();

//        Query query1 = em.createQuery("select s.name, s.indeks.number from Student s ");

        String s = "pl.kurs.advanced.jpa.JPA";
//        TypedQuery<QueryResult> query = em.createQuery("select new " + s + ".QueryResult(s.name, s.indeks.number) from Student s ", QueryResult.class);
        TypedQuery<QueryResultRecord> query = em.createQuery("select new " + s + ".QueryResultRecord(s.name, s.indeks.number ) from Student s ", QueryResultRecord.class);

        query.getResultList().forEach(System.out::println);


        List<QueryResultGroupBy> resultList = em.createQuery("Select new " + s + ".QueryResultGroupBy( s.name,count(s) ) from Student s group by s.name having s.name like 'P%'", QueryResultGroupBy.class).getResultList();

        resultList.forEach(System.out::println);


        System.out.println("--------- Student.getAll");
        em.createNamedQuery("Student.getAll",Student.class).getResultList().forEach(System.out::println);
        System.out.println("--------- Student.getByName");
        em.createNamedQuery("Student.getByName",Student.class).setParameter("name","Jan").getResultList().forEach(System.out::println);


    }

        public static void createDataJPQL3() {

        Student student;
        University university;
        em.getTransaction().begin();

        student = em.merge(new Student("Pawel", "12354"));
        student = em.merge(new Student("Jan", "456789"));

        em.getTransaction().commit();

        Query query = em.createQuery("select s, s.indeks from Student s ");


        System.out.println(query.getResultList()); //objekt
        query.getResultList().forEach(result -> {

            Object[] resultCasted = (Object[]) result;

            if(resultCasted[0] instanceof Student) {
                System.out.println(resultCasted[0]);
            }
        });


    }

    public static void createDataJPQL2() {

        Student student;

        University university;

        em.getTransaction().begin();

        student = em.merge(new Student("Pawel", "12354"));
        student = em.merge(new Student("Jan", "456789"));

        em.getTransaction().commit();

        Query query = em.createQuery("select s, s.indeks from Student s ");


        System.out.println(query.getResultList()); //objekt
        query.getResultList().forEach(result -> {

            Object[] resultCasted = (Object[]) result;

            if(resultCasted[0] instanceof Student) {
                System.out.println(resultCasted[0]);
            }
        });


    }

    public static void createDataJPQL(){

        Student student;


        University university;

        em.getTransaction().begin();;

        student=em.merge(new Student("Pawel","12354"));
        student=em.merge(new Student("Jan","456789"));


        em.getTransaction().commit();


        //    em.createQuery("from Student").getResultList().forEach(System.out::println);
//        em.createQuery("select s.indeks from Student s").getResultList().forEach(System.out::println);

        em.createQuery("from Student").getResultList();
        // List<Indeks> resultList = em.createQuery("select s.indeks from Student s", Indeks.class).getResultList();

        TypedQuery<Indeks> query = em.createQuery("select s.indeks from Student s where s.name=:name", Indeks.class);
        query.setParameter("name","Jan");

        System.out.println(query.getSingleResult());



    }


    public static void createDataJPA(){


        Student student;
        University university;

        em.getTransaction().begin();;

        student=em.merge(new Student("Pawel","12354"));
        university = em.merge(new University("UG"));

        student.setUniversity(university);
        university.addStudent(student);

        em.getTransaction().commit();

        university = em.find(University.class, university.getId());
        System.out.println(university);




//        em.getTransaction().begin();
//
//        student = em.merge(new Student("A", "123456"));
//        em.merge(student);
////        Indeks indeks = em.merge(new Indeks("123456"));
//        em.getTransaction().commit();
//
//
//        em.getTransaction().begin();
//        University university = em.merge(new University("UG"));
//        university.addStudent(student);
//        em.getTransaction().commit();
//
//
//        System.out.println(student);

//        student.setIndeks(indeks);
//        student = em.merge(student);
//        System.out.println(student);
//
//        indeks.setOwnerStudent(student);
//
//
//        Indeks idx = em.find(Indeks.class,indeks.getId());
//        System.out.println(idx);

//        System.out.println(student);


//        createStudent();
//        readStudent();
//        updateStudent();
//        readStudent();
//        deleteStudent(0);
//        readStudent();
//        TimeUnit.MINUTES.sleep(3);



    }

    private static void deleteStudent(int id) {

        System.out.println("--------- DELETE ");
        Profesor student = em.find(Profesor.class, id);

        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
    }

    private static void updateStudent() {
        System.out.println("--------- UPDATE ");

        Profesor student = em.find(Profesor.class, 2);
        student.setTelePhone("126");

        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();


        System.out.println("--------- UPDATE 2");
        //merge moze tez dodadwac
        Profesor student1 = new Profesor("Magda");

        em.getTransaction().begin();
        Profesor studentAdded = em.merge(student1);
        studentAdded.setTelePhone("5555");
        em.getTransaction().commit();


    }

    private static void readStudent() {
        System.out.println("--------- READ ");

        Profesor student = em.find(Profesor.class, 1);
        System.out.println(student);

        List from_student = em.createQuery("from Student").getResultList();
        from_student.forEach(System.out::println);
    }

    private static void createStudent() {

        System.out.println("--------- CREATE ");


        Profesor student1 = new Profesor("Pawel", "123");
        Profesor student2 = new Profesor("Pawel", "124");
        Profesor student3 = new Profesor("Kinga", "125");

        em.getTransaction().begin();
        em.persist(student1);
        em.persist(student2);
        em.persist(student3);
        em.getTransaction().commit();

    }


}
