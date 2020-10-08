package interview.delivered;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class Kv1Test {

    @Test
    void set() {

        InterviewKv1 kv = new InterviewKv1();
        kv.set("greeting", "hi");
        assertEquals("hi", kv.get("greeting"));

        kv.set("greeting1", "yo");
        assertEquals("yo", kv.get("greeting1"));

        kv.set("greeting", "hello");
        assertEquals("hello", kv.get("greeting"));

        assertNull(kv.get("fake"));

    }
}