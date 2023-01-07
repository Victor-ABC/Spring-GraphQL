package fhms.graphql.victor.victorgraphql.inbound.resolver;

import fhms.graphql.victor.victorgraphql.model.entities.Course;
import fhms.graphql.victor.victorgraphql.model.entities.Professor;
import fhms.graphql.victor.victorgraphql.model.entities.Student;
import fhms.graphql.victor.victorgraphql.model.repository.CourseRepository;
import fhms.graphql.victor.victorgraphql.model.repository.ProfessorRepository;
import fhms.graphql.victor.victorgraphql.model.repository.StudentRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/*
    Handels Read Operations (Query = Abfrage)
 */
@Slf4j
public class Query implements GraphQLQueryResolver {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    public Query(StudentRepository studentRepository, CourseRepository courseRepository,
            ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    public Iterable<Student> findAllStudents(Integer first, Integer last, String fullName) {
        if (first != null && first != 0) {
            return studentRepository.findAll().stream().limit(first).collect(Collectors.toList());
        }
        if (last != null && last != 0) {
            List<Student> all = studentRepository.findAll();
            return all.subList(all.size() - last, all.size());
        }
        if (fullName != null && fullName.equals("") == false) {
            return studentRepository.findAll().stream()
                    .filter(e -> (e.getFirstName() + " " + e.getLastName()).equals(fullName)).collect(
                            Collectors.toList());
        }
        return studentRepository.findAll();
    }

    public long countStudents() {
        return studentRepository.count();
    }

    public Iterable<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public long countCourses() {
        return courseRepository.count();
    }

    public List<Professor> findAllProfessors() {
        return professorRepository.findAll();
    }

    public long countProfessors() {
        return professorRepository.count();
    }
}
