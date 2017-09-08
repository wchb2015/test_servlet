package export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSVTEST {

    private static final String lineSeparator = System.getProperties().getProperty("line.separator");

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("/Users/wchb/Music/test/test.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("id");
        sb.append(',');
        sb.append("Name");
        sb.append(lineSeparator);

        sb.append("1");
        sb.append(',');
        sb.append("Prashant Ghimire 中文");
        sb.append(lineSeparator);

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
    }

}
