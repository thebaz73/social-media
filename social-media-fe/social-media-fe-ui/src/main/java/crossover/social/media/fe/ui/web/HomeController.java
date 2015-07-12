package crossover.social.media.fe.ui.web;

import crossover.social.media.domain.Company;
import crossover.social.media.domain.Person;
import crossover.social.media.domain.User;
import crossover.social.media.fe.ui.data.CacheContentRepository;
import crossover.social.media.fe.ui.data.CacheUserRepository;
import crossover.social.media.fe.ui.web.model.ContentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;

/**
 * HomeController
 * Created by bazzoni on 11/07/2015.
 */
@Controller
public class HomeController {
    @Autowired
    private CacheContentRepository cacheContentRepository;
    @Autowired
    private CacheUserRepository cacheUserRepository;

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

    @RequestMapping({"/"})
    public String home() {
        return "redirect:/home";
    }

    @RequestMapping({"/home"})
    public String dashboard() {
        return "index";
    }

    @RequestMapping(value = {"/home/contents"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ContentData> contents(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "3") int size) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return cacheContentRepository.findLastContents(page, size);
    }
}
