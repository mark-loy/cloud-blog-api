package com.mark.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mark.blog.entity.Tag;
import com.mark.blog.mapper.TagMapper;
import com.mark.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author mark
 * @since 2021-01-22
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    /**
     * 条件查询及分页
     * @param tagPage 分页对象
     * @param name 标签名
     */
    @Override
    public void executeTagPage(Page<Tag> tagPage, String name) {
        // 构建条件对象
        QueryWrapper<Tag> tagWrapper = new QueryWrapper<>();
        // 判断name非空
        if (!StringUtils.isEmpty(name)) {
            tagWrapper.like("name", name);
        }
        // 执行分页查询
        baseMapper.selectPage(tagPage, tagWrapper);
    }
}
