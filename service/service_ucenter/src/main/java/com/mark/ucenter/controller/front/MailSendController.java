package com.mark.ucenter.controller.front;

import com.mark.common.entity.Result;
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

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送邮件验证码
     * @param to 邮件接收人
     */
    @ApiOperation("发送邮件验证码")
    @PostMapping("/send")
    public Result sendSimpleMail(@ApiParam("邮件接收人") @RequestParam("email") @Email(message = "邮箱不合法") String to){

        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件接收方
        message.setTo(to);
        // 邮件标题
        message.setSubject("小码客个人博客网站");
        // 获取验证码
        String code = GeneratorCode.getCode();
        // 邮件内容主体
        message.setText("【小码客】验证码：" + code +
                "，您正在注册小码客，5分钟内有效。切勿将验证码告知他人，以防账号被盗。");
        // 邮件发送方
        message.setFrom(from);

        // 发送邮件
        mailSender.send(message);

        // 发送成功，将验证码放入redis中，有效期为5分钟
        redisTemplate.opsForValue().set(to, code, 5, TimeUnit.MINUTES);

        return Result.ok();
    }
}
