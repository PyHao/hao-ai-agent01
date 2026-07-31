package com.hao.haoaiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @description : 初始化基于内存的向量数据库 Bean
 * @date : 2026/7/24 15:25
 */

@Configuration
public class LoveAppVectorStoreConfig {
    @Resource
    private LoveAppDocumentLoader loveAppDocumentLoader;

    @Resource
    private MyTokenTextSplitter myTokenTextSplitter;

    @Resource
    private MyKeywordEnricher myKeywordEnricher;

    @Bean
    VectorStore loveAppVectorStore(EmbeddingModel dashscopeEmbeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(dashscopeEmbeddingModel).build();
        //加载文档
        List<Document> documentList = loveAppDocumentLoader.loadMarkdowns();
        //自主切分
//        List<Document> splitDocumentList = myTokenTextSplitter.splitCustomized(documentList);
        //自动补充文档的元信息
        List<Document> enrichDocumentList = myKeywordEnricher.enrichDocuments(documentList);

        simpleVectorStore.add(enrichDocumentList);
        return simpleVectorStore;
    }

}































