package pl.kurs.advanced.functional.domain;

import java.util.Objects;

//immutable-niezmienna
final public class Indeks {

    private String indexNumber;

    public Indeks(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    @Override
    public String toString() {
        return "Indeks{" +
                "indexNumber='" + indexNumber + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indeks indeks = (Indeks) o;
        return indexNumber.equals(indeks.indexNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexNumber);
    }
}
