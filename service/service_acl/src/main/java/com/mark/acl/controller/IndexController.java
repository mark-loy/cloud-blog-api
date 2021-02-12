package com.mark.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.mark.acl.service.IndexService;
import com.mark.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/2/10 12:19
 */
@Api(value = "登录页管理")
@RestController
@RequestMapping("admin/acl/index")
public class IndexController {

    @Resource
    private IndexService indexService;

    /**
     * 获取用户信息
     * @return Result
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public Result info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Result.ok().data(userInfo);
    }

    /**
     * 获取用户的菜单信息
     * @return Result
     */
    @ApiOperation("获取用户的菜单信息")
    @GetMapping("/menu")
    public Result getMenu(){
        //获取当前登录用户菜单
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Result.ok().data("permissionList", permissionList);
    }
}
