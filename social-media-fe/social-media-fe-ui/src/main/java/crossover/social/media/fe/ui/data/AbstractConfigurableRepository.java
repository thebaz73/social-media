package crossover.social.media.fe.ui.data;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

/**
 * AbstractConfigurableRepository
 * Created by bazzoni on 12/07/2015.
 */
public abstract class AbstractConfigurableRepository implements ConfigurableRepository {
    protected String hostname;
    protected int port;
    protected Properties properties;
    protected HttpClient client;
    @Value("${social.media.admin.username}")
    private String username = "admin";
    @Value("${social.media.admin.password}")
    private String password = "admin";

    /**
     * Set all CacheRepository with Web services uri
     *
     * @param properties properties
     */
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }


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
}
