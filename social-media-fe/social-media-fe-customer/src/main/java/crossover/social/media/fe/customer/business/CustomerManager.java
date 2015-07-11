package crossover.social.media.fe.customer.business;

import crossover.social.media.domain.Address;
import crossover.social.media.domain.Company;
import crossover.social.media.domain.CustomerData;
import crossover.social.media.domain.Office;
import crossover.social.media.repository.AddressRepository;
import crossover.social.media.repository.CompanyRepository;
import crossover.social.media.repository.CustomerDataRepository;
import crossover.social.media.repository.OfficeRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CustomerManager
 * Created by bazzoni on 11/07/2015.
 */
@Component
public class CustomerManager {
    @Autowired
    private CustomerDataRepository customerDataRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private OfficeRepository officeRepository;

    public void initialize() {
        customerDataRepository.deleteAll();
        addressRepository.deleteAll();
        companyRepository.deleteAll();
        final Company company = new Company();
        company.setName("Acme Corp.");
        company.getOffices().add(officeRepository.save(new Office(null, "NYC", "IT Dept", addressRepository.save(new Address(null, "5123, 5th Avenue", "NYC", "00001", "US")))));
        company.getOffices().add(officeRepository.save(new Office(null, "LAV", "MKTG Dept", addressRepository.save(new Address(null, "12343 Las vegas Boulevard", "LAV", "00002", "US")))));
        company.getOffices().add(officeRepository.save(new Office(null, "LAX", "Sales Dept", addressRepository.save(new Address(null, "1967 Melrose Place", "LAX", "00002", "US")))));
        companyRepository.save(company);
        for (int i = 0; i < 100; i++) {
            CustomerData data = new CustomerData();
            data.setCompanyId(company.getId());
            data.setCustomer(RandomStringUtils.randomAscii(15));
            String content = "";
            for (int j = 0; j < 20; j++) {
                content += RandomStringUtils.randomAscii(10) + " ";
            }
            data.setData(content);
            customerDataRepository.save(data);
        }
    }

    public void createCustomerData(CustomerData data) {
        customerDataRepository.save(data);
    }
}
