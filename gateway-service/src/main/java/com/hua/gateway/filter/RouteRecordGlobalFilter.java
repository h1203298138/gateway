/**
 * 版权所有(C)，华子不脱发科技有限公司，2023，所有权利保留。
 * <p>
 * 项目名： hua-project
 * 文件名： RouteRecordGlobalFilter.java
 * 模块说明：
 * 修改历史：
 * 2023年07月12日 - Hedh - 创建。
 */
package com.hua.gateway.filter;

/**
 * @author Hedh
 * @since 1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Component
public class RouteRecordGlobalFilter implements GatewayFilter, Ordered {
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    URI proxyRequestUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
    long start = System.currentTimeMillis();
    return chain.filter(exchange).then(Mono.fromRunnable(() -> {
      long end = System.currentTimeMillis();
      log.info("实际调用地址为：{}，调用耗时为：{}ms", proxyRequestUri, (end - start));
    }));
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
