package com.mark.blog.service.impl;

import com.mark.blog.entity.Comment;
import com.mark.blog.mapper.CommentMapper;
import com.mark.blog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author mark
 * @since 2021-01-22
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
