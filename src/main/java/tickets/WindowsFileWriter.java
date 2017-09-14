package tickets;

import java.io.*;

/**
 * Created by taldo on 23/08/2017.
 */
public class WindowsFileWriter implements FileWriter {

    Writer writer;

    private  String reportPath;

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }


    public void write(String text){
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reportPath), "utf-8"));
            writer.write(text);
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
