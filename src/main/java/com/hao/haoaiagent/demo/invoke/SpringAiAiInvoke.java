package com.hao.haoaiagent.demo.invoke;

import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Spring AI 框架调用AI大模型
 */

@Component
public class SpringAiAiInvoke implements CommandLineRunner {
    // 创建 DashScope API 实例
//    DashScopeApi dashScopeApi = DashScopeApi.builder()
//            .apiKey(System.getenv("AI_DASHSCOPE_API_KEY"))
//            .build();
//
//    // 创建 ChatModel
//    ChatModel chatModel = DashScopeChatModel.builder()
//            .dashScopeApi(dashScopeApi)
//            .build();

    @Resource
    private ChatModel dashscopeChatModel;


    @Override
    public void run(String... args) throws Exception {
//        AssistantMessage assistantMessage = dashscopeChatModel.call(new Prompt("你好吗，我不太好"))
//                .getResult()
//                .getOutput();
//        System.out.println(assistantMessage.getText());
    }
}
