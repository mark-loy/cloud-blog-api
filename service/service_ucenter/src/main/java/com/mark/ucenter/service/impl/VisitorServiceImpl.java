package com.mark.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mark.ucenter.entity.Visitor;
import com.mark.ucenter.entity.vo.VisitorQueryVO;
import com.mark.ucenter.entity.vo.VisitorRegisterVO;
import com.mark.ucenter.mapper.VisitorMapper;
import com.mark.ucenter.service.VisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 访客表 服务实现类
 * </p>
 *
 * @author mark
 * @since 2021-01-21
 */
@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {

    /**
     * 多条件组合分页查询访客数据
     * @param current 当前页
     * @param limit 当页显示数
     * @param visitorQueryVO 访客查询对象
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getVisitorQueryPage(Long current, Long limit, VisitorQueryVO visitorQueryVO) {
        // 构建分页对象
        Page<Visitor> visitorPage = new Page<>(current, limit);
        // 构建条件查询对象
        QueryWrapper<Visitor> visitorWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(visitorQueryVO.getNickname())) {
            visitorWrapper.like("nickname", visitorQueryVO.getNickname());
        }
        if (!StringUtils.isEmpty(visitorQueryVO.getIsDisabled())) {
            visitorWrapper.eq("is_disabled", visitorQueryVO.getIsDisabled());
        }
        // 执行分页
        baseMapper.selectPage(visitorPage, visitorWrapper);

        // 获取分页数据
        List<Visitor> visitors = visitorPage.getRecords();
        long total = visitorPage.getTotal();

        Map<String, Object> map = new HashMap<>(2);
        map.put("visitors", visitors);
        map.put("total", total);
        return map;
    }

    /**
     * 访客注册
     * @param visitorRegisterVO 访客注册表单
     */
    @Override
    public void toRegister(VisitorRegisterVO visitorRegisterVO) {

    }
}
