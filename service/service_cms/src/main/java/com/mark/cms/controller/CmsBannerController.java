package com.mark.cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mark.base.valid.Group;
import com.mark.cms.entity.CmsBanner;
import com.mark.cms.entity.vo.BannerFormVO;
import com.mark.cms.service.CmsBannerService;
import com.mark.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * banner表 前端控制器
 * </p>
 *
 * @author mark
 * @since 2021-01-21
 */
@Api(value = "banner管理", tags = {"banner管理服务接口"})
@RestController
@RequestMapping("api/cms/banner")
public class CmsBannerController {

    @Resource
    private CmsBannerService bannerService;


    /**
     * 保存banner信息
     * @param bannerFormVO banner表单对象
     * @return Result
     */
    @ApiOperation("保存banner信息")
    @PostMapping("/")
    public Result saveBanner(@ApiParam("banner表单对象") @Validated({Group.Add.class}) @RequestBody BannerFormVO bannerFormVO) {
        CmsBanner cmsBanner = new CmsBanner();
        BeanUtils.copyProperties(bannerFormVO, cmsBanner);
        bannerService.save(cmsBanner);
        return Result.ok();
    }

    /**
     * 分页查询banner信息
     * @param current 当前页
     * @param limit 当页显示数
     * @return Result
     */
    @ApiOperation("分页查询banner信息")
    @GetMapping("/{current}/{limit}")
    public Result getBannerPage(@ApiParam("当前页") @PathVariable("current") Long current,
                                @ApiParam("当页显示数") @PathVariable("limit") Long limit) {
        if (current < 1 || limit < 1) {
            return Result.error().message("分页参数不合法");
        }
        // 构建分页对象
        Page<CmsBanner> bannerPage = new Page<>(current, limit);
        QueryWrapper<CmsBanner> bannerWrapper = new QueryWrapper<>();
        bannerWrapper.orderByDesc("gmt_create");
        // 执行分页
        bannerService.page(bannerPage, bannerWrapper);

        // 获取分页数据
        List<CmsBanner> banners = bannerPage.getRecords();
        long total = bannerPage.getTotal();
        return Result.ok().data("banners", banners).data("total", total);
    }

    /**
     * 根据id获取banner信息
     * @param bannerId bannerId
     * @return Result
     */
    @ApiOperation("根据id获取banner信息")
    @GetMapping("/{bid}")
    public Result getBannerById(@ApiParam("bannerId") @PathVariable("bid") String bannerId) {
        CmsBanner cmsBanner = bannerService.getById(bannerId);
        return Result.ok().data("banner", cmsBanner);
    }

    /**
     * 修改banner
     * @param bannerFormVO banner表单对象
     * @return Result
     */
    @ApiOperation("修改banner")
    @PutMapping("/")
    public Result updateBanner(@ApiParam("banner表单对象") @Validated({Group.Update.class}) @RequestBody BannerFormVO bannerFormVO) {
        CmsBanner cmsBanner = new CmsBanner();
        BeanUtils.copyProperties(bannerFormVO, cmsBanner);
        bannerService.updateById(cmsBanner);
        return Result.ok();
    }

    /**
     * 根据id删除banner信息
     * @param bannerId bannerId
     * @return Result
     */
    @ApiOperation("根据id删除banner信息")
    @DeleteMapping("/{bid}")
    public Result deleteBanner(@ApiParam("bannerId") @PathVariable("bid") String bannerId) {
        bannerService.removeById(bannerId);
        return Result.ok();
    }

    /**
     * 批量删除banner信息
     * @param bannerIds bannerId集合
     * @return Result
     */
    @ApiOperation("批量删除banner信息")
    @DeleteMapping("/")
    public Result deleteBatchBanner(@ApiParam("bannerId集合") @RequestParam("bannerIds") List<String> bannerIds) {
        bannerService.removeByIds(bannerIds);
        return Result.ok();
    }
}

