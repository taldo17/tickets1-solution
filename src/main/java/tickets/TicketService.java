package tickets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by taldo on 22/08/2017.
 */
@Service
public class TicketService {

    private FileWriter fileWriter;
    private final int CANCELLATION_TIME_IN_WEEKS = 1;

    @Autowired
    public TicketService(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public String placeOrder(String showName, String desiredDateStr, int numberOfTickets) {
        Date desiredDate = parseDate(desiredDateStr);
        Date CurrentDate = new Date();
        validateDates(desiredDate, CurrentDate);
        Date lastCancellationDate = null;
        if (isMoreThanAWeek(desiredDate.getTime(), CurrentDate.getTime())) {
            lastCancellationDate = setCancellationDate(CurrentDate);
        }
        String reportString = generateReportString(showName, desiredDateStr, numberOfTickets, lastCancellationDate);
        fileWriter.write(reportString);
        return reportString;

    }

    private Date setCancellationDate(Date currentDate) {
        Date lastCancellationDate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.WEEK_OF_MONTH, CANCELLATION_TIME_IN_WEEKS);
        lastCancellationDate = calendar.getTime();
        return lastCancellationDate;
    }

    private String generateReportString(String showName, String desireDate, int numberOfTickets, Date lastCancellationDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order report:");
        sb.append("\n");
        sb.append("Show name: " + showName);
        sb.append("\n");
        sb.append("Desired date: " + desireDate);
        sb.append("\n");
        sb.append("Number of tickets: " + numberOfTickets);
        sb.append("\n");
        sb.append("Last cancellation Date: " + formatDate(lastCancellationDate));
        sb.append("\n");
        return sb.toString();
    }

    private void validateDates(Date desiredDate, Date currentDate) {
        currentDate = new Date();
        if (desiredDate.getTime() < currentDate.getTime()) {
            throw new RuntimeException("Desired date has already passed");
        }
    }

    private Date parseDate(String desiredDateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return simpleDateFormat.parse(desiredDateStr);
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return date != null? simpleDateFormat.format(date) : "No cancellation";
    }


    private boolean isMoreThanAWeek(long latterDate, long priorDate) {
        return (latterDate - priorDate) / (1000 * 60 * 60 * 24) > 7;
    }
}
