package crossover.social.media.fe.ui.data;

import crossover.social.media.domain.Person;
import crossover.social.media.domain.SocialContent;
import crossover.social.media.fe.ui.web.model.ContentData;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CacheContentRepository
 * Created by bazzoni on 11/07/2015.
 */
@Component
public class CacheContentRepository {
    protected String siteId;
    protected HttpClient client;
    @Autowired
    private CacheUserRepository cacheUserRepository;
    @Value("${social.media.admin.username}")
    private String username = "admin";
    @Value("${social.media.admin.password}")
    private String password = "admin";
    @Value("${social.media.repository.service.host}")
    private String hostname = "localhost";
    @Value("${social.media.repository.service.port}")
    private int port = 9000;

    protected void prepareHttpClient() {
        int timeout = 5;

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000)
                .build();

        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        AuthScope authScope = new AuthScope(hostname, port, AuthScope.ANY_REALM);
        Credentials credentials = new UsernamePasswordCredentials(username, password);
        credentialsProvider.setCredentials(authScope, credentials);

        client = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
    }

    @Cacheable("content")
    public List<ContentData> findLastContents(Date time) {
        //TODO call remote WS service
        RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));

        // Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);

        HttpEntity<SocialContent> requestEntity = new HttpEntity<>(headers);
        // Pass the new person and header
        @SuppressWarnings("rawtypes")
        ResponseEntity<SocialContentList> entity = template.exchange("http://" + hostname + ":" + port + "/socialContents?page=0&size=3", HttpMethod.GET, requestEntity, SocialContentList.class);

        final List<ContentData> dataList = new ArrayList<>();
        for (SocialContent socialContent : entity.getBody()) {
            ContentData contentData = new ContentData();
            contentData.setId(socialContent.getId());
            contentData.setTitle(socialContent.getTitle());
            contentData.setTimestamp(socialContent.getModificationDate());
            contentData.setAuthorId(socialContent.getAuthorId());

            final Person person = cacheUserRepository.findPerson(socialContent.getAuthorId());
            contentData.setAuthor(String.format("%s %s", person.getFirstName(), person.getLastName()));

            contentData.setPost(socialContent.getContent());
            dataList.add(contentData);
        }
        return dataList;
    }

    class SocialContentList extends ArrayList<SocialContent> {
    }
}
