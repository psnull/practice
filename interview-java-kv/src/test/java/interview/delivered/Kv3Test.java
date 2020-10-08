package interview.delivered;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Kv3Test {

    @Test
    void set() throws InterruptedException {
        Kv3 kv = new Kv3();

        long timeStamp1 = kv.set("greeting", "hi");
        System.out.println("Should be hi: " + kv.get("greeting"));
        Thread.sleep(1000L);

        long timeStamp2 = kv.set("greeting1", "yo");
        System.out.println("Should be yo: " + kv.get("greeting1"));
        Thread.sleep(1000L);

        long timeStamp3 = kv.set("greeting", "hello");
        System.out.println("Should be hello: " + kv.get("greeting"));
        Thread.sleep(1000L);

        System.out.println("Should be hi: " + kv.get("greeting", timeStamp1));
        System.out.println("Should be null: " + kv.get("greeting", 0L));
        System.out.println("Should be hello: " + kv.get("greeting", timeStamp3));

        System.out.println("Should be null: " + kv.get("fake"));

        System.out.println("Should be null: " + kv.get("greeting", 0L));
        System.out.println("Should be hi: " + kv.get("greeting", timeStamp1 + 750L));
        System.out.println("Should be hello: " + kv.get("greeting", timeStamp3 + 750L));
    }
}