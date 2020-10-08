package interview.improved;

import java.util.*;

public class Kv4 {
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
        if (this.map.containsKey(key)) {
            LinkedHashMap<Long, String> inner = this.map.get(key);
            Set<Long> keys = inner.keySet();
            ArrayList<Long> keyList = new ArrayList<>(keys);
            return inner.get(keyList.get(keyList.size() - 1));
        } else {
            return null;
        }

    }

    public String get(String key, Long timeStamp) {
        if (this.map.containsKey(key)) {
            LinkedHashMap<Long, String> inner = this.map.get(key);
            if (inner.containsKey(timeStamp)) {
                return inner.get(timeStamp);
            } else {
                Set<Long> keys = inner.keySet();
                ArrayList<Long> keyList = new ArrayList<>(keys);
                for (int i = keyList.size() - 1; i >= 0; i--) {
                    if (keyList.get(i) < timeStamp) {
                        return inner.get(keyList.get(i));
                    }
                }
                return null;
            }
        } else {
            return null;
        }
    }

}
