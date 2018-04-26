package com.taylorsfan.blog.util;

import java.util.Date;

/**
 * @author taylorsfan
 */
public class IdUtil {
    public static int createId() {
        return -(int) new Date().getTime();
    }

}
