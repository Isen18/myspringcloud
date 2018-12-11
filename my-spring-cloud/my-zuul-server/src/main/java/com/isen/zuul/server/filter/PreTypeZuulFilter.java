package com.isen.zuul.server.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Isen
 * @date 2018/9/19 17:11
 * @since 1.0
 */
public class PreTypeZuulFilter extends ZuulFilter {
    protected Logger logger = LoggerFactory.getLogger(PreTypeZuulFilter.class);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        this.logger.info("This is pre-type zuul filter.");
        return null;
    }
}