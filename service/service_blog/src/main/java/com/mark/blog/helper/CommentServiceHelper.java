package com.mark.blog.helper;

import com.mark.blog.entity.Comment;
import com.mark.blog.entity.vo.CommentResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/24 11:10
 */
@Component
public class CommentServiceHelper {

    /**
     * 递归遍历评论数据
     * @param comments 当前文章的评论数据
     * @param parentId 评论的父级id
     * @return List<CommentResponseVO>
     */
    public List<CommentResponseVO> getCommentResponse(List<Comment> comments, String parentId) {
        // 最终封装的结果集合
        List<CommentResponseVO> commentResponse = new ArrayList<>();
        // 遍历
        for (Comment comment : comments) {
            // 判断评论类型
            if (parentId.equals(comment.getParentId())) {
                // 构建返回的评论对象
                CommentResponseVO commentResponseVO = new CommentResponseVO();
                BeanUtils.copyProperties(comment, commentResponseVO);
                // 设置二级评论
                commentResponseVO.setChildren(getCommentResponse(comments, comment.getId()));

                // 将返回的评论对象加入集合
                commentResponse.add(commentResponseVO);
            }
        }
        return commentResponse;
    }
}
