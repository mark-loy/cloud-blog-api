package com.mark.blog.client;

import com.mark.base.vo.CommentMailRpcVO;
import com.mark.base.vo.VisitorRpcVO;
import com.mark.common.entity.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/2/5 10:50
 */
@FeignClient(value = "service-ucenter")
public interface UCenterClient {

    /**
     * 通过访客id获取访客信息
     * @param visitorId 访客id
     * @return VisitorRpcVO
     */
    @GetMapping("api/ucenter/rpc/visitor/{vid}")
    VisitorRpcVO getVisitorId(@ApiParam("访客id") @PathVariable("vid") String visitorId);

    /**
     * 发送评论回复通知邮件
     * @param commentMailVO 评论邮件实体
     * @return Result
     */
    @PostMapping("api/ucenter/rpc/mail/send")
    Result sendCommentMail(@RequestBody CommentMailRpcVO commentMailVO);
}
