package com.seal.guava.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/6 10:40
 * @description
 **/
public class TreeToolUtils {
    /**
     * 根节点对象存放到这里
     */
    private List<RegionBeanTree> rootList;
    /**
     * 其他节点存放到这里，可以包含根节点
     */
    private List<RegionBeanTree> bodyList;

    public TreeToolUtils(List<RegionBeanTree> rootList, List<RegionBeanTree> bodyList) {
        this.rootList = rootList;
        this.bodyList = bodyList;
    }

    /**
     * 调用的方法入口
     *
     * @return
     */
    public List<RegionBeanTree> getTree() {
        if (bodyList != null && !bodyList.isEmpty()) {
            // 声明一个map，用来过滤已操作过的数据
            Map<String, String> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            // 传递根对象和一个空map
            rootList.forEach(beanTree -> getChild(beanTree, map));
            return rootList;
        }
        return null;
    }

    public void getChild(RegionBeanTree beanTree, Map<String, String> map) {
        List<RegionBeanTree> childList = Lists.newArrayList();
        bodyList.stream()
                // map内不包含子节点的code
                .filter(c -> !map.containsKey(c.getCode()))
                // 子节点的父id==根节点的code 继续循环
                .filter(c -> c.getPid().equals(beanTree.getCode()))
                .forEach(c -> {
                    // 当前节点code和父节点id
                    map.put(c.getCode(), c.getPid());
                    // 递归调用
                    getChild(c, map);
                    childList.add(c);
                });
        beanTree.setChildren(childList);
    }

    public static void main(String[] args) {
        RegionBeanTree beanTree1 = new RegionBeanTree();
        beanTree1.setCode("540000");
        beanTree1.setName("西藏省");
        // 最高节点
        beanTree1.setPid("100000");
        RegionBeanTree beanTree2 = new RegionBeanTree();
        beanTree2.setCode("540100");
        beanTree2.setName("拉萨市");
        beanTree2.setPid("540000");
        RegionBeanTree beanTree3 = new RegionBeanTree();
        beanTree3.setCode("540300");
        beanTree3.setName("昌都市");
        beanTree3.setPid("540000");
        RegionBeanTree beanTree4 = new RegionBeanTree();
        beanTree4.setCode("540121");
        beanTree4.setName("林周县");
        beanTree4.setPid("540100");
        RegionBeanTree beanTree5 = new RegionBeanTree();
        beanTree5.setCode("540121206");
        beanTree5.setName("阿朗乡");
        beanTree5.setPid("540121");
        RegionBeanTree beanTree6 = new RegionBeanTree();
        List<RegionBeanTree> rootList = new ArrayList<>();
        rootList.add(beanTree1);
        List<RegionBeanTree> bodyList = new ArrayList<>();
        bodyList.add(beanTree1);
        bodyList.add(beanTree2);
        bodyList.add(beanTree3);
        bodyList.add(beanTree4);
        bodyList.add(beanTree5);
        TreeToolUtils utils = new TreeToolUtils(rootList, bodyList);
        List<RegionBeanTree> result = utils.getTree();

        result.get(0);
        //System.out.println(JSON.toJSON(result));
    }
}
