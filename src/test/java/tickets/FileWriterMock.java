package tickets;

/**
 * Created by taldo on 23/08/2017.
 */
public class FileWriterMock implements FileWriter {
    public void write(String text) {
        System.out.println("FileWriterMock - Doing nothing");
    }
}
