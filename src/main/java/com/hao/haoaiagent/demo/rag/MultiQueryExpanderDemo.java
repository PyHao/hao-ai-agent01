package com.hao.haoaiagent.demo.rag;

import org.jetbrains.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description :查询扩展器 Demo  每次调用重新创建
 * @date : 2026/7/31 09:43
 */

@Component
public class MultiQueryExpanderDemo {
    //对象工厂
//    private final ObjectFactory<ChatClient.Builder> chatClientBuilderObjectFactory;
//
//    public MultiQueryExpanderDemo(ObjectFactory<ChatClient.Builder> chatClientBuilderObjectFactory) {
//        this.chatClientBuilderObjectFactory = chatClientBuilderObjectFactory;
//    }

    //构造函数
    private final ChatClient.Builder chatClientBuilder;

//    public MultiQueryExpanderDemo(ChatClient.Builder chatClientBuilder) {
//        this.chatClientBuilder = chatClientBuilder;
//    }
    public MultiQueryExpanderDemo(ChatModel dashscopeChatModel) {
        this.chatClientBuilder = ChatClient.builder(dashscopeChatModel);
    }

    //lookup
//    @Lookup("chatClientBuilder")
//    public ChatClient.@Nullable Builder lookupChatClientBuilder() {
//        // 此方法将被 Spring 重写并返回 Builder 的新实例
//        return null;
//    }

    public List<Query> expand(String query) {
        MultiQueryExpander queryExpander = MultiQueryExpander.builder()
                .chatClientBuilder(chatClientBuilder)
                .numberOfQueries(3)
                .build();
        List<Query> queries = queryExpander.expand(new Query(query));
        return queries;

    }


}
