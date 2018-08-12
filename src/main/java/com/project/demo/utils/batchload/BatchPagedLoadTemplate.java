package com.project.demo.utils.batchload;


import java.util.Iterator;
import java.util.List;

/**
 * 根据查询条件分页加载数据
 *
 * @author: jia.deng Date:17/5/26 Time:下午3:40
 */
public abstract class BatchPagedLoadTemplate<D, Q extends PageRequest> {

    private Q query;

    public BatchPagedLoadTemplate(Q query) {
        this.query = query;
    }

    public Iterable<D> execute() {
        return new Iterable<D>() {
            @Override
            public Iterator<D> iterator() {
                return new Iterator<D>() {
                    Iterator<D> iterator;
                    boolean empty = false;

                    @Override
                    public boolean hasNext() {
                        if((iterator == null || !iterator.hasNext()) && !empty) {
                            query.setPage(new PageRequest.Page(query.getPage().getPageSize(), query.getPage().getPageNo() + 1));
                            List<D> data = queryData(query);
                            if(data == null) {
                                return false;
                            }
                            if(data.size() < query.getLimit()) {
                                empty = true;
                            }

                            iterator = data.iterator();
                        }
                        return iterator.hasNext();
                    }

                    @Override
                    public D next() {
                        return iterator.next();
                    }
                };
            }
        };
    }

    protected abstract List<D> queryData(Q query);
}
