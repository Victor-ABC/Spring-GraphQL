package fhms.graphql.victor.victorgraphql.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "student_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "student_first_name", nullable = false)
    private String firstName;

    @Column(name = "student_last_name", nullable = false)
    private String lastName;
    @Column(name = "student_matr_nr", nullable = false)
    private String matrNr;
    @Column(name = "student_semester", nullable = false)
    private int semester;

    public Student(String firstName, String lastName, String matrNr, int semester) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.matrNr = matrNr;
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;

        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", matrNr='" + matrNr + '\'' +
                '}';
    }

}
