package com.cn.xt.mp.feedback.wxSecurity.wxentity.wxmenu;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/29 10:29
 **/
public class Button implements Serializable {
    private String type;
    private String name;
    private Button[] sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "Button{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", sub_button=" + Arrays.toString(sub_button) +
                '}';
    }
}
