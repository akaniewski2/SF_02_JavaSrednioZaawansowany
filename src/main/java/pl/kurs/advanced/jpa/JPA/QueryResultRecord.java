package pl.kurs.advanced.jpa.JPA;

public record QueryResultRecord (String studentName, String indeksNumber) {

    @Override
    public String toString() {
        return "QueryResultRecord{" +
                "studentName='" + studentName + '\'' +
                ", indeksNumber='" + indeksNumber + '\'' +
                '}';
    }
}


