package com.baymin.restroomapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by baymin on 18-07-08.
 * 技能等级
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Blacklist:查询字段")
public class Blacklist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ApiModelProperty(value = "拉黑内容",example = "要的图二我")
    private String text;
    @ApiModelProperty(value = "拉黑用户名",example = "我是骗子")
    private String userNick;
    @JsonIgnore
    private Date createTime = new Date();
}
