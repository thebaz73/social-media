package crossover.social.media.fe.ui;

import crossover.social.media.fe.ui.service.ServiceInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Application
 * Created by bazzoni on 10/07/2015.
 */
@ComponentScan({"crossover.social"})
@EnableAutoConfiguration
public class Application implements CommandLineRunner {
    @Autowired
    private ServiceInitializer initializer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        final File props = Paths.get(args[0]).toFile();
        if (!props.exists()) {
            throw new RuntimeException("No service property file");
        }
        Properties p = new Properties();
        p.load(new FileReader(props));
        initializer.initialize(p);
    }
}
