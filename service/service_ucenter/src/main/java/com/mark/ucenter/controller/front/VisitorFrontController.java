package com.mark.ucenter.controller.front;

import com.mark.common.entity.Result;
import com.mark.ucenter.entity.vo.VisitorLoginVO;
import com.mark.ucenter.entity.vo.VisitorRegisterVO;
import com.mark.ucenter.service.VisitorService;
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
 * @date 2021/1/31 18:10
 */
@Api(value = "访客前台管理", tags = {"访客前台接口管理"})
@RestController
@RequestMapping("api/ucenter/front")
public class VisitorFrontController {

    @Resource
    private VisitorService visitorService;

    /**
     * 访客注册
     * @param visitorRegisterVO 访客注册表单
     * @return Result
     */
    @ApiOperation("访客注册")
    @PostMapping("/register")
    public Result visitorRegister(@ApiParam("访客注册表单") @Valid @RequestBody VisitorRegisterVO visitorRegisterVO) {
        if (visitorRegisterVO == null) {
            return Result.error().message("注册信息为空");
        }
        visitorService.toRegister(visitorRegisterVO);
        return Result.ok();
    }

    /**
     * 访客登录
     * @param visitorLoginVO 访客登录表单
     * @return Result
     */
    @ApiOperation(("访客登录"))
    @PostMapping("/login")
    public Result visitorLogin(@ApiParam("访客登录表单") @Valid @RequestBody VisitorLoginVO visitorLoginVO) {
        if (visitorLoginVO == null) {
            return Result.error().message("登录信息为空");
        }
        String token = visitorService.toLogin(visitorLoginVO);
        return Result.ok().data("token", token);
    }


}
