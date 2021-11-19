package com.hope.one.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.core.Converter;

public class BeanConverter {

    private static final ConcurrentHashMap<Pair<Class<?>, Class<?>>, BeanCopier> COPIERS = new ConcurrentHashMap();

    public BeanConverter() {
    }

    private static final <S, D> D doCopy(S src, D dest, Converter converter) {
        Class<?> clsSrc = src.getClass();
        Class<?> clsDest = dest.getClass();
        BeanCopier copier = getBeanCopier(clsSrc, clsDest);
        copier.copy(src, dest, converter);
        return dest;
    }


    private static final <S, D> D doCreate(S src, Class<D> clazz, Converter converter) {
        D dest = createInstance(clazz);
        return doCopy(src, dest, converter);
    }

    private static BeanCopier getBeanCopier(Class<?> clsSrc, Class<?> clsDest) {
        Pair<Class<?>, Class<?>> key = new Pair(clsSrc, clsDest);
        BeanCopier copier = (BeanCopier)COPIERS.get(key);
        if (copier == null) {
            synchronized(COPIERS) {
                copier = (BeanCopier)COPIERS.get(key);
                if (copier == null) {
                    copier = BeanCopier.create(clsSrc, clsDest, true);
                    COPIERS.putIfAbsent(key, copier);
                }
            }
        }

        return copier;
    }

    private static <T> T createInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (ReflectiveOperationException var2) {
            throw new RuntimeException(var2);
        }
    }


    public static <S, D> D convert(S src, Class<D> clazz) {
        return JSON.parseObject(JSON.toJSONString(src), clazz);
    }

    public static <S, D> D convert(S src, TypeReference<D> tref) {
        return JSON.parseObject(JSON.toJSONString(src), tref, new Feature[0]);
    }



}
