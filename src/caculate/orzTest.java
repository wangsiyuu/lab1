package caculate;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class orzTest {
	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testDerivation() {
		orz neworz = new orz();
		neworz.Derivation("!d/d x", "x+y*+2");
	}
}