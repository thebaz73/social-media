package crossover.social.media.fe.ui.data;

import crossover.social.media.domain.CustomerData;
import crossover.social.media.domain.SocialContent;
import crossover.social.media.fe.ui.data.domain.SearchDocument;
import crossover.social.media.fe.ui.data.domain.SearchList;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * CacheCustomerDataRepository
 * Created by bazzoni on 11/07/2015.
 */
@Component
public class CacheCustomerDataRepository extends AbstractConfigurableRepository {

    /**
     * Initializes all CacheRepository with Web services uri
     */
    @Override
    public void initialize() {
        hostname = properties.getProperty("search.hostname", "localhost");
        port = Integer.parseInt(properties.getProperty("search.port", "9000"));
        prepareHttpClient();
    }

    public List<CustomerData> searchCustomerData(String term) {
        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);

        HttpEntity<SocialContent> requestEntity = new HttpEntity<>(headers);
        // Pass the new person and header
        ResponseEntity<SearchList> entity = template.exchange("http://" + hostname + ":" + port + "/api/search/customer?q=" + term, HttpMethod.GET, requestEntity, SearchList.class);
        final SearchList searchList = entity.getBody();
        List<CustomerData> dataList = new ArrayList<>();

        int i = 0;
        for (SearchDocument searchDocument : searchList) {
            CustomerData customerData = new CustomerData(searchDocument.getId(), "Cusotmer0" + (i++), searchDocument.getContent(), "");
            dataList.add(customerData);
        }

        return dataList;
    }

    class SocialContentList extends ArrayList<SocialContent> {
    }
}
