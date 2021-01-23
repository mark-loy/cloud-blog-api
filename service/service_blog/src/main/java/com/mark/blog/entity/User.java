package com.mark.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * ?û??
 * </p>
 *
 * @author mark
 * @since 2021-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blog_user")
@ApiModel(value="User对象", description="?û??")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "?û?id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "?û???")
    private String username;

    @ApiModelProperty(value = "???")
    private String password;

    @ApiModelProperty(value = "?ǳ")
    private String nickname;

    @ApiModelProperty(value = "?û?ͷ?")
    private String avatar;

    @ApiModelProperty(value = "?û?ǩ??")
    private String sign;

    @ApiModelProperty(value = "?߼?ɾ?? 1??true????ɾ???? 0??false??δɾ??")
    private Boolean isDeleted;

    @ApiModelProperty(value = "????ʱ?")
    private Date gmtCreate;

    @ApiModelProperty(value = "????ʱ?")
    private Date gmtModified;


}
