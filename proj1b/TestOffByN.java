import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    @Test
    public void test1() {
        OffByN offBy5 = new OffByN(5);
        offBy5.equalChars('a', 'f');  // true
        offBy5.equalChars('f', 'a');  // true
        offBy5.equalChars('f', 'h');  // false
    }
}
