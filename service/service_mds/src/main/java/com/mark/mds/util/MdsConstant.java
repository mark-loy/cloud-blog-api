package com.mark.mds.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/2/6 16:22
 */
@Component
public class MdsConstant implements InitializingBean {

    @Value("${spring.mail.subject}")
    private String mailSubject;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public static String MAIL_SUBJECT;

    public static String MAIL_FROM;

    @Override
    public void afterPropertiesSet() throws Exception {
        MAIL_SUBJECT = mailSubject;
        MAIL_FROM = mailFrom;
    }
}
