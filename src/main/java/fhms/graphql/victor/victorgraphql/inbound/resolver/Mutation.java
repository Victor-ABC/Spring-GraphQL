package fhms.graphql.victor.victorgraphql.inbound.resolver;


import fhms.graphql.victor.victorgraphql.model.entities.Course;
import fhms.graphql.victor.victorgraphql.model.entities.Professor;
import fhms.graphql.victor.victorgraphql.model.entities.Student;
import fhms.graphql.victor.victorgraphql.model.repository.CourseRepository;
import fhms.graphql.victor.victorgraphql.model.repository.ProfessorRepository;
import fhms.graphql.victor.victorgraphql.model.repository.StudentRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;

/*
    Handels Write Operations (Mutation = Veränderung)
 */
@Slf4j
public class Mutation implements GraphQLMutationResolver {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;

    public Mutation(StudentRepository studentRepository, ProfessorRepository professorRepository,
            CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
    }

    /*
    newStudent(firstName: String!, lastName: String!, matrNr: String!, semester: Int!) : Student!
     */

    public Student newStudent(String firstName, String lastName, String matrNr, int semester) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMatrNr(matrNr);
        student.setSemester(semester);
        return studentRepository.save(student);
    }

    /*
     newProfessor(firstName: String!, lastName: String!, titel: String!) : Professor!
     */
    public Professor newProfessor(String firstName, String lastName, String titel) {
        Professor professor = new Professor();
        professor.setFirstName(firstName);
        professor.setLastName(lastName);
        professor.setTitel(titel);
        return this.professorRepository.save(professor);
    }

    /*
    newCourse(name: String!, gelesenVon: ID!) : Course!
     */
    public Course newCourse(String name, long gelesenVon) {
        Course course = new Course();
        course.setName(name);
        course.setGelesenVon(professorRepository.getById(gelesenVon));
        return courseRepository.save(course);
    }
}
