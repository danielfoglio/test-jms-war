package sample.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.WebApplicationInitializer;

/**
 * Sample WAR application
 */
@SpringBootApplication
@PropertySource(value = { "classpath:test-jms-war-main-context.properties" })
@ImportResource(locations = "classpath:test-jms-war-main-context.xml")
public class SampleWarApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static final String SERVER_NAME  = System.getProperty("weblogic.Name");

	public static void main(String[] args) {
		SpringApplication.run(SampleWarApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SampleWarApplication.class);
    }

}
