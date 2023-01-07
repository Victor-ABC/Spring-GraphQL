package fhms.graphql.victor.victorgraphql.model.repository;

import fhms.graphql.victor.victorgraphql.model.entities.StudentInCourse;
import fhms.graphql.victor.victorgraphql.model.entities.StudentInCoursePK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInCourseRepository extends JpaRepository<StudentInCourse, StudentInCoursePK> {

    List<StudentInCourse> findByStudentId(Long student_id);

    List<StudentInCourse> findByCourse_Id(Long kurs_id);
}