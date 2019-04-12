package com.baymin.restroomapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by baymin on 18-07-08.
 * 数据库对应的用户表表
 * oneToMany等参考
 * https://yq.aliyun.com/articles/372728
 * https://www.jianshu.com/p/bc0236f7dc98
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DeviceBoard:公告屏类")
public class Fishs implements Serializable {

    @Id
    @ApiModelProperty(value = "商品id", example = "123123232")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointKeyId")
    @JsonBackReference
    private AppointKey appointKey;

    @ApiModelProperty(value = "更新时间距离查询时间多久", example = "10分钟前")
    private String time;

    @ApiModelProperty(value = "商品名称", example = "三星s10+")
    private String title;

    @ApiModelProperty(value = "初始价钱", example = "6999.00")
    private Float price;

    @ApiModelProperty(value = "当前价钱", example = "5999.00")
    private Float nowPrice;

    @ApiModelProperty(value = "发布人昵称", example = "张三")
    private String userNick;

    @ApiModelProperty(value = "发布地址", example = "上海")
    private String location;

    @ApiModelProperty(value = "商品描述", example = "老子干啥油，老子手机刚买的")
    private String desc;

    @ApiModelProperty(value = "最新的状态说明", example = "降-50元")
    private String remark;

    @ApiModelProperty(value = "商品网址")
    private String url;

    @ApiModelProperty(value = "当前记录的状态",example = "0:朕已阅 1:未读 2:置顶，关注")
    private Integer status;


    @ApiModelProperty(value = "来源",example = "0:未知 1:闲鱼 2:转转")
    private Integer from=1;

    //@Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "查询时间")
    private Date searchTime = new Date();
    @ApiModelProperty(value = "创建时间")
    private Date createTime = new Date();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fishs )) return false;
        return id != null && id.equals(((Fishs) o).id);
    }
    @Override
    public int hashCode() {
        return 31;
    }
}
