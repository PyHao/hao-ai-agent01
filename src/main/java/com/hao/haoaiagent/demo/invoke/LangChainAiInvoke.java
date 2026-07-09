package com.hao.haoaiagent.demo.invoke;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.ChatModel;

public class LangChainAiInvoke {
    public static void main(String[] args) {
        ChatModel qwenChatModel = QwenChatModel.builder()
                .apiKey(TestApiKey.API_KEY)
                .modelName(TestApiKey.model)
                .build();
        String answer = qwenChatModel.chat("我是hao，这是我的项目测试，你是什么版本的？");
        System.out.println(answer);

    }
}
