package com.baymin.restroomapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@ApiModel(value = "AppointKey:查询字段")
public class AppointKey implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ApiModelProperty(value = "查询字段",example = "三星s10+")
    private String key;
    @ApiModelProperty(value = "状态",example = "0：厕所关闭|1：厕所对外开放")
    private Integer status;
    @JsonIgnore
    private Date createTime = new Date();

    private Date updateTime = new Date();

    @OneToMany(mappedBy = "appointKey",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
//    @JsonIgnore
    @JsonBackReference
    @ApiModelProperty(value = "对应的查询数据")
    private List<Fishs> fishs = new ArrayList<>();


    public void removeFishs(Fishs comment) {
        fishs.remove(comment);
        comment.setAppointKey(null);
    }

}
