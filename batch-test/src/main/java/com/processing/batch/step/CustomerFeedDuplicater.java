package com.processing.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomerFeedDuplicater implements Tasklet, StepExecutionListener {

	private Long salesFeedNumber;
	private final Logger LOG = LoggerFactory.getLogger(SalesFeedDuplicater.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {

	 ExecutionContext executionContext = stepExecution
				.getJobExecution()
				.getExecutionContext();
		this.salesFeedNumber =  (long)executionContext.get("salesFeedNumber");

	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		LOG.info(":::::::::::::::: Started Batch Execution of file ==>inside customer feed and recieved detail from feed {}",salesFeedNumber);
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

}
