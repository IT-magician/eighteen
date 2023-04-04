package com.eighteen.batchservice.batch;

import com.eighteen.batchservice.entity.*;
import com.eighteen.batchservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RankingJob {
    private final MyEighteenRepository myEighteenRepository;

    private final UserRepository userRepository;

    private final AgeGenderRepository ageGenderRepository;

    private final RankingRepository rankingRepository;

    private final TempRankingRepository tempRankingRepository;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final EntityManagerFactory entityManagerFactory;

    private final EntityManager entityManager;

    private int chunkSize;

    private Map<Music, Integer> M1 = new HashMap<>();
    private Map<Music, Integer> F1 = new HashMap<>();
    private Map<Music, Integer> M2 = new HashMap<>();
    private Map<Music, Integer> F2 = new HashMap<>();
    private Map<Music, Integer> M3 = new HashMap<>();
    private Map<Music, Integer> F3 = new HashMap<>();
    private Map<Music, Integer> M4 = new HashMap<>();
    private Map<Music, Integer> F4 = new HashMap<>();
    private Map<Music, Integer> M5 = new HashMap<>();
    private Map<Music, Integer> F5 = new HashMap<>();
    private Map<Music, Integer> M6 = new HashMap<>();
    private Map<Music, Integer> F6 = new HashMap<>();

    private void Refresh() {

        this.M1 = new HashMap<>();
        this.F1 = new HashMap<>();
        this.M2 = new HashMap<>();
        this.F2 = new HashMap<>();
        this.M3 = new HashMap<>();
        this.F3 = new HashMap<>();
        this.M4 = new HashMap<>();
        this.F4 = new HashMap<>();
        this.M5 = new HashMap<>();
        this.F5 = new HashMap<>();
        this.M6 = new HashMap<>();
        this.F6 = new HashMap<>();
    }

    @Bean
    public Job rankingJob_batchBuild() {

        Step step1 = rankingJob_step1();
        Step step2 = rankingJob_step2();

        return jobBuilderFactory.get("rankingJob")
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step rankingJob_step1() {
        this.chunkSize = (int)userRepository.count();
        return stepBuilderFactory.get("writeTemp")
                .<User, List<TempRanking>>chunk(chunkSize)
                .reader(jpaPageJob1_dbItemReader())
                .processor(userToRankingProcessor())
                .writer(jpaPageJob1_rankingItemWriter())
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        // Log statements to debug the issue
                        System.out.println("Before step execution");
                        System.out.println("Chunk size: " + chunkSize);
                    }
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
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
    public ItemProcessor<User, List<TempRanking>> userToRankingProcessor() {

        return user -> {
            List<MyEighteen> myEighteens = user.getMyEighteens();
            System.out.println(chunkSize);
            String genderId = user.getGender();
            LocalDate now = LocalDate.now();
            LocalDate birthDate = LocalDate.parse(user.getBirth());
            int age = Period.between(birthDate, now).getYears();
            if (age > 60) {
                age = 60;
            }
            else if (age < 19) {
                age = 10;
            }
            String agId = genderId + String.valueOf(age/10);
            AgeGender ageGender = ageGenderRepository.findByAgId(agId);
            if (ageGender == null) {
                log.warn("agId가 없어요 {}", agId);
                return null;
            }
            List<TempRanking> tempRankings = new ArrayList<>();
            for (MyEighteen myEighteen : myEighteens) {
                TempRanking tempRanking = TempRanking.builder()
                        .ageGender(ageGender)
                        .music(myEighteen.getMusic())
                        .build();
                tempRankings.add(tempRanking);
            }
            return tempRankings;
        };
    }

    @Bean
    public JpaItemListWriter<TempRanking> jpaPageJob1_rankingItemWriter() {

        JpaItemWriter<TempRanking> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);

        JpaItemListWriter<TempRanking> jpaItemListWriter = new JpaItemListWriter<>(writer);
        jpaItemListWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemListWriter;
    }

    public class JpaItemListWriter<T> extends JpaItemWriter<List<T>> {

        private JpaItemWriter<T> jpaItemWriter;

        public JpaItemListWriter(JpaItemWriter<T> jpaItemWriter) {
            this.jpaItemWriter = jpaItemWriter;
        }

        @Override
        public void write(List<? extends List<T>> items) {
            List<T> totalList = new ArrayList<>();

            for (List<T> list: items) {
                totalList.addAll(list);
            }

            jpaItemWriter.write(totalList);
        }
    }

    @Bean
    public Step rankingJob_step2() {
        this.chunkSize = (int)myEighteenRepository.count();
        System.out.println("---------------------------------------");
        System.out.println(chunkSize);
        System.out.println(chunkSize);
        System.out.println(chunkSize);
        System.out.println(chunkSize);
        System.out.println(chunkSize);
        System.out.println(chunkSize);
        System.out.println(chunkSize);
        System.out.println(chunkSize);
        System.out.println("---------------------------------------");
        return stepBuilderFactory.get("TempToRank")
                .<TempRanking, TempRanking>chunk(chunkSize)
                .reader(jpaPageJob2_dbItemReader())
                .processor(userToRankingProcessor2())
                .writer(jpaPageJob2_rankingItemWriter(entityManager))
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        Refresh();
                    }
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
                .build();
    }

    @Bean
    public JpaPagingItemReader<TempRanking> jpaPageJob2_dbItemReader() {
        System.out.println("---------------------------------------");
        System.out.println(chunkSize);
        System.out.println(M1);
        System.out.println(M2);
        System.out.println(M3);
        System.out.println(M4);
        System.out.println(M5);
        System.out.println(M6);
        System.out.println(F1);
        System.out.println(F2);
        System.out.println(F3);
        System.out.println(F4);
        System.out.println(F5);
        System.out.println(F6);
        System.out.println("---------------------------------------");
        return new JpaPagingItemReaderBuilder<TempRanking>()
                .name("jpaPageJob2_dbItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT t FROM TempRanking t ORDER BY t.id ASC")
                .build();
    }

    @Bean
    public ItemProcessor<TempRanking, TempRanking> userToRankingProcessor2() {

        return tempRanking -> {
            Music music = tempRanking.getMusic();
            if (tempRanking.getAgeGender().getAgId().equals("M1")) {
                M1.put(music, M1.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("F1")) {
                F1.put(music, F1.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("M2")) {
                M2.put(music, M2.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("F2")) {
                F2.put(music, F2.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("M3")) {
                M3.put(music, M3.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("F3")) {
                F3.put(music, F3.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("M4")) {
                M4.put(music, M4.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("F4")) {
                F4.put(music, F4.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("M5")) {
                M5.put(music, M5.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("F5")) {
                F5.put(music, F5.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("M6")) {
                M6.put(music, M6.getOrDefault(music, 0) + 1);
            } else if (tempRanking.getAgeGender().getAgId().equals("F6")) {
                F6.put(music, F6.getOrDefault(music, 0) + 1);
            }
            return null;
        };
    }

    @Bean
    public ItemWriter<TempRanking> jpaPageJob2_rankingItemWriter(EntityManager entityManager) {
        System.out.println("---------------------------------------");
        System.out.println(chunkSize);
        System.out.println(M1);
        System.out.println(M2);
        System.out.println(M3);
        System.out.println(M4);
        System.out.println(M5);
        System.out.println(M6);
        System.out.println(F1);
        System.out.println(F2);
        System.out.println(F3);
        System.out.println(F4);
        System.out.println(F5);
        System.out.println(F6);
        System.out.println("---------------------------------------");
        return list -> {
            rankingRepository.deleteAll();
            System.out.println(chunkSize);
            Map<Music, Integer> M1Sorted = sortByValue(M1);
            Map<Music, Integer> F1Sorted = sortByValue(F1);
            Map<Music, Integer> M2Sorted = sortByValue(M2);
            Map<Music, Integer> F2Sorted = sortByValue(F2);
            Map<Music, Integer> M3Sorted = sortByValue(M3);
            Map<Music, Integer> F3Sorted = sortByValue(F3);
            Map<Music, Integer> M4Sorted = sortByValue(M4);
            Map<Music, Integer> F4Sorted = sortByValue(F4);
            Map<Music, Integer> M5Sorted = sortByValue(M5);
            Map<Music, Integer> F5Sorted = sortByValue(F5);
            Map<Music, Integer> M6Sorted = sortByValue(M6);
            Map<Music, Integer> F6Sorted = sortByValue(F6);
            int rank = 1;
            for (Map.Entry<Music, Integer> entry : M1Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("M1");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : F1Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("F1");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : M2Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("M2");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : F2Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("F2");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : M3Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("M3");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : F3Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("F3");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : M4Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("M4");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : F4Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("F4");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : M5Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("M5");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : F5Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("F5");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : M6Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("M6");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            rank = 1;
            for (Map.Entry<Music, Integer> entry : F6Sorted.entrySet()) {
                if (rank > 10) {
                    break;
                }
                Music music = entry.getKey();
                AgeGender ageGender = ageGenderRepository.findByAgId("F6");
                Ranking ranking = Ranking.builder()
                        .ageGender(ageGender)
                        .music(music)
                        .build();
                System.out.println(ranking);
                rankingRepository.save(ranking);
            }
            Refresh();
            tempRankingRepository.deleteAll();
            entityManager.flush();
        };
    }

    private Map<Music, Integer> sortByValue(Map<Music, Integer> map) {
        List<Map.Entry<Music, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));
        Map<Music, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Music, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }



}