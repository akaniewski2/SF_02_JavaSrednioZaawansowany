package pl.kurs.advanced.functional.domain;

import javax.xml.bind.SchemaOutputResolver;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class App {

    public static void main(String[] args) {

    //    przyklad_1();
       // przyklad_2_predicate();
       // przyklad_3_consumer();
        //methodReferences();

//        streamAPI();
        streamAPI2();


       // streamy();


    }

    private static void streamAPI() {
        // streamy generowanie
        Stream.of("A","B","C","D").forEach(System.out::println);
        List<Student> students = createData();
        Stream<Student> stream =students.stream();

        // streamy generowanie 2
        Supplier<List<Student>> supplyPredefinedStudents = App::createData;
        Stream<List<Student>> generateStudents = Stream.generate(supplyPredefinedStudents);

        Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);
        System.out.println("----------------");
        Stream.iterate(0,i->i+2).limit(20).forEach(System.out::println);
        System.out.println("----------------");
        IntStream.rangeClosed(5, 100).filter(i -> i % 2 == 0).forEach(System.out::println);

    }




    private static void streamAPI2(){
        //stream API
        Predicate<Student> isOver30 = s->s.getAge()>30;
        Supplier<List<Student>> supplyPredefinedStudents =()->createData2();
        Consumer<String> print = System.out::println;
        Function<Student,String> getStudentName = Student::getName;
        Function<Student,String> getStudentNameAge = s->s.getName() + " - "+s.getAge() ;


        List<Student> students = supplyPredefinedStudents.get();

        students.stream().filter(student->student.getAge()>30).map(student -> student.getName() +"  "+ student.getAge()).forEach(print);
        //students.stream().filter(student->student.getAge()>30).map(Student::getName).forEach(print);

        List<Student> data = createData();
        for (Student s: data) {
              if (s.getAge()>30) {
                String name = s.getName();
                System.out.println(name);
            }

        }

        //--------------------
        // filtry
        System.out.println("------- filtry");

        createData().stream().filter(new Predicate<Student>() {
                                         @Override
                                         public boolean test(Student student) {
                                             return student.getName().equals("Jan");
                                         }
                                     }).forEach(System.out::println);


        System.out.println("---");
        createDataStream()
                .filter(s -> s.getName().equals("Jan"))
                .filter(isOver30)
             //   .map(getStudentNameAge)
                .map(  s->s.getName() + " - "+ s.getAge() + " - " +  s.getIndex().filter(i->s.getIndex().isPresent()).map(i -> i.getIndexNumber()))
                .forEach(System.out::println);



        System.out.println("---");
        createDataStream()
                .filter(isOver30)
                .map(getStudentName)
                .filter(name ->name.startsWith("Bill"))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        //-------------------------

        System.out.println("---");

        createDataStream()
                .map(s->s.getIndex())
                .filter(optionalInedex -> optionalInedex.isPresent())
                .map(optionalInedex->optionalInedex.get())
                .map(index -> index.getIndexNumber())
                .forEach(idx -> System.out.println("Indeks nr: "+idx));
                ;

        System.out.println("---");

        createDataStream()
                .map(Student::getIndex)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Indeks::getIndexNumber)
                .forEach(idx -> System.out.println("Indeks nr: "+idx));
        ;

        //---------------------------
        System.out.println("--------- forEach");

        StringBuilder sb= new StringBuilder("Lista indeksów: ");

        Set<StringBuilder> collect = createDataStream()
                .map(Student::getIndex)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Indeks::getIndexNumber)
                .map(s -> sb.append(s + ","))
                .collect(Collectors.toSet());
        //.forEach(s -> System.out.println(sb.append(s+","))
