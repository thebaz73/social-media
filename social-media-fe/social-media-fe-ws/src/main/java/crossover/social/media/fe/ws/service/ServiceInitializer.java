package crossover.social.media.fe.ws.service;

import crossover.social.media.domain.*;
import crossover.social.media.repository.*;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * InitializeService
 * Created by bazzoni
 */
@Service
public class ServiceInitializer {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private OfficeRepository officeRepository;
    private Address[] addresses = {new Address(null, "5123, 5th Avenue", "NYC", "00001", "US"),
            new Address(null, "12343 Las vegas Boulevard", "LAV", "00002", "US"),
            new Address(null, "1967 Melrose Place", "LAX", "00004", "US"),
            new Address(null, "5123, 5th Avenue", "Pasadena", "00123", "US")};

    public List<Role> findByRole(RoleName role) {
        return roleRepository.findByRole(role);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void addUser(String username, String password, RoleName roleName) {
        final List<User> byUsername = findByUsername(username);
        if (byUsername.isEmpty()) {
            User admin = new User(username, password, Arrays.asList(findByRole(RoleName.ROLE_USER).get(0), findByRole(roleName).get(0)));
            saveUser(admin);
            Person person = new Person(null, username, "", admin,
                    addressRepository.save(addresses[RandomUtils.nextInt(0, 4)]),
                    employeeRepository.save(new Employee(null, UUID.randomUUID().toString(), officeRepository.findAll().get(RandomUtils.nextInt(0, 3)))));
            person.getEmails().add(new Email(username + "@acme.com", true));
            personRepository.save(person);
        }
    }

    public void addCompany() {
        addressRepository.deleteAll();
        officeRepository.deleteAll();
        companyRepository.deleteAll();
        final Company company = new Company();
        company.setName("Acme Corp.");

        company.getOffices().add(officeRepository.save(new Office(null, "NYC", "IT Dept", addressRepository.save(addresses[RandomUtils.nextInt(0, 4)]))));
        company.getOffices().add(officeRepository.save(new Office(null, "LAV", "MKTG Dept", addressRepository.save(addresses[RandomUtils.nextInt(0, 4)]))));
        company.getOffices().add(officeRepository.save(new Office(null, "LAX", "Sales Dept", addressRepository.save(addresses[RandomUtils.nextInt(0, 4)]))));
        companyRepository.save(company);
    }
}
