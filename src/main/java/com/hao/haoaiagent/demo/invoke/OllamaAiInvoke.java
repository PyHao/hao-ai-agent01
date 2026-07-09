package com.hao.haoaiagent.demo.invoke;

import com.hao.haoaiagent.config.DashScopeProperties;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;

/**
 * @description :
 * @date : 2026/7/9 10:45
 */


public class OllamaAiInvoke implements CommandLineRunner {
    @Resource
    private ChatModel ollamaChatModel;

    @Override
    public void run(String... args) throws Exception {
        AssistantMessage assistantMessage = ollamaChatModel.call(new Prompt("你好，我是hao，我要赚大钱"))
                .getResult()
                .getOutput();
        System.out.println(assistantMessage.getText());
    }














}
