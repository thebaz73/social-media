package crossover.social.media.repository;

import crossover.social.media.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * EmployeeRepository
 * Created by bazzoni on 09/07/2015.
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
