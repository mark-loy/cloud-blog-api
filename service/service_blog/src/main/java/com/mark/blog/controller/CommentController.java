package com.mark.blog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mark.blog.entity.Comment;
import com.mark.blog.service.CommentService;
import com.mark.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author mark
 * @since 2021-01-22
 */
@Api(value = "评论管理", tags = {"评论管理服务接口"})
@RestController
@RequestMapping("api/blog/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 获取评论分页信息
     * @param current 当前页
     * @param limit 当页显示数
     * @param articleId 文章id
     * @return Result

     */
    @ApiOperation("获取评论分页信息")
    @GetMapping("/{current}/{limit}")
    public Result getCommentPage(@ApiParam("当前页") @PathVariable("current") Long current,
                                 @ApiParam("当页显示数") @PathVariable("limit") Long limit,
                                 @ApiParam("文章id") @RequestParam("aid") String articleId) {
        // 判断分页参数
        if (current < 1 || limit < 1) {
            return Result.error().message("分页参数不能为负数");
        }
        // 获取评论分页信息
        Map<String, Object> map = commentService.getCommentPage(current, limit, articleId);

        return Result.ok().data(map);
    }

    /**
     * 修改评论状态
     * @param commentId 评论id
     * @param status 状态 0：未禁止 1：表示已禁止
     * @return Result
     */
    @ApiOperation("修改评论状态")
    @PutMapping("/{cid}/{status}")
    public Result updateCommentStatus(@ApiParam("评论id") @PathVariable("cid") String commentId,
                                      @ApiParam(value = "状态", example = "0：已禁止 1：表示正常") @PathVariable("status") Boolean status) {
        commentService.updateCommentStatus(commentId, status);
        return Result.ok();
    }

    /**
     * 根据id删除评论
     * @param commentId 评论id
     * @return Result
     */
    @ApiOperation("根据id删除评论")
    @DeleteMapping("/{cid}")
    public Result deleteComment(@ApiParam("评论id") @PathVariable("cid") String commentId) {
        commentService.deleteComment(commentId);
        return Result.ok();
    }

    /**
     * 批量删除评论
     * @param commentIds 评论id集合
     * @return Result
     */
    @ApiOperation("批量删除评论")
    @DeleteMapping("/")
    public Result deleteBatchComment(@ApiParam("评论id集合") @RequestParam("commentIds") List<String> commentIds) {
        commentService.deleteBatchComment(commentIds);
        return Result.ok();
    }

    /**
     * TODO 添加评论
     * @return Result
     */
    @PostMapping("/")
    public Result saveComment() {

        return Result.ok();
    }

}

