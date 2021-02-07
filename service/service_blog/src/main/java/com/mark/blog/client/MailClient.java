package com.mark.blog.client;

import com.mark.base.vo.CommentMailRpcVO;
import com.mark.base.vo.VisitorRpcVO;
import com.mark.common.entity.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/2/5 10:50
 */
@FeignClient(value = "service-mds")
public interface MailClient {

    /**
     * 发送评论回复通知邮件UCenterClient
     * @param commentMailVO 评论邮件实体
     * @return Result
     */
    @PostMapping("api/mds/mail/send/comment")
    Result sendCommentMail(@RequestBody CommentMailRpcVO commentMailVO);
}
