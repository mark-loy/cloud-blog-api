package com.mark.blog.controller;


import com.mark.blog.entity.User;
import com.mark.blog.entity.vo.UserResponseVO;
import com.mark.blog.service.UserService;
import com.mark.common.entity.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mark
 * @since 2021-01-23
 */
@RestController
@RequestMapping("api/blog/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result userLogin() {
        return Result.ok().data("token", "token");
    }

    @GetMapping("/info")
    public Result getUserInfo() {
        return Result.ok().data("name", "admin").data("avatar", "");
    }

    /**
     * 获取所有用户
     * @return Result
     */
    @ApiOperation("获取所有用户")
    @GetMapping("/all")
    public Result getUser() {
        // 查询所有的用户
        List<User> users = userService.list(null);
        // 将所有用户映射为返回的用户对象
        List<UserResponseVO> usersResponse = users.stream().map(user -> {
            // 构建返回的user对象
            UserResponseVO userResponse = new UserResponseVO();
            BeanUtils.copyProperties(user, userResponse);
            return userResponse;
        }).collect(Collectors.toList());
        return  Result.ok().data("users", usersResponse);
    }
}

