package sol.csv;

import static org.junit.Assert.assertArrayEquals;

import com.google.code.tempusfugit.concurrency.IntermittentTestRunner;
import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(IntermittentTestRunner.class)
public class AbstractCSVTest {
  private AbstractCSV csv;

  
  /** 
   * @throws Exception Exception
   */
  @Before
  public void setUp() throws Exception {
    csv =
      new AbstractCSV() {

        @Override
        void transform(InputStreamReader in, OutputStream out)
          throws IOException {}
      };
  }

  @Test
  @Intermittent(repetition = 1)
  public void testParseLine() {
    String[] expected = { "", "a,b", "c\"", "c\"x", "a,b", "" };
    String[] actual = csv.parseLine(",\"a,b\",c\",c\"x,\"a,b\",");
    assertArrayEquals(expected, actual);
  }

  
  /** 
   * @throws IOException Exception
   */
  @Test
  @Intermittent(repetition = 1)
  public void testRead() throws IOException {
    StringBuilder sb = new StringBuilder("a,b,c").append("\r\n");
    sb.append("d, ,f").append("\r\n");
    StringReader reader = new StringReader(sb.toString());

    List<String[]> expecteds = new ArrayList<String[]>();
    expecteds.add(new String[] { "a", "b", "c" });
    expecteds.add(new String[] { "d", "", "f" });
    List<String[]> actuals = csv.readAll(reader);
    Iterator<String[]> iterator = actuals.iterator();

    for (String[] expected : expecteds) {
      if (iterator.hasNext()) {
        String[] actual = iterator.next();
        assertArrayEquals(expected, actual);
      }
    }
  }
}
