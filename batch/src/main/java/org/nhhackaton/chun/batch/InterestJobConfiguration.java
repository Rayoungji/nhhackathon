package org.nhhackaton.chun.batch;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.chun.batch.item.InterestItemProcessor;
import org.nhhackaton.chun.batch.item.InterestItemReader;
import org.nhhackaton.chun.batch.item.InterestItemWriter;
import org.nhhackaton.loan.entity.Loan;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class InterestJobConfiguration extends DefaultBatchConfigurer {

    private final InterestItemReader interestItemReader;
    private final InterestItemProcessor interestItemProcessor;
    private final InterestItemWriter interestItemWriter;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobLauncher jobLauncher;

    private final DataSource dataSource;

    @Bean
    public Job interestRepaymentJob() {
        return jobBuilderFactory.get("apiCallJob")
                .start(interestRepaymentStep())
                .build();
    }


    public Step interestRepaymentStep() {
        return stepBuilderFactory.get("interestRepaymentStep").<Loan, Loan>chunk(3)
                .reader(interestItemReader)
                .processor(interestItemProcessor)
                .writer(interestItemWriter)
                .transactionManager(jpaTransactionManager())
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobListener();
    }


    public JpaTransactionManager jpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor("batch_partitioner_");
    }
}