//.forEach(s -> sb.append(s+","))


        collect.stream().forEach(System.out::println);

        System.out.println("------ FindFirst, AnyMatch, AllMatch");
        // FindFirst, AnyMatch, AllMatch

        createDataStream()
                .filter(isOver30)
                .map(Student::getName)
                .findFirst().ifPresent(s-> System.out.println("Mamy studenta po 30" ));


        Optional<String> first = createDataStream()
                .filter(isOver30)
                .map(Student::getName)
                .findFirst();

         first.stream().forEach(System.out::println);

        // metody typu reduce sprowadzaja wartosc do jednego wyniku
        boolean jan = createDataStream().map(Student::getName).anyMatch(n -> n.equals("Jan"));
        boolean b = createDataStream().map(Student::getAge).allMatch(n -> n > 10);
        boolean b2 = createDataStream().map(Student::getAge).noneMatch(n -> n <65 ); //true jesli zaden element nie przekroczy 65 lat

        System.out.println(jan);
        System.out.println(b);
        System.out.println(b2);

        System.out.println("-----sumOfRandomDoubles");
        Double sumOfRandomDoubles = Stream.generate(Math::random).limit(10).reduce(0.0, new BinaryOperator<Double>() {
            @Override
            public Double apply(Double aDouble, Double aDouble2) {
                return aDouble + aDouble2;
            }
        });

        Double sumOfRandomDoubles2 = Stream.generate(Math::random).limit(10).reduce(0.0,Double::sum);


        System.out.println(sumOfRandomDoubles);
        System.out.println(sumOfRandomDoubles2);

        System.out.println("--najstarszy student");

        //szukamy najstarszego studenta
        //(x,y) x - jest wynikiem redukcji, y obencym elementem
        //(x, y) -> (poprzeni max) x >  (obecny max )y ? x : y
        Optional<Integer> maxStudentAge = createDataStream().map(Student::getAge).reduce((x, y) -> x > y ? x : y);
        Optional<Integer> maxStudentAge2 = createDataStream().map(Student::getAge).reduce(Integer::max);


        maxStudentAge.ifPresent(System.out::println);

        System.out.println("-------- collect ");
        //collect metoda terminalna

        List<Integer> collect1 = createDataStream().map(Student::getAge).collect(Collectors.toList());
        String collect2 = createDataStream().map(Student::getAge).map(s->s.toString()).collect(Collectors.joining(","));

        System.out.println(collect2);

        System.out.println("---------------------------");

        Map<Integer, List<Student>> studentsByAge = createDataStream().collect(groupingBy(Student::getAge));

        studentsByAge.forEach(new BiConsumer<Integer, List<Student>>() {
            @Override
            public void accept(Integer integer, List<Student> students) {
                System.out.println(integer);
                students.stream().map(Student::getName).forEach(System.out::println);
            }
        });


        System.out.println("--------------------------- ograniczenie stramow");

        createDataStream().limit(3).map(Student::getName).forEach(System.out::println);
        System.out.println("-------------");
        createDataStream().skip(3).map(Student::getName).forEach(System.out::println);

        System.out.println("-- sorted name");
        createDataStream().sorted().map(Student::getName).forEach(System.out::println);

        System.out.println("-- sorted age");
        createDataStream().sorted(
                new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return Integer.compare(o1.getAge(),o2.getAge());
                    }
                }
        ).map(Student::getName).forEach(System.out::println);

        System.out.println("-- sorted age 2");
        long count = createDataStream().sorted(Comparator.comparing(Student::getAge)).map(Student::getName).count();//.forEach(System.out::println);

        System.out.println(count);

        //limit
        //skip
        //distint
        //sorted
        //count

        IntStream intStream = createDataStream().map(Student::getAge).mapToInt(new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value.intValue();
            }
        });

