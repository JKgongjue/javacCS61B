package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        try {
            arb.enqueue(1);
            arb.enqueue(2);
            arb.enqueue(3);
            arb.enqueue(4);
            arb.enqueue(5);
            arb.enqueue(5);
            arb.enqueue(5);
            arb.enqueue(5);
            arb.enqueue(5);
            arb.enqueue(5);
            arb.enqueue(5);
            arb.enqueue(5);
            Iterator<Integer> a = arb.iterator();
            while (a.hasNext()) {
                System.out.println(a.next());
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
