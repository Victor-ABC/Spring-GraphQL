package fhms.graphql.victor.victorgraphql;


import fhms.graphql.victor.victorgraphql.inbound.exception.ErrorAdapter;
import fhms.graphql.victor.victorgraphql.inbound.resolver.CourseResolver;
import fhms.graphql.victor.victorgraphql.inbound.resolver.Mutation;
import fhms.graphql.victor.victorgraphql.inbound.resolver.Query;
import fhms.graphql.victor.victorgraphql.inbound.resolver.StudentResolver;
import fhms.graphql.victor.victorgraphql.model.entities.Course;
import fhms.graphql.victor.victorgraphql.model.entities.Professor;
import fhms.graphql.victor.victorgraphql.model.entities.Student;
import fhms.graphql.victor.victorgraphql.model.entities.StudentInCourse;
import fhms.graphql.victor.victorgraphql.model.repository.CourseRepository;
import fhms.graphql.victor.victorgraphql.model.repository.ProfessorRepository;
import fhms.graphql.victor.victorgraphql.model.repository.StudentInCourseRepository;
import fhms.graphql.victor.victorgraphql.model.repository.StudentRepository;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EAA_GraphQlDemoApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EAA_GraphQlDemoApp.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(ErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public CourseResolver kursResolver(StudentInCourseRepository studentInCourseRepository,
            ProfessorRepository professorRepository) {
        return new CourseResolver(studentInCourseRepository, professorRepository);
    }

    @Bean
    public StudentResolver studentResolver(StudentInCourseRepository studentInCourseRepository) {
        return new StudentResolver(studentInCourseRepository);
    }

    @Bean
    public Query query(StudentRepository studentRepository, CourseRepository courseRepository,
            ProfessorRepository professorRepository) {
        return new Query(studentRepository, courseRepository, professorRepository);
    }

    @Bean
    public Mutation mutation(StudentRepository studentRepository, ProfessorRepository professorRepository,
            CourseRepository courseRepository) {
        return new Mutation(studentRepository, professorRepository, courseRepository);
    }

    @Bean
    /*
    Test data for H2 DataSource
     */
    public CommandLineRunner demo(StudentRepository studentRepository,
            StudentInCourseRepository studentInCourseRepository, CourseRepository courseRepository,
            ProfessorRepository professorRepository) {
        return (args) -> {
            //###Studenten###
            Student s1 = new Student("Tim", "Kress", "1234", 1);
            Student s2 = new Student("Simon", "Weiß", "1235", 4);
            Student s3 = new Student("Kevin", "Bing", "1236", 3);
            studentRepository.saveAll(Arrays.asList(s1, s2, s3));
            //###Professoren###
            Professor p1 = new Professor("Sebastian", "Thoene", "Prof. Dr.");
            Professor p2 = new Professor("Tim", "Humernbrum", "Prof. Dr.");
            professorRepository.saveAll(Arrays.asList(p1, p2));
            //###Kurse###
            Course k1 = new Course("EAA", p1, "D225", false);
            Course k2 = new Course("BigData", p2, "D220", false);
            courseRepository.saveAll(Arrays.asList(k1, k2));
            //###Wer höhrt was?###
            StudentInCourse sk1 = new StudentInCourse(s1, k1);//tim hört EAA
            StudentInCourse sk2 = new StudentInCourse(s1, k2);//tim hört BigData
            StudentInCourse sk3 = new StudentInCourse(s2, k1);//simon hört nur EAA
            StudentInCourse sk4 = new StudentInCourse(s3, k2);//kevin hört nur BigData
            studentInCourseRepository.saveAll(Arrays.asList(sk1, sk2, sk3, sk4));
        };
    }
}
