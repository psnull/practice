package interview.improved;

import java.util.*;

public class Kv5 {
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
