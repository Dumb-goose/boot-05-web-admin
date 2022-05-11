package com.atguigu.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("user_tbl")  //指定bean对象对应的表名
public class User {
    @TableField(exist = false) //该属性在数据库表中不存在
    private String userName;
    @TableField(exist = false) //该属性在数据库表中不存在
    private String password;

    private Long id;
    private String name;
    private Integer age;
    private String email;

}
