package com.iflytek.jbxie.learn2.tree;

import org.springframework.beans.BeanUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 文件层级
 *
 * @author jbxie
 * @create 2020/04/09 14:43
 */

public class FileLevelTests {
    public static void main(String[] args) {
        depth();
    }

    public static void depth() {
        String [] args1 = new String[]{"a", "b", "c", "d","e", "f", "g", "h","i", "j", "k", "l","m", "n", "o", "p","q", "r", "s", "t","u", "v", "w", "x","y", "z"};
//        String [] args1 = new String[]{"a", "b", "c"};
        List<IflynoteFile> objList = new ArrayList<>();
        List<Tree<IflynoteFile>> trees = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            String temp = i + "";
            Arrays.stream(args1).forEach(e -> {
                IflynoteFile iflynoteFile = new IflynoteFile();
                String id = e + temp;
                iflynoteFile.setId(id);
                String pId = "0";
                if (temp.equals("1") && e.equals("a")) {
                    pId = "0";
                } else if (temp.equals("2") && e.equals("a")) {
//                    pId = objList.get(3 - 1 -1).getId();
                    pId = objList.get(26 - 1 - 1).getId();
                } else if (temp.equals("3") && e.equals("a")) {
//                    pId = objList.get(6 - 1 - 1).getId();
                    pId = objList.get(52 - 1 - 1).getId();
                }  else if (temp.equals("4") && e.equals("a")) {
//                    pId = objList.get(9 - 1 - 1).getId();
                    pId = objList.get(78 - 1 - 1).getId();
                } else {
                    pId = objList.get(objList.size() - 1).getId();
                }
                iflynoteFile.setPid(pId);
                String name = "name:" + e + temp;
                iflynoteFile.setName(name);
                iflynoteFile.setFormat("folder");
                iflynoteFile.setSize(0);
                objList.add(iflynoteFile);

                Tree<IflynoteFile> tree = new Tree<>();
                tree.setItemTypeId(id);
                tree.setItemTypeName(name);
                tree.setParentItemTypeId(pId);
                Map<String, Object> mp = new HashMap<>();
                mp.put("format", "folder");
                mp.put("size", 0);
                tree.setAttributes(mp);
                trees.add(tree);
            });
        }
//        String fileJson = JSON.toJSONString(objList);
//        String treesJson = JSON.toJSONString(trees);
//        System.out.println(fileJson);
//        System.out.println("*******************************************************************");
//        System.out.println(treesJson);

        LocalDateTime startTime = LocalDateTime.now();
        System.gc();
        long total = Runtime.getRuntime().totalMemory(); // byte
        long m1 = Runtime.getRuntime().freeMemory();

        Tree<IflynoteFile> t = BuildTree.build(trees);

        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        long total1 = Runtime.getRuntime().totalMemory();
        long m2 = Runtime.getRuntime().freeMemory();

//        System.out.println(t);
        System.out.println("startTime:" + startTime);
        System.out.println("endTime:" + endTime);
        System.out.println("time:" + duration.toMillis() + " ms");
        System.out.println("used before:" + (total - m1)  / 1024.0 + "kb");
        System.out.println("used after:" + (total1 - m2) / 1024.0 + "kb");
        System.out.println("program used:" + ((total1 - m2) - (total - m1)) / 1024.0 + "kb");

        System.out.println("*******************************************************************");
        LocalDateTime startTimeReverse = LocalDateTime.now();
        System.gc();
        long totalReverse = Runtime.getRuntime().totalMemory(); // byte
        long m1Reverse = Runtime.getRuntime().freeMemory();

        List<Tree<IflynoteFile>> nodes = new ArrayList<>();
        List<Tree<IflynoteFile>> treeNodes = buildReverse(t, nodes);

        LocalDateTime endTimeReverse = LocalDateTime.now();
        Duration durationReverse = Duration.between(startTimeReverse, endTimeReverse);
        long total1Reverse = Runtime.getRuntime().totalMemory();
        long m2Reverse = Runtime.getRuntime().freeMemory();

        System.out.println("startTime:" + startTimeReverse);
        System.out.println("endTime:" + endTimeReverse);
        System.out.println("time:" + durationReverse.toMillis() + " ms");
        System.out.println("used before:" + (totalReverse - m1Reverse)  / 1024.0 + "kb");
        System.out.println("used after:" + (total1Reverse - m2Reverse) / 1024.0 + "kb");
        System.out.println("program used:" + ((total1Reverse - m2Reverse) - (totalReverse - m1Reverse)) / 1024.0 + "kb");

//        System.out.println(JSON.toJSONString(treeNodes));
    }


    public static <T> List<Tree<T>> buildReverse(Tree<T> tree, List<Tree<T>> nodes) {
        if (tree == null) {
            return null;
        }
        Tree<T> tempTree = new Tree<>();
        BeanUtils.copyProperties(tree, tempTree);
        tempTree.setChildrens(null);
        nodes.add(tempTree);
        if (tree.getChildrens().size() > 0) {
            for(Tree<T> tree1: tree.getChildrens()){
                buildReverse(tree1, nodes);
            }
        }
        return nodes;
    }
}
