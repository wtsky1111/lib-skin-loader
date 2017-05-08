package com.jr36.skin.util;

import java.util.List;

/**
 * List Utils
 *
 * @author fengjun
 */
public class ListUtils {

    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }
}
