package com.popular.welove.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 速卖通类目表
 * </p>
 *
 * @author liuhang
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dg_taobao")
public class Taobao extends Model<Taobao> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 父ID
     */
    private Integer parentId;
    /**
     * 中文
     */
    private String zh;
    /**
     * 俄语
     */
    private String ru;
    /**
     * 葡萄牙语
     */
    private String pt;
    /**
     * 英语
     */
    private String en;
    /**
     * 排序，暂未使用
     */
    private Integer sort;
    /**
     * 类目id，关联pid使用
     */
    private Integer catid;
    /**
     * 是否使用catid查询
     */
    private Integer catidUse;
    /**
     * 淘宝查询
     */
    private String query;
    /**
     * 是否使用query
     */
    private Integer queryUse;
    /**
     * 类目单元配重
     */
    private Float weight;
    /**
     * 状态
     */
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
