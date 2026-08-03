package com.hao.haoaiagent.rag;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("pgvector")
@ConditionalOnProperty(prefix = "app.pgvector", name = "load-documents", havingValue = "true")
@Slf4j
public class PgVectorDocumentInitializer implements ApplicationRunner {

    @Resource
    private LoveAppDocumentLoader loveAppDocumentLoader;

    @Resource
    private VectorStore pgVectorDocumentStore;

    @Override
    public void run(ApplicationArguments args) {
        List<Document> documents = loveAppDocumentLoader.loadMarkdowns();
        int batchSize = 10;
        int count = 0;
        for (int i = 0; i < documents.size(); i += batchSize) {
            int end = Math.min(i + batchSize, documents.size());
            pgVectorDocumentStore.add(documents.subList(i, end));
            count += end - i;
        }
        log.info("Loaded {} markdown documents into pgvector store in batches of {}", count, batchSize);
    }
}
