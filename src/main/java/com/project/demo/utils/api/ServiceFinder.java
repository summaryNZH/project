package com.project.demo.utils.api;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Iterables;
import com.project.demo.utils.api.management.ServerManagement;


import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;


public class ServiceFinder<T> {

    private static final Map<Class, String> errorMessages = new HashMap<Class, String>();

    static {
        errorMessages.put(ServerManagement.class, "请检查是否引用了common-core，并且common各个包版本必须一致");
    }

    private static final ConcurrentHashMap<Class<?>, ServiceFinder<?>> instanceMap = new ConcurrentHashMap<Class<?>, ServiceFinder<?>>();

    private final Supplier<T> supplier;

    private ServiceFinder(final Class<T> clz) {
        supplier = Suppliers.memoize(() -> {
            ServiceLoader<T> loader = ServiceLoader.load(clz);
            return Iterables.getFirst(loader, null);
        });
    }


    public static <T> T getService(Class<T> clz) {
        T instance = getServiceWithoutCheck(clz);
        Preconditions.checkNotNull(instance, errorMessages.get(clz));
        return instance;
    }

    public static <T> T getServiceWithoutCheck(Class<T> clz) {
        Preconditions.checkNotNull(clz);
        Preconditions.checkArgument(clz.isInterface(), "clz is not a interface");
        ServiceFinder<T> serviceFinder = (ServiceFinder<T>) instanceMap.get(clz);
        if (serviceFinder == null) {
            instanceMap.putIfAbsent(clz, new ServiceFinder<T>(clz));
            serviceFinder = (ServiceFinder<T>) instanceMap.get(clz);
        }
        return serviceFinder.supplier.get();
    }

}
