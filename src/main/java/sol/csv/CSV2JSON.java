package sol.csv;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class CSV2JSON extends AbstractCSV {

  /**
   * @param lines String array in List
   * @param out OutputStream
   */
  public final void toJson(final List<String[]> lines, final OutputStream out) {
    if (lines.size() <= 1) {
      return;
    }
    final String[] header = lines.get(0);

    final Map<String, Object> properties = new HashMap<String, Object>();
    final JsonGeneratorFactory jgf = Json.createGeneratorFactory(properties);
    final JsonGenerator jg = jgf.createGenerator(out);
    try {
      jg.writeStartObject();

      jg.writeStartArray("headers");
      for (String string : header) {
        jg.write(string);
      }
      jg.writeEnd();

      jg.writeStartArray("rows");
      for (int j = 1; j < lines.size(); j++) {
        final String[] line = lines.get(j);
        jg.writeStartObject();
        for (int i = 0; i < line.length; i++) {
          jg.write(header[i], line[i]);
        }
        jg.writeEnd();
      }
      jg.writeEnd();

      jg.writeEnd();
    } finally {
      jg.close();
    }
  }

  /**
   * @param in InputStreamReader
   * @param out OutputStream
   * @throws IOException IOException
   */
  public final void transform(final InputStreamReader in,
                              final OutputStream out) throws IOException {
    toJson(readAll(in), out);
  }

  /**
   * @param args Command line Arguments
   * @throws IOException IOException
   */
  public static void main(final String[] args) throws IOException {
    new CSV2JSON().transform(System.in, System.out);
    System.out.println();
  }
}
