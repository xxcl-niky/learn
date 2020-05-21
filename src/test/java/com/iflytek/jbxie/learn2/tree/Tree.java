package com.iflytek.jbxie.learn2.tree;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 树
 *
 * @author jbxie
 * @create 2020/04/09 15:38
 */
@Data
public class Tree<T> {
    /**
     * 节点ID
     */
    private String itemTypeId;
    /**
     * 显示节点文本
     */
    private String itemTypeName;

    /**
     * 节点状态，open closed
     */
    private String state = "open";

    /**
     * 节点是否被选中 true false
     */
    private boolean checked = false;

    /**
     * 节点属性
     */
    private Map<String, Object> attributes;
    
    /**
     * 节点的子节点
     */
    private List<Tree<T>> childrens = new ArrayList<>();

    /**
     * 父ID
     */
    private String parentItemTypeId;

    /**
     * 是否有父节点
     */
    private boolean isParent = false;

    /**
     * 是否有子节点
     */
    private boolean isChildren = false;

    public Tree(String itemTypeId, String itemTypeName, String state, boolean checked,
                Map<String, Object> attributes, List<Tree<T>> childrens,
                boolean isParent, boolean isChildren, String parentItemTypeId) {
        super();
        this.itemTypeId = itemTypeId;
        this.itemTypeName = itemTypeName;
        this.state = state;
        this.checked = checked;
        this.attributes = attributes;
        this.childrens = childrens;
        this.isParent = isParent;
        this.isChildren = isChildren;
        this.parentItemTypeId = parentItemTypeId;
    }

    public Tree() {
        super();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}