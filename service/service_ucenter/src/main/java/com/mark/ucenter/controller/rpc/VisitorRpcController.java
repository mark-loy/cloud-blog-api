package com.mark.ucenter.controller.rpc;

import com.mark.base.vo.VisitorRpcVO;
import com.mark.common.entity.Result;
import com.mark.ucenter.entity.Visitor;
import com.mark.ucenter.service.VisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/2/5 10:55
 */
@Api(value = "访客RPC服务管理")
@RestController
@RequestMapping("api/ucenter/rpc/visitor")
public class VisitorRpcController {

    @Resource
    private VisitorService visitorService;

    /**
     * 通过访客id获取访客信息
     * @param visitorId 访客id
     * @return VisitorRpcVO
     */
    @ApiOperation("通过访客id获取访客信息")
    @GetMapping("/{vid}")
    public VisitorRpcVO getVisitorId(@ApiParam("访客id") @PathVariable("vid") String visitorId) {
        Visitor visitor = visitorService.getById(visitorId);
        VisitorRpcVO visitorRpcVO = new VisitorRpcVO();
        BeanUtils.copyProperties(visitor, visitorRpcVO);
        return visitorRpcVO;
    }

}
