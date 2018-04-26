package com.taylorsfan.blog.controller.admin;

import com.taylorsfan.blog.model.Sort;
import com.taylorsfan.blog.service.BlogService;
import com.taylorsfan.blog.service.SortService;
import com.taylorsfan.blog.util.IdUtil;
import com.taylorsfan.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author tianle
 */
@RestController
@RequestMapping("/admin/sort")
public class DoSortController {
    private final SortService sortService;

    @Autowired
    public DoSortController(SortService sortService, BlogService blogService) {
        this.sortService = sortService;
    }

    @RequestMapping("/all")
    public List<Sort> SortAll() {
        return sortService.showAll(new HashMap<>());
    }

    @RequestMapping("/{sortId}/update")
    public ResultUtil update(Sort sort, @PathVariable int sortId) {
        if (sortService.update(sort)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(400, "failure");
    }

    @RequestMapping("/insert")
    public ResultUtil insert(Sort sort) {
        int id = IdUtil.createId();
        sort.setId(id);
        if (sortService.insert(sort)) {
            return new ResultUtil(200, "success");
        }
        return new ResultUtil(400, "failure");
    }
}

