package sol.csv;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CSV2XML extends AbstractCSV {

  /**
   * @param rows String array in List
   * @param out OutputStream
   * @throws ParserConfigurationException XML Parser Exception
   * @throws TransformerException XML Transformer Exception
   */
  public final void toXml(final List<String[]> rows, final OutputStream out)
      throws ParserConfigurationException, TransformerException {
    if (rows.size() <= 1) {
      return;
    }
    final String[] header = rows.get(0);

    final DocumentBuilderFactory docFactory =
        DocumentBuilderFactory.newInstance();
    final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    final Document doc = docBuilder.newDocument();
    final Element rootElement = doc.createElement("root");

    doc.appendChild(rootElement);
    for (int j = 1; j < rows.size(); j++) {
      final Element rowElement = doc.createElement("row");
      rootElement.appendChild(rowElement);

      String[] row = rows.get(j);
      for (int i = 0; i < row.length; i++) {
        final Element e = doc.createElement(header[i].trim());
        e.setTextContent(row[i]);
        rowElement.appendChild(e);
      }
    }

    final TransformerFactory transformerFactory =
        TransformerFactory.newInstance();
    final Transformer transformer = transformerFactory.newTransformer();
    transformer.transform(new DOMSource(doc), new StreamResult(out));
  }

  /**
   * @param in InputStreamReader
   * @param out OutputStream
   * @throws IOException IOException
   */
  public final void transform(final InputStreamReader in,
                              final OutputStream out) throws IOException {
    try {
      toXml(readAll(in), out);
    } catch (ParserConfigurationException e) {
      throw new IOException(e);
    } catch (TransformerException e) {
      throw new IOException(e);
    }
  }

  /**
   * @param args Command line Arguments
   * @throws IOException IOException
   */
  public static void main(final String[] args) throws IOException {
    new CSV2XML().transform(System.in, System.out);
  }
}
