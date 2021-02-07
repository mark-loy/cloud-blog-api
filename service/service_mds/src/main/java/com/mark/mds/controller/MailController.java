package com.mark.mds.controller;


import com.mark.base.vo.CommentMailRpcVO;
import com.mark.common.entity.Result;
import com.mark.mds.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;

/**
 * <p>
 * 邮件管理表 前端控制器
 * </p>
 *
 * @author mark
 * @since 2021-02-06
 */
@Api(value = "邮件发送管理", tags = {"邮件发送服务接口"})
@Validated
@RestController
@RequestMapping("api/mds/mail")
public class MailController {

    @Resource
    private MailService mailService;

    /**
     * 发送邮件验证码
     * @param to 邮件接收人
     */
    @ApiOperation("发送邮件验证码")
    @PostMapping("/send/code")
    public Result sendSimpleMail(@ApiParam("邮件接收人") @RequestParam("email") @Email(message = "邮箱不合法") String to){

        mailService.sendCodeMail(to);

        return Result.ok();
    }

    /**
     * 发送评论回复通知邮件
     * @param commentMailVO 评论邮件实体
     * @return Result
     */
    @ApiOperation("发送评论回复通知邮件")
    @PostMapping("/send/comment")
    public Result sendCommentMail(@ApiParam("评论邮件实体") @RequestBody @Valid CommentMailRpcVO commentMailVO) {
        mailService.sendCommentMail(commentMailVO);
        return Result.ok();
    }
}

