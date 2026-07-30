package com.hao.haoaiagent.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 自定义 基于 token的切词器
 */
@Component
class MyTokenTextSplitter {
    public List<Document> splitDocuments(List<Document> documents) {
        TokenTextSplitter splitter = new TokenTextSplitter();
        return splitter.apply(documents);
    }

    public List<Document> splitCustomized(List<Document> documents) {
        List<Character> chinesePunct = List.of(
                '。', '！', '？', '；', '…',
                '，', '、', '：', '“', '”', '（', '）',
                '.', '!', '?', ';', ',', ':'
        );

        TokenTextSplitter splitter = new TokenTextSplitter(200, 100, 10, 5000, true,chinesePunct);
        return splitter.apply(documents);
    }
}
