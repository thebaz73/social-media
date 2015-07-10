package crossover.social.media.fe.search.web;

import crossover.social.media.plugin.search.SearchDocument;
import crossover.social.media.plugin.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SearchController
 * Created by bazzoni on 10/07/2015.
 */
@RestController
@RequestMapping(value = "/api")
public class SearchController {
    @Autowired
    private PluginService pluginService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Secured({"ROLE_MANAGER"})
    @RequestMapping(value = "/search/{siteId}", params = {"q"}, method = RequestMethod.GET)
    HttpEntity<List<? extends SearchDocument>> contents(@PathVariable("siteId") String siteId,
                                                        @RequestParam("q") String searchTerms) {

        List<? extends SearchDocument> contents = pluginService.getSearchPlugin().search(siteId, searchTerms);
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }
}
