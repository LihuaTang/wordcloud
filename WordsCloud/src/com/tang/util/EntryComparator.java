package com.tang.util;

import java.util.Comparator;
import java.util.Map;

/**
 * 
 * @deprecated:HashMap����ʵ����
 * @author LihuaTang 
 * @date 2015��7��9�� ����2:18:31
 */
public class EntryComparator implements Comparator<Map.Entry<String, Integer>> {
    public EntryComparator() {
    }

    public int compare(Map.Entry<String, Integer> o1,
            Map.Entry<String, Integer> o2)
    {
        return o2.getValue() - o1.getValue();
    }
}
