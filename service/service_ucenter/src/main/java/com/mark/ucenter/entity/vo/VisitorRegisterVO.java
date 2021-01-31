package com.mark.ucenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/31 18:13
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "访客注册对象")
public class VisitorRegisterVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("验证码")
    private String code;

}
