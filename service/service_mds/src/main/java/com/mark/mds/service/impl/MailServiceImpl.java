package com.mark.mds.service.impl;

import com.mark.base.vo.CommentMailRpcVO;
import com.mark.mds.entity.Mail;
import com.mark.mds.mapper.MailMapper;
import com.mark.mds.service.MailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mark.mds.util.GeneratorCode;
import com.mark.mds.util.MdsConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 邮件管理表 服务实现类
 * </p>
 *
 * @author mark
 * @since 2021-02-06
 */
@Service
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail> implements MailService {
    @Resource
    private JavaMailSender mailSender;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送验证码邮件
     * @param to 邮件接收人
     */
    @Override
    public void sendCodeMail(String to) {
        // 定义模板文本
        String temText = "【小码客】验证码：%s，您正在注册小码客，5分钟内有效。切勿将验证码告知他人，以防账号被盗。";
        // 生成四位数验证码
        String code = GeneratorCode.getCode();
        // 发送邮件
        sendMail(to, String.format(temText, code));
        // 发送成功，将验证码放入redis中，有效期为5分钟
        redisTemplate.opsForValue().set(to, code, 5, TimeUnit.MINUTES);
    }

    /**
     * 发送评论回复邮件
     * @param commentMailVO 评论邮件实体
     */
    @Override
    public void sendCommentMail(CommentMailRpcVO commentMailVO) {
        // 定义模板文本
        String temText = "【小码客】通知：%s %s %s %s";
        String typeText;
        String endText;
        // 判断评论类型
        if ("0".equals(commentMailVO.getParentId())) {
            // 一级评论
            // 定义类型文本
            typeText = "评论了";
            // 定义结尾
            endText = "文章";

        } else {
            // 回复评论
            typeText = "回复了";
            // 定义结尾
            endText = "文章的评论";
        }
        // 替换文本
        String text = String.format(temText, commentMailVO.getReplayName(), typeText, commentMailVO.getArticleName(), endText);
        // 发送邮件
        sendMail(commentMailVO.getEmail(), text);
    }

    /**
     * 发送简单邮件
     * @param to 邮件接收人
     * @param text 发送文本
     */
    private void sendMail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件接收方
        message.setTo(to);
        // 邮件标题
        message.setSubject(MdsConstant.MAIL_SUBJECT);
        // 邮件内容主体
        message.setText(text);
        // 邮件发送方
        message.setFrom(MdsConstant.MAIL_FROM);
        // 发送邮件
        mailSender.send(message);

        // 将邮件数据保存到数据库
        Mail mail = new Mail();
        mail.setFrom(MdsConstant.MAIL_FROM).setTo(to).setSubject(MdsConstant.MAIL_SUBJECT).setText(text);
        baseMapper.insert(mail);
    }
}
