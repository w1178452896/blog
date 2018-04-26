package com.taylorsfan.blog.util;

import com.taylorsfan.blog.model.Blog;
import com.taylorsfan.blog.vo.BlogVo;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author taylorsfan
 */
public class MapUtil {
    public static Map<String, Integer> int2map(int pageNum, int pageSize) {
        Map<String, Integer> map = new HashMap<>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return map;
    }
}
