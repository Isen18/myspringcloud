package com.isen.zuul.server.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Isen
 * @date 2018/9/19 17:25
 * @since 1.0
 */
public class GlobalErrorFilter extends ZuulFilter {

    protected Logger logger = LoggerFactory.getLogger(GlobalErrorFilter.class);

    @Override
    public String filterType() {
        return ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        Throwable throwable = context.getThrowable();
        this.logger.error("[ErrorFilter] error message: {}", throwable.getCause().getMessage());
        context.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        context.set("error.exception", throwable.getCause());
        return null;
    }
}
