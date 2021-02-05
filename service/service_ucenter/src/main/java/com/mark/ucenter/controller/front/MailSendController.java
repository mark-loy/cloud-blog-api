package com.mark.ucenter.controller.front;

import com.mark.common.entity.Result;
import com.mark.ucenter.service.MailSendService;
import com.mark.ucenter.util.GeneratorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.concurrent.TimeUnit;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/2/1 11:38
 */
@Api(value = "邮件发送管理", tags = {"邮件发送服务接口"})
@RestController
@RequestMapping("api/ucenter/front/mail")
@Validated
public class MailSendController {

    @Resource
    private MailSendService mailSendService;

    /**
     * 发送邮件验证码
     * @param to 邮件接收人
     */
    @ApiOperation("发送邮件验证码")
    @PostMapping("/send")
    public Result sendSimpleMail(@ApiParam("邮件接收人") @RequestParam("email") @Email(message = "邮箱不合法") String to){

        mailSendService.sendCodeMail(to);

        return Result.ok();
    }
}
