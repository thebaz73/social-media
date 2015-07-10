package crossover.social.media.repository;

import crossover.social.media.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * EmployeeRepository
 * Created by bazzoni
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
