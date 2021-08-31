package com.soul.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel   // 设置为swagger Model
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty("ID号")    // 设置变量描述
    int id;
    @ApiModelProperty("名字")    // 设置变量描述
    String name;
}
