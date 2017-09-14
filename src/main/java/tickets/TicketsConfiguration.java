package tickets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by taldo on 23/08/2017.
 */
@Configuration
@ComponentScan(basePackages = "tickets")
@PropertySource("classpath:properties/config-context.properties")
public class TicketsConfiguration {

    @Value("${report_current_path}")
    private String reportPath;

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    FileWriter fileWriter(){
        WindowsFileWriter windowsFileWriter = new WindowsFileWriter();
        windowsFileWriter.setReportPath(reportPath);
        return windowsFileWriter;
    }

}
