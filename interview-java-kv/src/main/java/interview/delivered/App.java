package interview.delivered;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    static class KV {
        private final Map<String, String> map = new HashMap<>();

        public void set(String key, String value) {
            this.map.put(key, value);
        }

        public String get(String key) {
            return this.map.get(key);
        }
    }

    public static void main(String[] args) {

        KV kv = new KV();
        kv.set("greeting", "hi");
        System.out.println("Should be hi: " + kv.get("greeting"));

        kv.set("greeting1", "yo");
        System.out.println("Should be yo: " + kv.get("greeting1"));

        kv.set("greeting", "hello");
        System.out.println("Should be hello: " + kv.get("greeting"));

        System.out.println("Should be null: " + kv.get("fake"));

    }
}
