package com.ybs.es.controller;

import com.ybs.es.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * ContentController
 *
 * @author Paulson
 * @date 2020/4/9 23:08
 */


@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable String keyword) throws IOException {
        return contentService.parserContent(keyword);
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String, Object>> search(@PathVariable String keyword, @PathVariable int pageNo, @PathVariable int pageSize ) throws IOException {
        return contentService.searchPageHighlighter(keyword, pageNo, pageSize);
    }


}
