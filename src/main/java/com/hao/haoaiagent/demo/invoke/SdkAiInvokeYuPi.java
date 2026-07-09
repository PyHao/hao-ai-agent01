package com.hao.haoaiagent.demo.invoke;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import com.hao.haoaiagent.config.DashScopeProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 阿里云 灵积 AI SDK 调用
 */
@Service
public class SdkAiInvokeYuPi {
    static {
        Constants.baseHttpApiUrl="https://ws-i7k7izp1izg0thtd.cn-beijing.maas.aliyuncs.com/api/v1";
    }

    public static GenerationResult callWithMessage() throws ApiException, NoApiKeyException, InputRequiredException {

        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.")
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content("你好，我是hao，正在学习AI相关知识")
                .build();
        GenerationParam param = GenerationParam.builder()
                // 若没有配置环境变量，请用百炼API Key将下行替换为：.apiKey("sk-xxx")
                .apiKey(TestApiKey.API_KEY)
                .model(TestApiKey.model)
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .enableThinking(true)
                .build();
        return gen.call(param);
    }
    public static void main(String[] args) {
        try {
            GenerationResult result = callWithMessage();
            System.out.println("====================思考过程====================");
            System.out.println(result.getOutput().getChoices().get(0).getMessage().getReasoningContent());
            System.out.println("\n====================完整回复====================");
            System.out.println(result.getOutput().getChoices().get(0).getMessage().getContent());
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.err.println("错误信息："+e.getMessage());
        }
    }
}