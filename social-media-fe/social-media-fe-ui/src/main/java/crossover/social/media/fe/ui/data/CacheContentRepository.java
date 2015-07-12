package crossover.social.media.fe.ui.data;

import crossover.social.media.domain.Person;
import crossover.social.media.domain.SocialContent;
import crossover.social.media.fe.ui.web.model.ContentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * CacheContentRepository
 * Created by bazzoni on 11/07/2015.
 */
@Component
public class CacheContentRepository extends AbstractConfigurableRepository {
    @Autowired
    private CacheUserRepository cacheUserRepository;

    /**
     * Initializes all CacheRepository with Web services uri
     */
    @Override
    public void initialize() {
        hostname = properties.getProperty("repository.hostname", "localhost");
        port = Integer.parseInt(properties.getProperty("repository.port", "9000"));
        prepareHttpClient();
    }

    public List<ContentData> findLastContents(int page, int size) {
        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);

        HttpEntity<SocialContent> requestEntity = new HttpEntity<>(headers);
        // Pass the new person and header
        ResponseEntity<Page> entity = template.exchange("http://" + hostname + ":" + port + "/socialContents?page=" + page + "&size=" + size, HttpMethod.GET, requestEntity, Page.class);

        final List<ContentData> dataList = new ArrayList<>();

        entity.getBody().getContent().stream().filter(o -> o instanceof SocialContent).forEach(o -> {
            SocialContent socialContent = (SocialContent) o;

            ContentData contentData = new ContentData();
            contentData.setId(socialContent.getId());
            contentData.setTitle(socialContent.getTitle());
            contentData.setTimestamp(socialContent.getModificationDate());
            contentData.setAuthorId(socialContent.getAuthorId());

            final Person person = cacheUserRepository.findPerson(socialContent.getAuthorId());
            contentData.setAuthor(String.format("%s %s", person.getFirstName(), person.getLastName()));

            contentData.setPost(socialContent.getContent());
            dataList.add(contentData);
        });
        return dataList;
    }
}
