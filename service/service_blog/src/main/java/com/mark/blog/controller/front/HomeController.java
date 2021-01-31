package com.mark.blog.controller.front;

import com.mark.blog.entity.vo.front.ArticleFrontQueryVO;
import com.mark.blog.entity.vo.front.FocusMapVO;
import com.mark.blog.service.ArticleService;
import com.mark.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/28 19:49
 */
@Api(value = "首页管理", tags = {"首页接口管理"})
@RestController
@RequestMapping("api/blog/home")
public class HomeController {

    @Resource
    private ArticleService articleService;

    /**
     * 获取文章列表数据
     * @param page 当前页
     * @param size 当页显示数
     * @param articleFrontQueryVO 文章查询对象
     * @return Result
     */
    @ApiOperation("获取文章列表数据")
    @PostMapping("/articles/{page}/{size}")
    public Result getArticleList(@ApiParam("当前页") @PathVariable("page") Long page,
                                 @ApiParam("当前显示数") @PathVariable("size") Long size,
                                 @ApiParam("文章查询对象") @RequestBody(required = false) ArticleFrontQueryVO articleFrontQueryVO
                                 ) {
        if (page < 1 || size < 1) {
            return  Result.error().message("分页参数不正确");
        }
        Map<String, Object> map = articleService.getFrontArticles(page, size, articleFrontQueryVO);
        return Result.ok().data(map);
    }

    /**
     * 获取焦点图列表数据
     * @return Result
     */
    @ApiOperation("获取焦点图列表数据")
    @GetMapping("/focus")
    public Result getFocusMap() {
        List<FocusMapVO> focusMaps = articleService.getFocusMap();
        return Result.ok().data("features", focusMaps);
    }
}
