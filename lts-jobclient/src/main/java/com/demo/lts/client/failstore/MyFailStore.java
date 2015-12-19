package com.demo.lts.client.failstore;

import com.lts.core.domain.KVPair;
import com.lts.core.failstore.FailStore;
import com.lts.core.failstore.FailStoreException;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 实现自己的FailStore机制来进行节点容错，类似于LeveldbFailStore、RocksdbFailStore等
 * Created by xuliugen on 15/12/19.
 */
public class MyFailStore implements FailStore {

    public String getPath() {
        return null;
    }

    public void open() throws FailStoreException {

    }

    public void put(String key, Object value) throws FailStoreException {

    }

    public void delete(String key) throws FailStoreException {

    }

    public void delete(List<String> keys) throws FailStoreException {

    }

    public <T> List<KVPair<String, T>> fetchTop(int size, Type type) throws FailStoreException {
        return null;
    }

    public void close() throws FailStoreException {

    }

    public void destroy() throws FailStoreException {

    }
}
