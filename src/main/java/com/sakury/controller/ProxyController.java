package com.sakury.controller;

import com.alibaba.fastjson2.JSONObject;
import com.sakury.entity.RestBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @CrossOrigin(origins = "http://localhost:5173") // 允许来自5173的跨域请求
    @PostMapping("/tts")
    public ResponseEntity<?> proxyPost(@RequestBody Map<String, Object> requestBody) {
        String targetUrl = "https://infer.acgnai.top/infer/gen";

        // 从 "prarm" 对象中提取 text 内容
        Map<String, Object> prarm = (Map<String, Object>) requestBody.get("prarm");
        String text = (String) prarm.get("text"); // 从 "prarm" 中获取 "text"

        if (text != null) {
            String firstSentence = extractFirstSentence(text);
            prarm.put("text", firstSentence); // 修改 prarm 中的 text 字段
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // 转发请求到目标 API
        ResponseEntity<?> response = restTemplate.exchange(
                targetUrl,
                HttpMethod.POST,
                entity,
                String.class
        );
        // 解析目标 API 的响应并封装为 RestBean
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            JSONObject responseBody = JSONObject.parseObject(String.valueOf(response.getBody()));
            String message = responseBody.getString("message");
            String audio = responseBody.getString("audio");

            Map<String, Object> data = Map.of("message", message, "audio", audio);
            RestBean<Map<String, Object>> restBean = RestBean.success(data);
            return ResponseEntity.ok(restBean);
        } else {
            // 如果响应失败，返回错误信息
            return ResponseEntity.status(response.getStatusCode())
                    .body(RestBean.failure(response.getStatusCodeValue(), "请求失败"));
        }
    }

    /**
     * 提取第一个中文句号结尾的句子
     *
     * @param text 输入的文本
     * @return 第一个中文句号结尾的句子
     */
    private String extractFirstSentence(String text) {
        // 正则表达式匹配第一个以中文句号结尾的句子，句号后可有空格或其他符号
        Pattern pattern = Pattern.compile("([^\n。]*?。)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim(); // 提取第一个句子并去除两边空格
        }
        return text.trim(); // 如果没有找到中文句号结尾的句子，返回原文本
    }
}



