package com.mark.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mark.blog.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author mark
 * @since 2021-01-22
 */
public interface TagService extends IService<Tag> {

    /**
     * 条件带分页查询标签数据
     * @param tagPage 分页对象
     * @param name 标签名
     */
    void executeTagPage(Page<Tag> tagPage, String name);
}
