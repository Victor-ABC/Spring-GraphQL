package fhms.graphql.victor.victorgraphql.inbound.resolver;


import fhms.graphql.victor.victorgraphql.model.entities.Course;
import fhms.graphql.victor.victorgraphql.model.entities.Professor;
import fhms.graphql.victor.victorgraphql.model.entities.Student;
import fhms.graphql.victor.victorgraphql.model.entities.StudentInCourse;
import fhms.graphql.victor.victorgraphql.model.repository.ProfessorRepository;
import fhms.graphql.victor.victorgraphql.model.repository.StudentInCourseRepository;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CourseResolver implements GraphQLResolver<Course> {

    private final StudentInCourseRepository studentInCourseRepository;
    private final ProfessorRepository professorRepository;

    public CourseResolver(StudentInCourseRepository studentInCourseRepository,
            ProfessorRepository professorRepository) {
        this.studentInCourseRepository = studentInCourseRepository;
        this.professorRepository = professorRepository;
    }

    public List<Student> getStudents(Course course) {
        List<StudentInCourse> studentInKurs = this.studentInCourseRepository.findByCourse_Id(course.getId());
        return studentInKurs.stream().map(StudentInCourse::getStudent).collect(Collectors.toList());
    }

    public Professor getGelesenVon(Course course) {
        Professor p = professorRepository.findById(course.getGelesenVon().getId()).orElseThrow(null);
        log.info(p.toString());
        return p;
    }
}