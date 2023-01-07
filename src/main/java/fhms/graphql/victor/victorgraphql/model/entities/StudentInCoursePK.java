package fhms.graphql.victor.victorgraphql.model.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Victor Corbet
 * this class solves the problem, that the seceond generic argument of
 * crudRepository only takes one class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class StudentInCoursePK implements Serializable {

    private long student; //user-id
    private long course; //unique course id
}
