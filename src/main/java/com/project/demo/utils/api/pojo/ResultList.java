package com.project.demo.utils.api.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ResultList<E extends Serializable> implements Iterable<E>, Serializable {

    private static final long serialVersionUID = 2789771757285593580L;

    private List<E> list;

    private int count = 0;

    public ResultList() {
        this(new ArrayList<E>());
    }

    public ResultList(List<E> list) {
        this(0, list);
    }

    public ResultList(int count) {
        this(count, new ArrayList<E>());
    }

    public ResultList(int count, List<E> list) {
        if (list == null)
            list = Collections.emptyList();
        this.list = list;
        this.count = Math.max(count, list.size());
    }

    public void add(E ele) {
        list.add(ele);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}