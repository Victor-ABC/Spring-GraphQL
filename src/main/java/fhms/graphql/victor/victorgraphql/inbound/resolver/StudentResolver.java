package fhms.graphql.victor.victorgraphql.inbound.resolver;


import fhms.graphql.victor.victorgraphql.model.entities.Course;
import fhms.graphql.victor.victorgraphql.model.entities.Student;
import fhms.graphql.victor.victorgraphql.model.entities.StudentInCourse;
import fhms.graphql.victor.victorgraphql.model.repository.StudentInCourseRepository;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentResolver implements GraphQLResolver<Student> {

    private final StudentInCourseRepository studentInCourseRepository;

    public StudentResolver(StudentInCourseRepository studentInCourseRepository) {
        this.studentInCourseRepository = studentInCourseRepository;
    }

    public List<Course> getCourses(Student student) {
        List<StudentInCourse> studentInKurs = this.studentInCourseRepository.findByStudentId(student.getId());
        return studentInKurs.stream().map(StudentInCourse::getCourse).collect(Collectors.toList());
    }
}