package com.eighteen.batchservice.batch;

import com.eighteen.batchservice.entity.Ranking;
import com.eighteen.batchservice.entity.User;
import com.eighteen.batchservice.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RakingJob {
    private final UserRepository userRepository;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private int chunkSize;

    private void Chunk() {
        this.chunkSize = (int)userRepository.count();
    }

    @Bean
    public Job RankingJob_batchBuild() {
        Chunk();
        return jobBuilderFactory.get("rankingJob")
                .start(RankingJob_step1()).build();
    }

    @Bean
    public Step RankingJob_step1() {
        return stepBuilderFactory.get("").<User, User>chunk(chunkSize)
                .reader(jpaPageJob1_dbItemReader())
                .writer(jpaPageJob1_printItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<User> jpaPageJob1_dbItemReader() {

        return new JpaPagingItemReaderBuilder<User>()
                .name("jpaPageJob1_dbItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT u FROM USER u order by user_id asc")
                .build();
    }

    @Bean
    public ItemWriter<User> jpaPageJob1_printItemWriter() {
        return list -> {
            for(User user: list) {
                log.debug(user.toString());
            }
        };
    }
}
