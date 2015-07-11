package crossover.social.media.fe.customer.business;

import crossover.social.media.domain.Company;
import crossover.social.media.domain.CustomerData;
import crossover.social.media.repository.CompanyRepository;
import crossover.social.media.repository.CustomerDataRepository;
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
    private CompanyRepository companyRepository;

    public void initialize() {
        customerDataRepository.deleteAll();
        final Company company = companyRepository.findAll().get(0);
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
