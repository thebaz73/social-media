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
            String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi eu sodales eros. Ut porta congue mattis. Etiam augue sem, laoreet id risus vel, maximus tincidunt odio. Phasellus convallis ante nunc, vitae tincidunt tellus commodo non. Phasellus finibus, tellus ac rhoncus interdum, risus nibh auctor enim, id lobortis erat mi quis urna. In faucibus sit amet metus non commodo. Praesent pretium, arcu sed consectetur hendrerit, enim nulla lobortis nisl, nec elementum libero est gravida neque. Vivamus porta mi augue, ut ultricies nunc elementum in. Donec lobortis ex risus, et fermentum nisl cursus sed.";
            data.setData(content);
            customerDataRepository.save(data);
        }
    }

    public void createCustomerData(CustomerData data) {
        customerDataRepository.save(data);
    }
}
