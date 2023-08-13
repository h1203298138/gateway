/**
 * 版权所有(C)，华子不脱发科技有限公司，2023，所有权利保留。
 * <p>
 * 项目名： hua-project
 * 文件名： About.java
 * 模块说明：
 * 修改历史：
 * 2023年07月12日 - Hedh - 创建。
 */
package com.hua.gateway.healthy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hedh
 * @since 1.0
 */
@RefreshScope
@RestController
public class About {
  @Value("${test.appId:}")
  private String appId;

  @GetMapping(value = "about", produces = "application/json;charset=utf-8")
  public Map<String, Object> about() {
    Map<String, Object> map = new HashMap<>();
    map.put("data", "success");
    map.put("code", "20000");
    return map;
  }

  @GetMapping(value = "config", produces = "application/json;charset=utf-8")
  public Map<String, Object> config() {
    Map<String, Object> map = new HashMap<>();
    map.put("data", appId);
    map.put("code", "20000");
    return map;
  }
}
