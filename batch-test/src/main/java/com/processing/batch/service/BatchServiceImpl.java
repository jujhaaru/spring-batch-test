package com.processing.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BatchServiceImpl implements BatchService {


    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("taskletsJob")
    private Job job;

    @Override


    public String processJobs(List<Long> details) {
        details.forEach(obj -> {
            try {
                Map<String, JobParameter> map = new HashMap<>();
                JobParameter jp = new JobParameter(obj.longValue());
                map.put("salesFeedNumber", jp);
                JobParameter timeAsParameter = new JobParameter(System.currentTimeMillis());
                map.put("currentTime", timeAsParameter);
                JobParameters parameters = new JobParameters(map);

                JobExecution execution = jobLauncher.run(job, parameters);
                System.out.println(execution.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        return "Successfully executed";
    }
}
