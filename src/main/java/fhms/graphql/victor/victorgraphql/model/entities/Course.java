package fhms.graphql.victor.victorgraphql.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Course {

    @Id
    @Column(name = "kurs_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "kurs_name", nullable = false)
    private String name;

    @Column(name = "kurs_raum", nullable = false)
    private String raum;

    @Column(name = "kurs_is_online", nullable = false)
    private boolean isOnline;

    @ManyToOne
    @JoinColumn(name = "professor_id", updatable = false)
    private Professor gelesenVon;

    public Course(String name, Professor gelesenVon, String raum, boolean isOnline) {
        this.name = name;
        this.gelesenVon = gelesenVon;
        this.raum = raum;
        this.isOnline = isOnline;
    }

    public Professor getGelesenVon() {
        return gelesenVon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Course course = (Course) o;

        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gelesenVon='" + gelesenVon.toString() + '\'' +
                '}';
    }

}