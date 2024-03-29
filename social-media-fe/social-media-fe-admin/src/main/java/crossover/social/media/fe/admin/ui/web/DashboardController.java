package crossover.social.media.fe.admin.ui.web;

import crossover.social.media.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * DashboardController
 * Created by bazzoni on 10/07/2015.
 */
@Controller
public class DashboardController {
    @ModelAttribute("company")
    Company company() {
        final Company company = new Company();
        company.setName("Acme Corp.");
        return company;
    }

    @ModelAttribute("allSites")
    List<Site> allSites() {
        return new ArrayList<>();
    }

    @ModelAttribute("allAssets")
    List<SocialAsset> allAssets() {
        return new ArrayList<>();
    }

    @ModelAttribute("allOffices")
    List<Office> allOffices() {
        return new ArrayList<>();
    }

    @ModelAttribute("allUsers")
    List<User> allUsers() {
        return new ArrayList<>();
    }

    @RequestMapping({"/"})
    public String home() {
        return "redirect:/home";
    }

    @RequestMapping({"/home"})
    public String dashboard() {
        return "index";
    }
}
