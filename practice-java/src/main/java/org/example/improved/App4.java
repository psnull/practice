package org.example.improved;

import java.util.*;

/**
 * Hello world!
 */
public class App4 {
    static class KV {
        private final Map<String, LinkedHashMap<Long, String>> map = new HashMap<>();

        public long set(String key, String value) {
            long timestamp = System.currentTimeMillis();
            if (this.map.containsKey(key)) {
                this.map.get(key).put(timestamp, value);
            } else {
                LinkedHashMap<Long, String> newMap = new LinkedHashMap<>();
                newMap.put(timestamp, value);
                this.map.put(key, newMap);
            }
            return timestamp;
        }

        public String get(String key) {
            if (!this.map.containsKey(key)) {
                return null;
            }
            LinkedHashMap<Long, String> inner = this.map.get(key);
            Set<Long> keys = inner.keySet();
            ArrayList<Long> keyList = new ArrayList<>(keys);
            return inner.get(keyList.get(keyList.size() - 1));
        }

        public String get(String key, Long timeStamp) {
            if (!this.map.containsKey(key)) {
                return null;
            }

            LinkedHashMap<Long, String> inner = this.map.get(key);
            if (inner.containsKey(timeStamp)) {
                return inner.get(timeStamp);
            }

            Set<Long> keys = inner.keySet();
            ArrayList<Long> keyList = new ArrayList<>(keys);
            if (keyList.get(0) > timeStamp) {
                return null;
            }
            for (int i = keyList.size() - 1; i >= 0; i--) {
                if (keyList.get(i) < timeStamp) {
                    return inner.get(keyList.get(i));
                }
            }
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        App4.KV kv = new App4.KV();

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