//        intStream.map(new IntUnaryOperator() {
//            @Override
//            public int applyAsInt(int operand) {
//                return 0;
//            }
//        });
        System.out.println("---");
        intStream.sorted().forEach(System.out::println);
    }


    private static Stream<Student> createDataStream() {

        Student student1 = new Student("Adam", 31, "456445");
        Student student2 = new Student("Pawel",29,"8970");
        Student student3 = new Student("Jan",75,"45");
        Student student4 = new Student("Jan2",39,"45");
        Student student5 = new Student("Hanna",48,"45f");
        Student student6 = new Student("Hanna2",48,"45f");

        return Stream.of(student1,student2,student3,student4,student5,student6);

    }

    private static void methodReferences(){
        //
        // method references

        Consumer<String> stringConsumer = (String x) -> App.testme(x);
        Consumer<String> stringConsumer2 =  x -> App.testme(x);
        Consumer<String> stringConsumer3 = App::testme;


        BiFunction<Student,String,Student> changeIndex = (student, indexNumber) -> student.changeIndexNumber(indexNumber);
//        BiFunction<Student,String,Student> changeIndex2 = (student, indexNumber) -> Student::changeIndexNumber;



    }


    private static void OpctionalExample () {
        Supplier<List<Student>> supplyPredefinedStudents =()->createData2();
        Student student = supplyPredefinedStudents.get().get(0);
        Optional<Indeks> index = student.getIndex();

        // 1
        if (index.isPresent()) {
            System.out.println(index.get().getIndexNumber());

        }

        // 2 - z comsumerem - na podsatwie obiektu cos zrobi ale nic nie zwroci
        index.ifPresent(idx ->  System.out.println(idx.getIndexNumber()));

        // 3 z supplyier
        // index.orElseGet()...

        //4
        if (index.isPresent()) {
            System.out.println(index.get().getIndexNumber().equals("1234"));

        }

        //5
        Optional<Indeks> indeks = index.filter(i -> i.getIndexNumber().equals("1234")); //...->  .ifPresent();

        //6
        indeks.map(i -> i.getIndexNumber()).filter(idxNum  -> idxNum.equals("213")).ifPresent(idxNum-> System.out.println(idxNum));
        //indeks.map(i -> i.getIndexNumber()).filter(idxNum  -> idxNum.equals("213")).ifPresent(idxNum-> ));



    }

    private static void przyklad_3_consumer() {
        //consumer
        //supplier
        //function




        List<Student> students = createData();
        System.out.println(students);
        Predicate<Student> isOver30= student -> student.getAge()>30;

//        Predicate<Student> isOver30= student -> student.getAge()>30;

        //# todo: istnieją tez odpowiedniki dla typow prymitywnych:    Long/double/ predicate,consumer,Function itp

        IntPredicate intPredicate =new IntPredicate() {
            @Override
            public boolean test(int value) {
                return false;
            }
        };


        ToIntFunction<Student> getAge = new ToIntFunction<Student>() {
            @Override
            public int applyAsInt(Student value) {
                return value.getAge();
            }
        };



//        Consumer<Student> printStudentName =new Consumer<Student>() {
//            @Override
//            public void accept(Student student) {
//                System.out.println(student.getName());
//            }
//        };

        Consumer<Student> printStudentName =student -> System.out.println(student.getName());
        Consumer<Student> printStudentNameUppercase =student -> System.out.println(student.getName().toUpperCase());
        Consumer<Student> studentConsumer = printStudentName.andThen(printStudentNameUppercase);


        List<Student> over30 = filterStudents(students, isOver30);

        filterStudents(students,isOver30);
        consumeStudents(students,printStudentName);
        System.out.println("------------------");
        consumeStudents(filterStudents(students,isOver30),printStudentName);
        System.out.println("------------------");
        consumeStudents(filterStudents(students,isOver30), studentConsumer);


        System.out.println("---- supplier ----------");

//        Supplier<Student> getStudent = new Supplier<Student>() {
//            @Override
//            public Student get() {
//                return new Student("x",32,"456654");
//            }
//        } ;


        Supplier<Student> newStudent =()->new Student("x",32,"456654");
        Supplier<List<Student>> supplyPredefinedStudents =()->createData2();

        consumeStudents(filterStudentsSupplier(supplyPredefinedStudents,isOver30),printStudentName );


        System.out.println("---- function ----------");

//        Function<Student,String> getStudentName = new Function<Student, String>() {
//            @Override
//            public String apply(Student student) {
//                return student.getName();
//            }
//        };

        Function<Student,String> getStudentName = (student) -> student.getName();
        Consumer<String> printStudentNameFunction =text -> System.out.println(text);
        consumeStudentsFunction(filterStudentsSupplier(supplyPredefinedStudents,isOver30),getStudentName,printStudentNameFunction );



    }

    private static void consumeStudents(List<Student> students ,Consumer<Student> consumer){

        for (Student s : students)
        {consumer.accept(s);  };
        };

    private static void consumeStudentsFunction(List<Student> students ,Function<Student,String> function,Consumer<String> consumer){

        for (Student s : students)
        {consumer.accept(function.apply(s));  };
    };






    private static void przyklad_2_predicate() {


        Predicate<Student> predicate = new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return false;
            }
        };

        Predicate<Student> isJan_wzor = new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getName().equals("Jan");
            }
        };

        Predicate<Student> isOver30_wzor = new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getAge()>0;
            }
        };


        Predicate<Student> isJan= student -> student.getName().equals("Jan");
        Predicate<Student> isOver30= student -> student.getAge()>0;






        List<Student> students = createData();
        System.out.println(students);




