package com.novbank.store.domain.base.resource;

import java.util.regex.Pattern;

/**
 * Created by Cao Ke on 2015/4/21.
 */
public class ResourceUtils  {
    public final static String SEPARATOR = ":";
    public final static Pattern URL_PATTERN = Pattern.compile("^(.+?):([a-zA-Z_]+):([^:]+)$");
}
