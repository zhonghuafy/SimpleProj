package org.fe.ek.test.model.enums;

/**
 * @program: TestProj
 * @description: 一个简单的枚举
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
public enum SendIdEnum {

    UNKNOWN(0),
    YBX(1),
    KOALA(2),
    SOMEONE(3),
    ;

    SendIdEnum(Integer id){
        this.id = id;
    }
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public static SendIdEnum getMin() {
        return YBX;
    }

    public static SendIdEnum getMax() {
        return SOMEONE;
    }

    public static SendIdEnum getById(Integer id) {
        if (id == null) {
            id = UNKNOWN.id;
        }
        for (SendIdEnum e: SendIdEnum.values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return UNKNOWN;
    }

}
