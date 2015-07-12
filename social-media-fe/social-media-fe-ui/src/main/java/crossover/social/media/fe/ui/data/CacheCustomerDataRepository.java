package crossover.social.media.fe.ui.data;

import crossover.social.media.domain.CustomerData;
import crossover.social.media.domain.SocialContent;
import org.springframework.data.domain.Page;
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
        ResponseEntity<Page> entity = template.exchange("http://" + hostname + ":" + port + "/socialContents?page=0&size=3", HttpMethod.GET, requestEntity, Page.class);

        List<CustomerData> dataList = new ArrayList<>();

        entity.getBody().getContent().stream().filter(o -> o instanceof CustomerData).forEach(o -> {
            CustomerData customerData = (CustomerData) o;
            dataList.add(customerData);
        });

        return dataList;
    }

    class SocialContentList extends ArrayList<SocialContent> {
    }
}
