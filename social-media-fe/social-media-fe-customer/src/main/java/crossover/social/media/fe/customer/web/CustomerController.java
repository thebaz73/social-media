package crossover.social.media.fe.customer.web;

import crossover.social.media.domain.CustomerData;
import crossover.social.media.fe.customer.business.CustomerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * CustomerController
 * Created by bazzoni on 11/07/2015.
 */
@Controller
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerManager customerManager;

    @Secured({"ROLE_MANAGER"})
    @RequestMapping(value = {"/customer"}, method = RequestMethod.POST)
    public HttpEntity<Void> post(@RequestBody CustomerData data) {
        customerManager.createCustomerData(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
