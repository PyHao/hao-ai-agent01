package com.hao.haoaiagent.rag;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("pgvector")
@Slf4j
public class EmbeddingDimensionStartupCheck implements ApplicationRunner {

    @Resource
    private EmbeddingModel dashscopeEmbeddingModel;

    @Override
    public void run(ApplicationArguments args) {
        float[] vector = dashscopeEmbeddingModel.embed("embedding-dimension-check");
        log.info("DashScope embedding dimension: {}", vector.length);
    }
}
