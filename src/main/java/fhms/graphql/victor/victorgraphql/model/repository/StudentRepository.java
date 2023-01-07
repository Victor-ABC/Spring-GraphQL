package fhms.graphql.victor.victorgraphql.model.repository;

import fhms.graphql.victor.victorgraphql.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}