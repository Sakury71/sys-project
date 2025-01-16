package com.sakury.controller;

import com.sakury.entity.RestBean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/create")
public class CreateController {

    private final String API_URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/video-generation/video-synthesis";
    private final String TASK_URL = "https://dashscope.aliyuncs.com/api/v1/tasks/";

    // 创建视频任务
    @PostMapping("/video")
    public RestBean<String> createVideoTask(@RequestBody Map<String, Object> requestBody) {
        try {
            // 获取传入的文本（text）
            String text = (String) requestBody.get("text");

            // 检查文本是否为空
            if (text == null || text.isEmpty()) {
                return RestBean.error("请求文本不能为空");
            }

            // 创建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer sk-d98755f3c9cc4f04b8bd8731b6f98ae1");  // 替换为实际的 API Key
            headers.set("X-DashScope-Async", "enable");

            // 构造请求体，符合阿里文生视频 API 的格式
            Map<String, Object> input = new HashMap<>();
            input.put("prompt", text);

            Map<String, Object> requestPayload = new HashMap<>();
            requestPayload.put("model", "wanx2.1-t2v-turbo");
            requestPayload.put("input", input);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestPayload, headers);

            // 使用 RestTemplate 发送请求
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Map.class);

            // 检查响应状态码
            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> responseBody = response.getBody();

                // 检查是否包含 output 字段，并从中获取 task_id
                if (responseBody != null && responseBody.containsKey("output")) {
                    Map<String, Object> output = (Map<String, Object>) responseBody.get("output");
                    if (output.containsKey("task_id")) {
                        String taskId = (String) output.get("task_id");
                        return RestBean.success(taskId);
                    } else {
                        return RestBean.error("返回的结果中未包含任务ID");
                    }
                } else {
                    return RestBean.error("返回的结果中未包含输出字段");
                }
            } else {
                // 处理非200状态的返回
                return RestBean.error("创建任务失败，状态码：" + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // 捕获 HTTP 请求异常
            e.printStackTrace();
            return RestBean.error("HTTP请求错误，状态码：" + e.getStatusCode());
        } catch (Exception e) {
            // 捕获其他未知异常
            e.printStackTrace();
            return RestBean.error("请求异常，错误信息：" + e.getMessage());
        }
    }


    @GetMapping("/video/{taskId}")
    public RestBean<Map<String, Object>> queryVideoResult(@PathVariable String taskId) {
        try {
            // 创建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer sk-d98755f3c9cc4f04b8bd8731b6f98ae1");  // 替换为实际的 API Key

            // 构建 URL
            String url = TASK_URL + taskId;

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // 使用 RestTemplate 发送请求
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

            // 检查返回状态
            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> responseBody = response.getBody();

                // 检查 responseBody 是否为空，并从中获取 output
                if (responseBody != null && responseBody.containsKey("output")) {
                    Map<String, Object> output = (Map<String, Object>) responseBody.get("output");

                    // 返回 output，前端可以根据此判断是否包含 video_url
                    return RestBean.success(output);
                } else {
                    return RestBean.error("返回的结果中未包含输出字段");
                }
            } else {
                return RestBean.error("查询任务失败，状态码：" + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // 捕获 HTTP 请求异常
            e.printStackTrace();
            return RestBean.error("HTTP请求错误，状态码：" + e.getStatusCode());
        } catch (Exception e) {
            // 捕获其他未知异常
            e.printStackTrace();
            return RestBean.error("请求异常，错误信息：" + e.getMessage());
        }
    }

}

