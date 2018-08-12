package com.project.demo.utils.batchload;

import com.project.demo.utils.Safes;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


// PurchasePlanQuery query = new PurchasePlanQuery();
// PageRequest.Page page = new PageRequest.Page();
// page.setPageNo(0);
// page.setPageSize(pageSize);
// query.setPage(page);
//
// List<PurchasePlanAo> allPurchasePlanAoList = Lists.newArrayList(new BatchPagedLoadTemplate<PurchasePlanAo, PurchasePlanQuery>(query) {
// @Override
// protected List<PurchasePlanAo> queryData(PurchasePlanQuery purchasePlanQuery) {
//        List<PurchasePlanAo> purchasePlanAoList = purchasePlanManageRemote.selectByQuery(purchasePlanQuery);
//        return purchasePlanAoList;
//        }
//        }.execute());

public abstract class BatchLoadTemplate<D> {

    private int lastId = 0;

    private int limit;

    private Function<D, Integer> compareFunction;

    public BatchLoadTemplate(int limit, Function<D, Integer> function) {
        this.limit = limit;
        this.compareFunction = function;
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
                            List<D> data = queryData(lastId, limit);
                            if(data == null) {
                                return false;
                            }
                            if(data.size() < limit) {
                                empty = true;
                            }

                            lastId = findMaxId(data);
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

    protected abstract List<D> queryData(int lastId, int limit);

    private int findMaxId(List<D> dataList) {
        int maxId = 0;
        for (D data : Safes.of(dataList)) {
            if (compareFunction.apply(data) > maxId) {
                maxId = compareFunction.apply(data);
            }
        }

        return maxId;
    }
}
