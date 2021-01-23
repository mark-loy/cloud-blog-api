package com.mark.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * banner表
 * </p>
 *
 * @author mark
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CmsBanner对象", description="banner表")
public class CmsBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "bannerid")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "banner图类型；0.表示首页背景图，1.表示文章展示图")
    private Boolean type;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "文章id，只有type为1时才有值")
    private String articleId;

    @ApiModelProperty(value = "逻辑删除;0.表示未删除，1.表示已删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}
