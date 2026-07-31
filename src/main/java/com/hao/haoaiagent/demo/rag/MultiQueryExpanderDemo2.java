package com.hao.haoaiagent.demo.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description :查询扩展器 Demo  每次调用
 * @date : 2026/7/31 09:43
 */

@Component
public class MultiQueryExpanderDemo2 {

    //构造函数
    private final MultiQueryExpander queryExpander;

    public MultiQueryExpanderDemo2(ChatModel dashscopeChatModel) {
        ChatClient.Builder builder = ChatClient.builder(dashscopeChatModel);
        this.queryExpander = MultiQueryExpander.builder()
                .chatClientBuilder(builder)
                .numberOfQueries(3)
                .build();
    }

    public List<Query> expand(String query) {
        return queryExpander.expand(new Query(query));
    }


}
