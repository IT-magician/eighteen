//package com.eighteen.batchservice.batch;
//
//
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//@Configuration
//@EnableScheduling
//public class SchedulingConfig {
//
//    private final JobLauncher jobLauncher;
//
//    private final Job rankingJob;
//
//    public SchedulingConfig(JobLauncher jobLauncher, Job rankingJob) {
//        this.jobLauncher = jobLauncher;
//        this.rankingJob = rankingJob;
//    }
//
//    @Scheduled(fixedDelay = 30000)
//    public void runBatchJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("jobId", String.valueOf(System.currentTimeMillis()))
//                .toJobParameters();
//        jobLauncher.run(rankingJob, jobParameters);
//    }
//}
