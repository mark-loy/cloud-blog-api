package com.mark.blog.service.impl;

import com.mark.blog.entity.User;
import com.mark.blog.mapper.UserMapper;
import com.mark.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ?û?? 服务实现类
 * </p>
 *
 * @author mark
 * @since 2021-01-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
