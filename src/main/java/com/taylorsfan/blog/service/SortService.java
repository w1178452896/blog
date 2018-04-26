package com.taylorsfan.blog.service;

import com.taylorsfan.blog.model.Sort;

/**
 * @author taylorsfan
 */
public interface SortService extends BaseService<Sort> {

    Sort showSortByBlogId(int blogId);

}
