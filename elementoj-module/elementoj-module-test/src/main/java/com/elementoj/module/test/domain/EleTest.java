package com.elementoj.module.test.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("test2")
public class EleTest extends BaseEntity {
    @TableField("a")
    public Integer a;

    @TableField("b")
    public Integer b;

    @TableField("c")
    public Integer c;

    @TableField("d")
    public Integer d;

    @TableField("e")
    public String e;

    public EleTest(Integer a, Integer b, Integer c, Integer d, String e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
}
