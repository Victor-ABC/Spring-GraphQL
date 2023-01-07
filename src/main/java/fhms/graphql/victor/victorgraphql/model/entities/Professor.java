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
public class Professor {

    @Id
    @Column(name = "professor_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "professor_first_name", nullable = false)
    private String firstName;

    @Column(name = "professor_last_name", nullable = false)
    private String lastName;

    @Column(name = "professor_titel", nullable = false)
    private String titel;

    public Professor(String firstName, String lastName, String titel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.titel = titel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Professor professor = (Professor) o;

        return id.equals(professor.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", titel='" + titel + '\'' +
                '}';
    }

}