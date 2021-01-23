package com.mark.blog.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/22 18:32
 */
@Data
@ApiModel(value = "文章结果对象")
public class ArticleResponseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "发表人名称")
    private String userName;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "阅读数")
    private Integer viewCount;

    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;

    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

    @ApiModelProperty(value = "图片标题")
    private String imageTitle;

    @ApiModelProperty(value = "背景图地址")
    private String imageUrl;

    @ApiModelProperty(value = "图片链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "标签名称集合")
    private List<String> tagsName;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
}
