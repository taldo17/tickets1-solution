package tickets;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by taldo on 23/08/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class TicketServiceTest {
    @Autowired
    TicketService ticketService;

    @Test
    public void ticketsOrderedOneWeekOrLessBeforeTheShowHasNoCancellationDate() throws Exception {
        String showName = "Metallica";
        String desiredDateStr = SetDesiredDateToOneDayFromNow();
        int numberOfTickets = 2;
        String actualReport = ticketService.placeOrder(showName, desiredDateStr, numberOfTickets);
        String expectedReport = buildExpectedResult(showName, desiredDateStr, numberOfTickets);
        Assert.assertEquals(actualReport, expectedReport);
    }

    private String SetDesiredDateToOneDayFromNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        return formatDate(calendar.getTime());
    }

    private String buildExpectedResult(String showName, String desiredDateStr, int numberOfTickets) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order report:");
        sb.append("\n");
        sb.append("Show name: " + showName);
        sb.append("\n");
        sb.append("Desired date: " + desiredDateStr);
        sb.append("\n");
        sb.append("Number of tickets: " + numberOfTickets);
        sb.append("\n");
        sb.append("Last cancellation Date: " + "No cancellation");
        sb.append("\n");

        return sb.toString();
    }

    private String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return date != null? simpleDateFormat.format(date) : null;
    }
}
