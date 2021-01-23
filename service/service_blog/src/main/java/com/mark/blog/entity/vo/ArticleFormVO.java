package com.mark.blog.entity.vo;

import com.mark.base.valid.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/22 17:16
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "文章对象", description = "文章表单对象")
public class ArticleFormVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    @NotBlank(message = "文章ID不能为空", groups = {Group.Update.class})
    private String id;

    @ApiModelProperty(value = "文章标题")
    @NotBlank(message = "文章标题不能为空", groups = {Group.Add.class})
    private String title;

    @ApiModelProperty(value = "发表人id")
    @NotBlank(message = "发表人ID不能为空", groups = {Group.Add.class})
    private String userId;

    @ApiModelProperty(value = "分类id")
    @NotBlank(message = "分类ID不能为空", groups = {Group.Add.class})
    private String categoryId;

    @ApiModelProperty(value = "阅读数")
    @Min(value = 0, message = "阅读数不能为负数", groups = {Group.Add.class, Group.Update.class} )
    private Integer viewCount;

    @ApiModelProperty(value = "点赞数")
    @Min(value = 0, message = "点赞数不能为负数", groups = {Group.Add.class, Group.Update.class})
    private Integer likeCount;

    @ApiModelProperty(value = "评论数")
    @Min(value = 0, message = "评论数不能为负数", groups = {Group.Add.class, Group.Update.class})
    private Integer commentCount;

    @ApiModelProperty(value = "图片标题")
    private String imageTitle;

    @ApiModelProperty(value = "背景图地址")
    private String imageUrl;

    @ApiModelProperty(value = "图片链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "文章内容")
    @NotBlank(message = "文章内容不能为空", groups = {Group.Add.class})
    private String content;

    @ApiModelProperty(value = "标签id")
    @NotEmpty(message = "请选择至少一个标签", groups = {Group.Add.class})
    private List<String> tagIds;
}
