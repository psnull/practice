package interview.delivered;

import org.junit.jupiter.api.Test;

class KvTest {

    @Test
    void set() {
        
        Kv kv = new Kv();
        kv.set("greeting", "hi");
        System.out.println("Should be hi: " + kv.get("greeting"));

        kv.set("greeting1", "yo");
        System.out.println("Should be yo: " + kv.get("greeting1"));

        kv.set("greeting", "hello");
        System.out.println("Should be hello: " + kv.get("greeting"));

        System.out.println("Should be null: " + kv.get("fake"));

    }
}