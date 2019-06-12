package com.processing.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.processing.batch.step.CustomerFeedDuplicater;
import com.processing.batch.step.SalesFeedDuplicater;

@Configuration
@EnableBatchProcessing
public class GRIBatchConfig {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	public JobRepository jobRepository() throws Exception {
		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
		factory.setTransactionManager(transactionManager());
		return (JobRepository) factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}

	@Bean
	public JobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository());
		return jobLauncher;
	}

	// Bean to duplicate sales feed
	@Bean
	protected Step duplicateSalesFeed() {
		return steps.get("duplicateSalesFeed").tasklet(salesFeedDuplicater()).build();
	}

	private SalesFeedDuplicater salesFeedDuplicater() {
		return new SalesFeedDuplicater();
	}

	@Bean
	protected Step duplicateCustomerFeed() {
		return steps.get("duplicateCustomerFeed").tasklet(customerFeedDuplicater()).build();
	}

	private CustomerFeedDuplicater customerFeedDuplicater() {
		return new CustomerFeedDuplicater();
	}

	@Bean
	public Job job() {
		return jobs.get("taskletsJob").start(duplicateSalesFeed()).next(duplicateCustomerFeed()).build();
	}
}
