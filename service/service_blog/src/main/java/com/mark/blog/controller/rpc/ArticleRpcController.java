package com.mark.blog.controller.rpc;

import com.mark.blog.service.ArticleService;
import com.mark.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/24 18:52
 */
@Api(value = "文章RPC管理", tags = {"文章RPC管理服务接口"})
@RestController
@RequestMapping("api/blog/article/rpc")
public class ArticleRpcController {

    @Resource
    private ArticleService articleService;

    /**
     * RPC服务提供者
     *    生成文章的每日统计数据
     * @param date 日期
     * @return Result
     */
    @ApiOperation("生成文章的统计数据")
    @PostMapping("/generate/{date}")
    public Result generateArticleStaData(@ApiParam(value = "日期", example = "2020-01-24") @PathVariable("date") String date) {
        Map<String, Object> resultMap = articleService.generateStaData(date);
        return Result.ok().data(resultMap);
    }

}
