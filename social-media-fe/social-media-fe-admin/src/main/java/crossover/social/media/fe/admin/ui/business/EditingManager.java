package crossover.social.media.fe.admin.ui.business;

import java.io.Serializable;

/**
 * EditingManager
 * Created by bazzoni on 10/07/2015.
 */
public interface EditingManager<T extends Serializable> {
    /**
     * Converts ViewModel to model entity and saves it using a repository
     *
     * @param viewModel view model
     */
    void add(T viewModel);

    /**
     * Converts ViewModel to model entity and merge it and saves using a repository
     *
     * @param viewModel view model
     */
    void edit(T viewModel);
}
