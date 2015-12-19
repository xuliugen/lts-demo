package com.demo.lts.client.listener;

import com.lts.core.cluster.Node;
import com.lts.core.listener.MasterChangeListener;

import java.text.MessageFormat;

/**
 * master节点监听
 * Created by xuliugen on 15/12/8.
 */
public class MasterChangeListenerImpl implements MasterChangeListener {

    /**
     * @param master   当前已经成为master的节点
     * @param isMaster 自己是不是master
     */
    public void change(Node master, boolean isMaster) {
        if (isMaster) {
            // to do something
            System.out.println("我变成了master节点了");
        } else {
            // to do something
            System.out.println(MessageFormat.format("节点【{}】被推举为master!", master));
        }
    }
}
