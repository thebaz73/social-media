package crossover.social.media.fe.ui.web;

import crossover.social.media.domain.Company;
import crossover.social.media.domain.CustomerData;
import crossover.social.media.domain.Person;
import crossover.social.media.domain.User;
import crossover.social.media.fe.ui.data.CacheCustomerDataRepository;
import crossover.social.media.fe.ui.data.CacheUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * StreamController
 * Created by bazzoni on 12/07/2015.
 */
@Controller
@RequestMapping({"/streams"})
public class StreamController {
    @Autowired
    private CacheUserRepository cacheUserRepository;
    @Autowired
    private CacheCustomerDataRepository cacheCustomerDataRepository;

    @ModelAttribute("company")
    Company company() {
        final Company company = new Company();
        company.setName("Acme Corp.");
        return company;
    }

    @ModelAttribute("person")
    Person person(HttpServletRequest request) {
        final List<User> byUsername = cacheUserRepository.findByUsername(request.getRemoteUser());
        if (!byUsername.isEmpty()) {
            return cacheUserRepository.findPersonByUserId(byUsername.get(0).getId());
        }

        return new Person();
    }

    @RequestMapping({"/customer"})
    public String customer() {
        return "customer";
    }

    @RequestMapping(value = {"/customer/search"}, params = "q")
    public String searchCustomer(@RequestParam("q") String query, ModelMap model) {
        List<CustomerData> customers = cacheCustomerDataRepository.searchCustomerData(query);
        model.addAttribute("allCustomers", customers);
        return "customer";
    }

}
