package com.mark.blog.controller.front;

import com.mark.blog.entity.Article;
import com.mark.blog.entity.vo.front.ArticleResultVO;
import com.mark.blog.helper.ArticleServiceHelper;
import com.mark.blog.service.ArticleService;
import com.mark.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/29 14:47
 */
@Api(value = "文章详情页管理", tags = {"文章详情页接口管理"})
@RestController
@RequestMapping("api/blog/article/detail")
public class ArticleDetailController {

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleServiceHelper articleServiceHelper;

    /**
     * 根据id获取文章数据
     * @param articleId 文章id
     * @return Result
     */
    @ApiOperation("根据id获取文章数据")
    @GetMapping("/{aid}")
    public Result getArticle(@ApiParam("文章id") @PathVariable("aid") String articleId) {
        // 获取文章数据
        Article article = articleService.getById(articleId);
        if (article == null) {
            return Result.error().message("当前文章不存在");
        }
        // 获取文章的返回数据
        ArticleResultVO articleResult = articleServiceHelper.getArticleResult(article);
        return Result.ok().data("article", articleResult);
    }

}
