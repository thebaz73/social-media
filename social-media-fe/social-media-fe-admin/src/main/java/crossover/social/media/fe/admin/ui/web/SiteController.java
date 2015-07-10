package crossover.social.media.fe.admin.ui.web;

import crossover.social.media.domain.Company;
import crossover.social.media.domain.Site;
import crossover.social.media.fe.admin.ui.business.EditingManager;
import crossover.social.media.fe.admin.ui.web.model.SiteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SiteController
 * Created by bazzoni on 10/07/2015.
 */
@Controller
public class SiteController extends EditableController<SiteModel> {

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

    /**
     * Returns the injected editing manager
     *
     * @return editing manager
     */
    @Override
    protected EditingManager<SiteModel> getManager() {
        return null;
    }

    /**
     * Shows base page default HTTP GET view
     *
     * @param model map model
     * @return view
     */
    @Override
    @RequestMapping(value = {"/sites"}, method = RequestMethod.GET)
    public String show(ModelMap model) {
        model.put("siteModel", new SiteModel());
        model.put("mode", "add");
        return "sites";
    }

    /**
     * Called when HTTP POST is called, view model is added into call body, executes an object creation
     *
     * @param response      http response
     * @param viewModel     view model
     * @param bindingResult form binding results
     * @param model         map model
     * @return success or error view
     * @throws IOException if unhandled error
     */
    @Override
    @RequestMapping(value = {"/sites"}, method = RequestMethod.POST)
    public String post(HttpServletResponse response, @ModelAttribute("siteModel") SiteModel viewModel,
                       final BindingResult bindingResult, final ModelMap model) throws IOException {
        return addModel(response, bindingResult, model, viewModel, "redirect:/sites", "sites");
    }

    /**
     * Enters edit mode by setting a value in map model and retrieving view model to be edited by id
     *
     * @param id    entity model id
     * @param model map model
     * @return base view
     */
    @Override
    @RequestMapping(value = {"/sites/{id}"}, method = RequestMethod.GET)
    public String editMode(@PathVariable("id") String id, ModelMap model) {
        //TODO Find object by Id and enter edit mode
        //model.put("siteModel", siteModel);
        model.put("mode", "edit");
        return "sites";
    }


    /**
     * Called when HTTP PUT is called, view model is added into call body, executes an object editing
     *
     * @param response      http response
     * @param viewModel     view model
     * @param bindingResult form binding results
     * @param model         map model
     * @return success or error view
     * @throws IOException if unhandled error
     */
    @Override
    @RequestMapping(value = {"/sites"}, method = RequestMethod.PUT)
    public String put(HttpServletResponse response, @ModelAttribute("siteModel") SiteModel viewModel,
                      final BindingResult bindingResult, final ModelMap model) throws IOException {
        return editModel(response, bindingResult, model, viewModel, "redirect:/sites", "sites");
    }


    /**
     * Deletes retrieving entity to be deleted by id
     *
     * @param id entity model id
     * @return redirects to show base view
     */
    @Override
    @RequestMapping(value = {"/sites/{id}"}, method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id) {
        return "redirect:/sites";
    }
}
