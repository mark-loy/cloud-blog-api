package com.mark.ucenter.service;

import com.mark.base.vo.CommentMailRpcVO;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/2/5 12:15
 */
public interface MailSendService {
    /**
     * 发送验证码邮件
     * @param to 邮件接收人
     */
    void sendCodeMail(String to);

    /**
     * 发送评论回复通知邮件
     * @param commentMailVO 评论邮件实体
     */
    void sendCommentMail(CommentMailRpcVO commentMailVO);

}
