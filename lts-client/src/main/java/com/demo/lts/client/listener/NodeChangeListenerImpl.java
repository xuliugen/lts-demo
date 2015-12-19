package com.demo.lts.client.listener;

import com.lts.core.cluster.Node;
import com.lts.core.listener.NodeChangeListener;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by xuliugen on 15/12/8.
 */
public class NodeChangeListenerImpl implements NodeChangeListener {

    /**
     * 添加节点
     * @param nodes
     */
    public void addNodes(List<Node> nodes) {
        System.out.println("有" + nodes.size() + "个节点添加了！");
        for (Node node : nodes) {
            System.out.println("我变成了node节点了");
            System.out.println(node.getAddress());
            System.out.println(node.getClusterName());
            System.out.println(node.getCommandPort());
            System.out.println(node.getCreateTime());
            System.out.println(node.getGroup());
            System.out.println(node.getHostName());
            System.out.println(node.getIdentity());
            System.out.println(node.getIp());
        }
    }

    /**
     * 移除节点
     * @param nodes
     */
    public void removeNodes(List<Node> nodes) {
        System.out.println("有" + nodes.size() + "个节点被移除了！");
        for (Node node : nodes) {
            System.out.println("node节点被移除了！");
        }
    }
}
