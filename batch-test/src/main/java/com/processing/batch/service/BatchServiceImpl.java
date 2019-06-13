package com.processing.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {



    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("taskletsJob")
    private Job job;

    @Override
    public String processJobs(List<Integer> details) {
        details.forEach(System.out::println);

        try {
            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println(execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Successfully executed";
    }
}
