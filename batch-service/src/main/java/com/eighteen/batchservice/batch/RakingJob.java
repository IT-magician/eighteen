package com.eighteen.batchservice.batch;

import com.eighteen.batchservice.entity.AgeGender;
import com.eighteen.batchservice.entity.MyEighteen;
import com.eighteen.batchservice.entity.Ranking;
import com.eighteen.batchservice.entity.User;
import com.eighteen.batchservice.repository.AgeGenderRepository;
import com.eighteen.batchservice.repository.RankingRepository;
import com.eighteen.batchservice.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RankingJob {
    private final UserRepository userRepository;

    private final AgeGenderRepository ageGenderRepository;
    private final RankingRepository rankingRepository;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private int chunkSize;

    private void Chunk() {
        this.chunkSize = (int)userRepository.count();
    }

    @Bean
    public Job rankingJob_batchBuild() {
        Chunk();
        return jobBuilderFactory.get("rankingJob")
                .start(rankingJob_step1())
                .next(rankingJob_step2())
                .build();
    }

    @Bean
    public Step rankingJob_step1() {
        return stepBuilderFactory.get("writeTemp")
                .<User, Ranking>chunk(chunkSize)
                .reader(jpaPageJob1_dbItemReader())
                .processor(userToRankingProcessor())
                .writer(jpaPageJob1_rankingItemWriter())
                .build();
    }

    @Bean
    public Step rankingJob_step2() {
        return stepBuilderFactory.get("writeTemp")
                .<User, Ranking>chunk(chunkSize)
                .reader(jpaPageJob1_dbItemReader())
                .processor(userToRankingProcessor())
                .writer(jpaPageJob1_rankingItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<User> jpaPageJob1_dbItemReader() {
        return new JpaPagingItemReaderBuilder<User>()
                .name("jpaPageJob1_dbItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT u FROM User u ORDER BY u.id ASC")
                .build();
    }


    @Bean
    public ItemProcessor<User, Ranking> userToRankingProcessor() {
        return user -> {
            List<MyEighteen> myEighteens = user.getMyEighteens();
            String genderId = user.getGender();
            LocalDate birth = user.getBirth();
            LocalDate now = LocalDate.now();
            int age = Period.between(birth, now).getYears();
            for (MyEighteen myEighteen : myEighteens) {
                AgeGender ageGender =
                Ranking.builder()
                        .ageGender(genderId)
                        .music(myEighteen.getMusic())
                        .build();
            }
            return;
        };
    }

    @Bean
    public ItemWriter<Ranking> jpaPageJob1_rankingItemWriter() {
        return list -> {
            for(Ranking ranking: list) {
                rankingRepository.save(ranking);
            }
        };
    }
}