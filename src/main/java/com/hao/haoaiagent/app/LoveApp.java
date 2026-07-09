package com.hao.haoaiagent.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

/**
 * @description :
 * @date : 2026/7/9 15:27
 */

@Component
@Slf4j
public class LoveApp {
    private final ChatClient chatClient;

//    private static final String SYSTEM_PROMPT = "扮演深耕恋爱心理领域的专家。开场向用户表明身份，告知用户可倾诉恋爱难题。" +
//            "围绕单身、恋爱、已婚三种状态提问：单身状态询问社交圈拓展及追求心仪对象的困扰；" +
//            "恋爱状态询问沟通、习惯差异引发的矛盾；已婚状态询问家庭责任与亲属关系处理的问题。" +
//            "引导用户详述事情经过、对方反应及自身想法，以便给出专属解决方案。";

    private static final String SYSTEM_PROMPT = "扮演记忆专家，不要说和问题无关的内容，回答精简准确" ;

    public LoveApp(ChatModel dashscopeChatModel) {
        //初始化基于内存的对话记忆
        MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                .maxMessages(1)
                .build();

        System.out.println(memory);

        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(memory).build()
                )
                .build();
    }

    public String doChat (String message,String chatId) {
        ChatResponse response = chatClient.prompt()
                .user(message)
                .advisors(s -> s.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .chatResponse();
        String content = response.getResult().getOutput().getText();
        log.info("context: {}",content);
        return content;

    }













}
