package crossover.social.media.fe.admin.ui.web;

import crossover.social.media.domain.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * SearchController
 * Created by bazzoni on 10/07/2015.
 */
@Controller
public class SearchController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ModelAttribute("company")
    Company company() {
        final Company company = new Company();
        company.setName("Acme Corp.");
        return company;
    }

    @RequestMapping(value = {"/search"}, params = {"query"}, method = RequestMethod.GET)
    public String search(HttpServletRequest request, HttpServletResponse response, ModelMap model, String query) throws IOException {
        try {
            //TODO Call remote search web service using Spring RestTemplate
            model.addAttribute("docs", Collections.emptyList());
        } catch (RuntimeException e) {
            String msg = String.format("Cannot search contents. Reason: %s", e.getMessage());
            logger.info(msg, e);
            response.sendError(400, msg);
        }
        return "search";
    }
}
