package com.popular.welove.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuhang
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别【男】【女】【未知】
     */
    private String sex;
    /**
     * 状态【0：禁用】【1：启用】
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
