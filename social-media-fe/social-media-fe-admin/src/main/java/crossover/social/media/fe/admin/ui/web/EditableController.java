package crossover.social.media.fe.admin.ui.web;

import crossover.social.media.fe.admin.ui.business.EditingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * EditableController
 * Created by bazzoni on 10/07/2015.
 */
public abstract class EditableController<T extends Serializable> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Returns the injected editing manager
     *
     * @return editing manager
     */
    protected abstract EditingManager<T> getManager();

    /**
     * Shows base page default HTTP GET view
     *
     * @param model map model
     * @return view
     */
    abstract String show(ModelMap model);

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
    abstract String post(HttpServletResponse response, T viewModel,
                         BindingResult bindingResult, ModelMap model) throws IOException;

    /**
     * Enters edit mode by setting a value in map model and retrieving view model to be edited by id
     *
     * @param id    entity model id
     * @param model map model
     * @return base view
     */
    abstract String editMode(String id, ModelMap model);

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
    abstract String put(HttpServletResponse response, T viewModel,
                        BindingResult bindingResult, ModelMap model) throws IOException;

    /**
     * Deletes retrieving entity to be deleted by id
     *
     * @param id entity model id
     * @return redirects to show base view
     */
    abstract String delete(String id);

    protected String addModel(HttpServletResponse response, BindingResult bindingResult, ModelMap model, T viewModel, String successView, String errorView) throws IOException {
        if (bindingResult.hasErrors()) {
            return errorView;
        }
        try {
            getManager().add(viewModel);
            model.clear();
        } catch (RuntimeException e) {
            String msg = String.format("Cannot author contents. Reason: %s", e.getMessage());
            logger.info(msg, e);
            response.sendError(400, msg);
        }
        return successView;
    }

    protected String editModel(HttpServletResponse response, BindingResult bindingResult, ModelMap model, T viewModel, String successView, String errorView) throws IOException {
        if (bindingResult.hasErrors()) {
            return errorView;
        }
        try {
            getManager().edit(viewModel);
            model.clear();
        } catch (RuntimeException e) {
            String msg = String.format("Cannot author contents. Reason: %s", e.getMessage());
            logger.info(msg, e);
            response.sendError(400, msg);
        }
        return successView;
    }
}
