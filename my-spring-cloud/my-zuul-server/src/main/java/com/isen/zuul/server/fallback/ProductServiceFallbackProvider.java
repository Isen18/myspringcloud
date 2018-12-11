package com.isen.zuul.server.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * @author Isen
 * @date 2018/9/19 16:50
 * @since 1.0
 */
@Component
public class ProductServiceFallbackProvider implements FallbackProvider {
    protected Logger logger = LoggerFactory.getLogger(ProductServiceFallbackProvider.class);

    @Override
    public String getRoute() {
        // 注意: 这里是route的名称，不是服务的名称，
        // 如果这里写成大写PRODUCT-SERVICE将无法起到回退作用
        // 表明是为哪个微服务提供回退，*表示为所有微服务提供回退
//        return "*";
        return "product-service";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return this.fallbackResponse();
        }
    }

    public ClientHttpResponse fallbackResponse() {
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("服务不可用，请稍后再试。".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // headers设定
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}