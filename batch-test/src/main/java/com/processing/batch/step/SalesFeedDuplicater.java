/**
 * 
 */
package com.processing.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author 5256313
 *
 */
public class SalesFeedDuplicater implements Tasklet, StepExecutionListener {

	private String salesFeedNumber;
	
	private final Logger LOG = LoggerFactory.getLogger(SalesFeedDuplicater.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		

	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution
		  .getJobExecution()
		  .getExecutionContext()
		  .put("feedDetails", this.salesFeedNumber);
		return null;
	}

}
