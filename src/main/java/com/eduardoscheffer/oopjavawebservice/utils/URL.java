package com.eduardoscheffer.oopjavawebservice.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class URL {
    public static String decodeParam(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }

    public static String[] getNullPropertyNames(Object source) { //  usado para obter os nomes das propriedades nulas da source e retornar uma array com os nomes
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyNonNullProperties(Object source, Object target) { // somente as propriedades cujos nomes estão na lista de nomes de propriedades nulas não serão copiadas. Em outras palavras, apenas as propriedades não nulas em source serão copiadas para target.

        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));

    }

}
