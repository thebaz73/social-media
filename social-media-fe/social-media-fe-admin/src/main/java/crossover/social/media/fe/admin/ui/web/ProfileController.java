package crossover.social.media.fe.admin.ui.web;

import crossover.social.media.fe.admin.ui.web.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * ProfileController
 * Created by bazzoni on 10/07/2015.
 */
@Controller
public class ProfileController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping({"/profile"})
    public String userProfile(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException {
        //TODO GET Logged User data
        return "profile";
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.PUT)
    public String editUserProfile(HttpServletRequest request, HttpServletResponse response, UserModel editUser, Map<String, Object> model) throws IOException {
        //TODO Save logged user data
        return "profile";
    }
}
