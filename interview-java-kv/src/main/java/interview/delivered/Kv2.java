package interview.delivered;

import java.util.*;

public class Kv2 {

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
