package interview.improved;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Kv4Test {

    @Test
    void set() throws InterruptedException {
        Kv4 kv = new Kv4();

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
        assertNull(kv.get("greeting", 0L));
        assertEquals("hello", kv.get("greeting", timeStamp3));

        assertNull(kv.get("fake"));

        assertNull(kv.get("greeting", 0L));
        assertEquals("hi", kv.get("greeting", timeStamp1 + 750L));
        assertEquals("hello", kv.get("greeting", timeStamp3 + 750L));
    }
}