package crossover.social.media.fe.ui.data;

import crossover.social.media.domain.Person;
import crossover.social.media.domain.SocialContent;
import crossover.social.media.domain.User;
import crossover.social.media.fe.ui.data.domain.PersonList;
import crossover.social.media.fe.ui.data.domain.UserList;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * CacheUserRepository
 * Created by bazzoni on 10/07/2015.
 */
@Component
public class CacheUserRepository extends AbstractConfigurableRepository {

    /**
     * Initializes all CacheRepository with Web services uri
     */
    @Override
    public void initialize() {
        hostname = properties.getProperty("repository.hostname", "localhost");
        port = Integer.parseInt(properties.getProperty("repository.port", "9000"));
        prepareHttpClient();
    }

    public List<User> findByUsername(String username) {
        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);

        HttpEntity<SocialContent> requestEntity = new HttpEntity<>(headers);
        // Pass the new person and header
        ResponseEntity<UserList> entity = template.exchange("http://" + hostname + ":" + port + "/users/search/findByUsername?username=" + username, HttpMethod.GET, requestEntity, UserList.class);

        return entity.getBody()._embedded.users;
    }

    public Person findPerson(String id) {
        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);

        HttpEntity<SocialContent> requestEntity = new HttpEntity<>(headers);
        // Pass the new person and header
        ResponseEntity<Person> entity = template.exchange("http://" + hostname + ":" + port + "/persons/" + id, HttpMethod.GET, requestEntity, Person.class);

        return entity.getBody();
    }

    public Person findPersonByUserId(String userId) {

        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);

        HttpEntity<SocialContent> requestEntity = new HttpEntity<>(headers);
        // Pass the new person and header
        ResponseEntity<PersonList> entity = template.exchange("http://" + hostname + ":" + port + "/persons/search/findByUserId?userId=" + userId, HttpMethod.GET, requestEntity, PersonList.class);

        return entity.getBody()._embedded.persons.get(0);
    }
}
