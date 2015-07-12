package crossover.social.media.fe.ws.service;

import crossover.social.media.domain.*;
import crossover.social.media.repository.*;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Date;
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
    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private ContentRepository contentRepository;

    private Address[] addresses = {new Address(null, "5123, 5th Avenue", "NYC", "00001", "US"),
            new Address(null, "12343 Las vegas Boulevard", "LAV", "00002", "US"),
            new Address(null, "1967 Melrose Place", "LAX", "00004", "US"),
            new Address(null, "5123, 5th Avenue", "Pasadena", "00123", "US")};

    /**
     * Converts a generic string to an url compliant string
     *
     * @param string generic string
     * @return pretty uri
     */
    public static String toPrettyURL(String string) {
        return Normalizer.normalize(string.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{Alnum}]+", "_");
    }

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

    public void addBlog() {
        final User manager = findByUsername("manager").get(0);
        final Site site = siteRepository.save(new Site("Acme Blog", new Date(), "/blog", WorkflowType.SELF_APPROVAL_WF, CommentApprovalMode.SELF_APPROVAL, manager));
        for (int i = 0; i < 5; i++) {
            final String string = "content0" + i;
            final String summary = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
            final String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi eu sodales eros. Ut porta congue mattis. Etiam augue sem, laoreet id risus vel, maximus tincidunt odio. Phasellus convallis ante nunc, vitae tincidunt tellus commodo non. Phasellus finibus, tellus ac rhoncus interdum, risus nibh auctor enim, id lobortis erat mi quis urna. In faucibus sit amet metus non commodo. Praesent pretium, arcu sed consectetur hendrerit, enim nulla lobortis nisl, nec elementum libero est gravida neque. Vivamus porta mi augue, ut ultricies nunc elementum in. Donec lobortis ex risus, et fermentum nisl cursus sed.";
            contentRepository.save(new SocialContent(manager.getId(), site.getId(), string, string, toPrettyURL(string), new Date(), summary, content));
        }
    }
}
