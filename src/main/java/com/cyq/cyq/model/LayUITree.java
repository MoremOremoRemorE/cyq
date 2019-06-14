package com.cyq.cyq.model;

import lombok.Data;

import java.util.List;

@Data
public class LayUITree {
    private String id; //ID
    private String pId; //PID
    private String name; //名字
    private String status; //状态
    private Boolean spread; //是否展开状态
    private String creatTime; //创建时间
    private String updateTime; //更新时间
    private String href; //节点链接（可选）,未设则不会跳转
    private List<LayUITree> children;
}
