package sol.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.StringTokenizer;

public abstract class AbstractCSV {

  /**
   * CSV StringTokenizer.
   */
  private final StringTokenizer token = StringTokenizer.getCSVInstance();

  /**
   * @param line CSV line
   * @return String[] String array
   */
  public final String[] parseLine(final String line) {
    token.reset(line);
    final String[] row = new String[token.size()];
    for (int i = 0; i < row.length; i++) {
      row[i] = (String) token.next();
    }
    return row;
  }

  /**
   * @param reader BufferedReader
   * @return List&lt;String[]&gt; String array in List
   * @throws IOException IOException
   */
  public final List<String[]> readAll(final BufferedReader reader)
      throws IOException {
    final List<String[]> list = new ArrayList<String[]>(8);
    String line;
    while ((line = reader.readLine()) != null) {
      list.add(parseLine(line));
    }
    return list;
  }

  /**
   * @param reader Reader
   * @return List&lt;String[]&gt; String array in List
   * @throws IOException IOException
   */
  public final List<String[]> readAll(final Reader reader) throws IOException {
    return readAll(new BufferedReader(reader));
  }

  /**
   * @param in InputStream
   * @param out OutputStream
   * @throws IOException IOException
   */
  public final void transform(final InputStream in, final OutputStream out)
      throws IOException {
    transform(new InputStreamReader(in), out);
  }

  abstract void transform(InputStreamReader in, OutputStream out)
      throws IOException;
}
