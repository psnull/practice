package interview.delivered;

import java.util.HashMap;
import java.util.Map;

public class Kv {

    private final Map<String, String> map = new HashMap<>();

    public void set(String key, String value) {
        this.map.put(key, value);
    }

    public String get(String key) {
        return this.map.get(key);
    }


}
