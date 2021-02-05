package com.mark.ucenter.controller.rpc;

import com.mark.base.vo.CommentMailRpcVO;
import com.mark.common.entity.Result;
import com.mark.ucenter.service.MailSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/2/5 12:22
 */
@Api(value = "邮件RPC服务管理")
@RestController
@RequestMapping("api/ucenter/rpc/mail")
public class MailSendRpcController {

    @Resource
    private MailSendService mailSendService;

    /**
     * 发送评论回复通知邮件
     * @param commentMailVO 评论邮件实体
     * @return Result
     */
    @ApiOperation("发送评论回复通知邮件")
    @PostMapping("/send")
    public Result sendCommentMail(@ApiParam("评论邮件实体") @RequestBody @Valid CommentMailRpcVO commentMailVO) {
        mailSendService.sendCommentMail(commentMailVO);
        return Result.ok();
    }
}
