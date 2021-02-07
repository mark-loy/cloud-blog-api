package com.mark.blog.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mark.base.vo.CommentMailRpcVO;
import com.mark.base.vo.VisitorRpcVO;
import com.mark.blog.client.MailClient;
import com.mark.blog.client.UCenterClient;
import com.mark.blog.entity.Article;
import com.mark.blog.entity.Comment;
import com.mark.blog.entity.User;
import com.mark.blog.entity.vo.front.ArticleResultVO;
import com.mark.blog.entity.vo.front.CommentFrontFormVO;
import com.mark.blog.entity.vo.front.CommentResultVO;
import com.mark.blog.helper.ArticleServiceHelper;
import com.mark.blog.helper.CommentServiceHelper;
import com.mark.blog.service.ArticleService;
import com.mark.blog.service.CommentService;
import com.mark.blog.service.UserService;
import com.mark.common.entity.Result;
import com.mark.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/29 14:47
 */
@Api(value = "文章详情页管理", tags = {"文章详情页接口管理"})
@RestController
@RequestMapping("api/blog/article/detail")
public class ArticlePageController {

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleServiceHelper articleServiceHelper;

    @Resource
    private CommentService commentService;

    @Resource
    private CommentServiceHelper commentServiceHelper;

    @Resource
    private UCenterClient uCenterClient;

    @Resource
    private MailClient mailClient;

    @Resource
    private UserService userService;

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

    /**
     * 根据文章id获取评论数据
     * @param articleId 文章id
     * @return Result
     */
    @ApiOperation("根据文章id获取评论数据")
    @GetMapping("/comment/{aid}")
    public Result getComments(@ApiParam("文章id") @PathVariable("aid") String articleId) {
        QueryWrapper<Comment> commentWrapper = new QueryWrapper<>();
        commentWrapper.eq("article_id", articleId);
        List<Comment> comments = commentService.list(commentWrapper);
        // 获取评论总数
        long total = comments.size();
        List<CommentResultVO> commentResult = commentServiceHelper.getCommentResult(comments, "0");
        return Result.ok().data("comments", commentResult).data("total", total);
    }

    /**
     * 保存评论信息
     * @param commentForm 评论表单
     * @return Result
     */
    @ApiOperation("保存评论信息")
    @PostMapping("/comment")
    public Result saveComment(@ApiParam("评论表单") @RequestBody @Valid CommentFrontFormVO commentForm, HttpServletRequest request) {
        // 获取request请求头中的token，解析token得到评论人id
        Claims claims = JwtUtil.getUserByJwtToken(request);
        if (claims == null) {
            return Result.error().message("token为空");
        }
        // 获取评论人信息
        String id = claims.get("id", String.class);
        String nickname = claims.get("nickname", String.class);
        String avatar = claims.get("avatar", String.class);

        // 保存评论
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentForm, comment);
        // 设置评论人信息
        comment.setVisitorId(id).setVisitorName(nickname).setVisitorAvatar(avatar);

        VisitorRpcVO acceptRpcVO = new VisitorRpcVO();
        // 判断评论类型
        if ("0".equals(commentForm.getParentId())) {
            // 一级评论，通知用户
            User user = userService.getById(commentForm.getAcceptId());
            // 设置接收人信息
            comment.setAcceptId(user.getId()).setAcceptName(user.getNickname());
        } else {
            // 二级评论，通知访客
            // 通过远程调用获取接收人昵称
            acceptRpcVO = uCenterClient.getVisitorId(commentForm.getAcceptId());
            // 设置接收人信息
            comment.setAcceptId(acceptRpcVO.getId()).setAcceptName(acceptRpcVO.getNickname());
        }

        // 执行保存
        commentService.save(comment);

        // 对于二级评论，判断接收人是否有Email
        if (!StringUtils.isEmpty(acceptRpcVO.getEmail())) {
            // 根据文章id查询文章名称
            Article article = articleService.getById(commentForm.getArticleId());

            // 存在，发送邮件通知
            CommentMailRpcVO commentMailVO = new CommentMailRpcVO();
            // 设置评论邮件实体
            commentMailVO
                    .setEmail(acceptRpcVO.getEmail())
                    .setParentId(commentForm.getParentId())
                    .setContent(commentForm.getContent())
                    .setReplayName(acceptRpcVO.getNickname())
                    .setArticleName(article.getTitle());
            // 远程调用，发送邮件
            Result result = mailClient.sendCommentMail(commentMailVO);
            if (!result.getSuccess()) {
                return Result.error().message("邮件发送失败");
            }
        }
        return Result.ok();
    }
}
