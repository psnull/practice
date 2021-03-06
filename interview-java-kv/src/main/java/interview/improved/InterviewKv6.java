package interview.improved;

import java.util.*;

public class InterviewKv6 {
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

        return getFuzzy(timeStamp, inner);
    }

    //Found this great implementation here:
    //https://codereview.stackexchange.com/questions/33009/finding-greatest-value-in-array-smaller-than-x
    private String getFuzzy(Long timeStamp, LinkedHashMap<Long, String> inner) {
        Set<Long> keys = inner.keySet();
        ArrayList<Long> keyList = new ArrayList<>(keys);
        if (keyList.get(0) > timeStamp) {
            return null;
        }
        int index = greatestIndexNotExceeding(keyList, timeStamp);
        if (index >= 0) {
            return inner.get(keyList.get(index));
        }
        return null;
    }

    private int greatestIndexNotExceeding(ArrayList<Long> keyList, long limit) {
        if (keyList.size() < 1) {
            return -1;
        }
        return greatestIndexNotExceeding(keyList, limit, 0, keyList.size() - 1);
    }

    private int greatestIndexNotExceeding(ArrayList<Long> keyList, long limit, int lb, int ub) {
        final int mid = (lb + ub) / 2;
        //Need to go lower but can't
        if (mid == lb && keyList.get(mid) > limit) {
            return -1;
        }

        boolean currentNumberIsUnderLimit = keyList.get(mid) <= limit;
        boolean noGreaterValuesRemaining = mid == ub;

        boolean nextGreaterValueExceedsLimit = keyList.size() - 1 >= mid + 1 && keyList.get(mid + 1) > limit;
        if (currentNumberIsUnderLimit && (noGreaterValuesRemaining || nextGreaterValueExceedsLimit)) {
            return mid;
        }
        if (currentNumberIsUnderLimit) {
            //consider upper half
            return greatestIndexNotExceeding(keyList, limit, mid + 1, ub);
        } else {
            return greatestIndexNotExceeding(keyList, limit, lb, mid);
        }

    }

}
