package com.hao.haoaiagent.rag;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.springframework.ai.vectorstore.pgvector.PgVectorStore.PgDistanceType.COSINE_DISTANCE;

@Configuration
@Profile("pgvector")
public class PgVectorStoreConfig {

    @Bean
    public VectorStore pgVectorStore(JdbcTemplate jdbcTemplate, EmbeddingModel dashscopeEmbeddingModel) {
        return buildVectorStore(jdbcTemplate, dashscopeEmbeddingModel);
    }

    @Bean
    public VectorStore pgVectorDocumentStore(JdbcTemplate jdbcTemplate, EmbeddingModel dashscopeEmbeddingModel) {
        return buildVectorStore(jdbcTemplate, dashscopeEmbeddingModel);
    }

    private PgVectorStore buildVectorStore(JdbcTemplate jdbcTemplate, EmbeddingModel dashscopeEmbeddingModel) {
        return PgVectorStore.builder(jdbcTemplate, dashscopeEmbeddingModel)
                .dimensions(1024)
                .distanceType(COSINE_DISTANCE)
                .indexType(PgVectorStore.PgIndexType.HNSW)
                .initializeSchema(true)
                .schemaName("public")
                .vectorTableName("vector_store_1024")
                .maxDocumentBatchSize(10000)
                .build();
    }
}
