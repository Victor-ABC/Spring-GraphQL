package fhms.graphql.victor.victorgraphql.model.repository;


import fhms.graphql.victor.victorgraphql.model.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}