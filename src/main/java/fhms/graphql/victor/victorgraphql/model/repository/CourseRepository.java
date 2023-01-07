package fhms.graphql.victor.victorgraphql.model.repository;

import fhms.graphql.victor.victorgraphql.model.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}