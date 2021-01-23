package com.mark.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mark.base.valid.Group;
import com.mark.blog.entity.*;
import com.mark.blog.entity.vo.ArticleFormVO;
import com.mark.blog.entity.vo.ArticleQueryVO;
import com.mark.blog.entity.vo.ArticleResponseVO;
import com.mark.blog.helper.ArticleServiceHelper;
import com.mark.blog.helper.ArticleTagServiceHelper;
import com.mark.blog.mapper.ArticleMapper;
import com.mark.blog.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author mark
 * @since 2021-01-22
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private ArticleContentService articleContentService;

    @Resource
    private ArticleServiceHelper articleServiceHelper;

    @Resource
    private ArticleTagServiceHelper articleTagServiceHelper;

    /**
     * 多组合条件分页查询文章数据
     *
     * @param current      当前页
     * @param limit        当页显示数
     * @param articleQuery 查询条件对象
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getArticlePage(Long current, Long limit, ArticleQueryVO articleQuery) {

        // 构建查询条件对象
        QueryWrapper<Article> articleWrapper = new QueryWrapper<>();
        articleWrapper.orderByDesc("gmt_create");
        // 查询条件非空校验
        if (!StringUtils.isEmpty(articleQuery.getTitle())) {
            articleWrapper.like("title", articleQuery.getTitle());
        }
        if (!StringUtils.isEmpty(articleQuery.getCategoryId())) {
            articleWrapper.eq("category_id", articleQuery.getCategoryId());
        }
        if (articleQuery.getBegin() != null) {
            articleWrapper.ge("gmt_create", articleQuery.getBegin());
        }
        if (articleQuery.getEnd() != null) {
            articleWrapper.le("gmt_create", articleQuery.getEnd());
        }
        // 构建分页对象
        Page<Article> articlePage = new Page<>(current, limit);
        // 执行分页
        baseMapper.selectPage(articlePage, articleWrapper);
        // 获取文章分页数据
        List<Article> articles = articlePage.getRecords();
        // 获取总记录数
        long total = articlePage.getTotal();

        // 构建最终返回的文章集合
        List<ArticleResponseVO> articleResult = new ArrayList<>();

        // 遍历文章数据
        for (Article article : articles) {
            // 将返回的文章对象加入集合
            articleResult.add(articleServiceHelper.getArticleResponse(article));
        }

        // 最终结果封装
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("articles", articleResult);
        resultMap.put("total", total);
        return resultMap;
    }


    /**
     * 保存文章数据及关联数据
     *
     * @param articleFormVO 文章表单对象
     */
    @Override
    public void saveArticle(ArticleFormVO articleFormVO) {
        // 保存文章数据
        Article article = new Article();
        // 设置文章数据
        BeanUtils.copyProperties(articleFormVO, article);
        // 执行保存
        baseMapper.insert(article);

        // 保存文章内容
        ArticleContent articleContent = new ArticleContent();
        // 设置文章id
        articleContent.setId(article.getId());
        // 设置文章内容
        articleContent.setContent(articleFormVO.getContent());
        // 执行保存
        articleContentService.save(articleContent);

        // 保存文章标签中间表数据
        // 获取标签集合id
        List<String> tagIds = articleFormVO.getTagIds();
        // 将标签id集合转换为文章标签集合
        List<ArticleTag> articleTags = articleTagServiceHelper.tagIdsConversionTags(tagIds, article.getId());
        // 执行保存
        articleTagService.saveBatch(articleTags);
    }

    /**
     * 根据文章id查询文章数据，并返回包装结果
     * @param articleId 文章id
     * @return ArticleResponseVO
     */
    @Override
    public ArticleResponseVO getArticleById(String articleId) {
        // 获取文章数据
        Article article = baseMapper.selectById(articleId);
        // 返回包装的文章数据
        return articleServiceHelper.getArticleResponse(article);
    }

    /**
     * 修改文章数据及关联数据
     * @param articleFormVO 文章表单对象
     */
    @Override
    public void updateArticle(ArticleFormVO articleFormVO) {
        // 修改文章数据
        Article article = new Article();
        // 设置文章属性数据
        BeanUtils.copyProperties(articleFormVO, article);
        // 执行文章数据修改
        baseMapper.updateById(article);

        // 修改文章内容数据
        ArticleContent articleContent = new ArticleContent();
        articleContent.setId(articleFormVO.getId());
        articleContent.setContent(articleFormVO.getContent());
        articleContentService.updateById(articleContent);

        // 修改文章关联的标签数据
        //   1. 先删除中间表中该文章与标签的关联数据
        QueryWrapper<ArticleTag> articleTagQueryWrapper = new QueryWrapper<>();
        //      设置文章id
        articleTagQueryWrapper.eq("article_id", article.getId());
        //      执行删除
        articleTagService.remove(articleTagQueryWrapper);
        //   2. 再添加本次修改的标签数据
        //      转换标签id集合为文章标签集合
        List<ArticleTag> articleTags = articleTagServiceHelper.tagIdsConversionTags(articleFormVO.getTagIds(), article.getId());
        //      执行保存
        articleTagService.saveBatch(articleTags);
    }

    /**
     * 删除文章数据及关联数据
     * @param articleId 文章id
     */
    @Override
    public void deleteArticle(String articleId) {
        // 删除文章内容
        articleContentService.removeById(articleId);

        // 删除文章标签中间表的关联数据
        QueryWrapper<ArticleTag> articleTagWrapper = new QueryWrapper<>();
        articleTagWrapper.eq("article_id", articleId);
        articleTagService.remove(articleTagWrapper);

        // 删除文章数据
        baseMapper.deleteById(articleId);
    }

    /**
     * 批量删除文章数据及关联数据
     * @param articlesId 文章id集合
     */
    @Override
    public void deleteBatchArticle(List<String> articlesId) {
        for (String articleId : articlesId) {
            this.deleteArticle(articleId);
        }
    }
}
