package tickets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by taldo on 23/08/2017.
 */
@Configuration
public class TestConfiguration {

    @Bean
    TicketService ticketService(FileWriter fileWriter){
        return new TicketService(fileWriter());
    }

    @Bean
    FileWriter fileWriter(){
        FileWriterMock fileWriterMock = new FileWriterMock();
        return fileWriterMock;
    }
}
