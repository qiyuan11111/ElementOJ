package com.elementoj.common.security.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
    private final HashMap<String, String> addHeaderMap = new HashMap<>();
    private final HashSet<String> removeHeaderMap = new HashSet<>();
    public HeaderMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public void addHeader(String name, String value) {
        addHeaderMap.put(name, value);
    }

    public void removeHeader(String name) {
        removeHeaderMap.add(name);
    }

    @Override
    public String getHeader(String name) {
        return removeHeaderMap.contains(name)
                ? null
                : (addHeaderMap.containsKey(name)? addHeaderMap.get(name)
                : super.getHeader(name));
    }


    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(addHeaderMap.keySet());
        names.removeAll(removeHeaderMap);
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        if(removeHeaderMap.contains(name)){
            return Collections.emptyEnumeration();
        }
        List<String> values = Collections.list(super.getHeaders(name));
        if (addHeaderMap.containsKey(name)) {
            values.add(addHeaderMap.get(name));
        }
        return Collections.enumeration(values);
    }
}
