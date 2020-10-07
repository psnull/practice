package org.example.delivered;

import java.util.*;

/**
 * Hello world!
 */
public class App2 {
    static class KV {
        private final Map<String, Map<Long, String>> map = new HashMap<>();

        public long set(String key, String value) {
            long timestamp = System.currentTimeMillis();
            if (this.map.containsKey(key)) {
                this.map.get(key).put(timestamp, value);
            } else {
                Map<Long, String> newMap = new HashMap<>();
                newMap.put(timestamp, value);
                this.map.put(key, newMap);
            }
            return timestamp;
        }

        public String get(String key) {
            if (this.map.containsKey(key)) {
                Map<Long, String> inner = this.map.get(key);
                Set<Long> keys = inner.keySet();
                ArrayList<Long> keyList = new ArrayList<>(keys);
                Collections.sort(keyList);
                return inner.get(keyList.get(keyList.size() - 1));
            } else {
                return null;
            }
        }

        public String get(String key, Long timeStamp) {
            if (this.map.containsKey(key)) {
                Map<Long, String> inner = this.map.get(key);
                if (inner.containsKey(timeStamp)) {
                    return inner.get(timeStamp);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        App2.KV kv = new App2.KV();

        long timeStamp1 = kv.set("greeting", "hi");
        System.out.println("Should be hi: " + kv.get("greeting"));
        Thread.sleep(1000L);

        long timeStamp2 = kv.set("greeting1", "yo");
        System.out.println("Should be yo: " + kv.get("greeting1"));
        Thread.sleep(1000L);

        long timeStamp3 = kv.set("greeting", "hello");
        System.out.println("Should be hello " + kv.get("greeting"));
        Thread.sleep(1000L);

        System.out.println("Should be hi " + kv.get("greeting", timeStamp1));
        System.out.println("Should be null " + kv.get("greeting", timeStamp2));
        System.out.println("Should be hello " + kv.get("greeting", timeStamp3));

        System.out.println("Should be null: " + kv.get("fake"));
    }
}
