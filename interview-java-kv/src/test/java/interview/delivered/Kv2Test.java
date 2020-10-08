package interview.delivered;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class Kv2Test {

    @Test
    void main() throws InterruptedException {
        Kv2 kv = new Kv2();

        long timeStamp1 = kv.set("greeting", "hi");
        assertEquals("hi", kv.get("greeting"));
        Thread.sleep(1000L);

        long timeStamp2 = kv.set("greeting1", "yo");
        assertEquals("yo", kv.get("greeting1"));
        Thread.sleep(1000L);

        long timeStamp3 = kv.set("greeting", "hello");
        assertEquals("hello", kv.get("greeting"));
        Thread.sleep(1000L);

        assertEquals("hi", kv.get("greeting", timeStamp1));
        assertNull(kv.get("greeting", timeStamp2));
        assertEquals("hello", kv.get("greeting", timeStamp3));

        assertNull(kv.get("fake"));
    }
}