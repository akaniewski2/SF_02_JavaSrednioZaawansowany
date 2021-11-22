package pl.kurs.advanced.jpa.JPA;

public record QueryResultGroupBy(String name,long count) {

    @Override
    public String toString() {
        return "QueryResultGroupBy{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