//        List<Student> jan = getJan(students);
//        List<Student> over30 = getOver30(students);

        List<Student> jan = filterStudents(students, isJan);
        List<Student> nieJan = filterStudents(students, isJan.negate());
        List<Student> over30 = filterStudents(students, isOver30);
        //List<Student> index = filterStudents(students, isOver30);

        //List<Student> and = isJan.and(isOver30);

        System.out.println(jan);
        System.out.println(over30);
        System.out.println(nieJan);
        System.out.println("--------------");
       // System.out.println(and.toString());
    }

    private static List<Student> filterStudentsSupplier (Supplier<List<Student>> supplier,Predicate<Student> predicate) {

        List<Student> result = new ArrayList<>();
        List<Student> students = supplier.get();
        for (Student s : students) {
            if (predicate.test(s))  result.add(s);
        }
        return result;
    }

    private static List<Student> filterStudents (List<Student> students,Predicate<Student> predicate) {

        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (predicate.test(s))  result.add(s);
        }
        return result;
    }



//
//    private static List<Student> getJan(List<Student> students) {
//
//        List<Student> result= new ArrayList<>();
//
//        for (Student s:students){
//            if(s.getName().equals("Jan")) {result.add(s);}
//
//        }
//       return result;
//
//    }

    private static List<Student> createData() {
        List<Student> result =new ArrayList<>();
        result.add(new Student("Adam",31,"456445"));
        result.add(new Student("Pawel",29,"8970"));
        result.add(new Student("Jan",15,"45"));
        result.add(new Student("Jan",39,"45"));
        return result;
    }


    private static List<Student> createData2() {
        List<Student> result =new ArrayList<>();
        result.add(new Student("Jacek",31,"456445"));
        result.add(new Student("Marek",29,"8970"));
        result.add(new Student("Agata",15,"45"));
        result.add(new Student("Jan",39,"20"));
        return result;
    }


    private static void testme(String x) {

    }

    private static void przyklad_1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("A");
            }
        };

        Runnable r2 = () ->   System.out.println("A");
        Runnable r3 = () ->   System.out.println("B");


        Comparable c = new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

        Comparable<String> c2 = (String o)->0;
        Comparable<String> c3 = o ->0;

        System.out.println(c3);

        Moveable m = direction -> 11;

        System.out.println(m);

//------------------------------------------
        test("a",r2);
        test("b",r3);
    }



    public static void test(String name,Runnable a) {

    }
}
