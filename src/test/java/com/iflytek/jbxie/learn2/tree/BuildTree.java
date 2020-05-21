package com.iflytek.jbxie.learn2.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbxie
 * @create 2020/04/09 16:14
 */

public class BuildTree {
    public static <T> Tree<T> build(List<Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        for (Tree<T> children : nodes) {
            String pid = children.getParentItemTypeId();
            if (pid == null || "".equals(pid) || pid.equals("0")) {
                topNodes.add(children);
                continue;
            }
            for (Tree<T> parent : nodes) {
                String id = parent.getItemTypeId();
                if (id != null && id.equals(pid)) {
                    parent.getChildrens().add(children);
                    children.setParent(true);
                    parent.setChildren(true);
                    parent.setAttributes(parent.getAttributes());
                    continue;
                }
            }
        }
        Tree<T> root = new Tree<T>();
        if (topNodes.size() == 1) {
            root = topNodes.get(0);
        } else {
            root.setItemTypeId("-1");
            root.setParentItemTypeId("");
            root.setParent(false);
            root.setChildren(true);
            root.setChecked(true);
            root.setChildrens(topNodes);
            root.setItemTypeName("顶级节点");
        }
        return root;
    }
}
