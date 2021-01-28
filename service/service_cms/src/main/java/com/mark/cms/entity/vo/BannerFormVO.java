package com.mark.cms.entity.vo;

import com.mark.base.valid.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/24 14:27
 */
@Data
@ApiModel("banner表单对象")
public class BannerFormVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "bannerId")
    @NotBlank(message = "id不能为空", groups = {Group.Update.class})
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片地址")
    @NotBlank(message = "图片地址不能为空", groups = {Group.Add.class})
    private String imageUrl;

    @ApiModelProperty(value = "链接地址")
    private String linkUrl;
}
