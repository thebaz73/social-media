package crossover.social.media.fe.signon.web;

import crossover.social.media.fe.signon.business.RegistrationException;
import crossover.social.media.fe.signon.business.RegistrationManager;
import crossover.social.media.fe.signon.web.domain.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RegistrationController
 * Created by bazzoni on 10/07/2015.
 */
@RestController
public class RegistrationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RegistrationManager registrationManager;

    @RequestMapping(value = "/public/user/{type}", method = RequestMethod.POST)
    public void createUser(HttpServletResponse response,
                           @PathVariable(value = "type") String type,
                           @RequestBody UserData userData) throws IOException {
        try {
            registrationManager.createUser(RegistrationManager.getUserType(type.toUpperCase()), userData);
        } catch (RegistrationException e) {
            String msg = String.format("Cannot create userData. Reason: %s", e.toString());
            logger.info(msg, e);
            response.sendError(400, msg);
        }
    }
}
