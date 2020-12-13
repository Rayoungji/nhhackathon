package org.nhhackaton.chun.batch;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.chun.batch.item.InterestItemProcessor;
import org.nhhackaton.chun.batch.item.InterestItemReader;
import org.nhhackaton.chun.batch.item.InterestItemWriter;
import org.nhhackaton.chun.dao.InterestRepayDAO;
import org.nhhackaton.chun.dao.InterestRepayMapper;
import org.nhhackaton.interest.entity.Interest;
import org.nhhackaton.loan.entity.Loan;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class InterestJobConfiguration extends DefaultBatchConfigurer {
    private final InterestItemProcessor interestItemProcessor;
    private final InterestItemWriter interestItemWriter;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final DataSource dataSource;

    private final int CHUNK_SIZE = 10;
    private final static String REPAY_LIST_QUERY = "SELECT members.identity, loans.interest\n" +
            "FROM test.loans\n" +
            "join members\n" +
            "on loans.member_id = members.member_id\n" +
            "WHERE end_date > now();";
    @Bean
    public Job interestRepaymentJob() {
        return jobBuilderFactory.get("interestRepaymentJob")
                .start(interestRepaymentStep())
                .build();
    }


    public Step interestRepaymentStep() {
        return stepBuilderFactory.get("interestRepaymentStep").<InterestRepayDAO, Interest>chunk(CHUNK_SIZE)
                .reader(jdbcCursorItemReader())
                .processor(interestItemProcessor)
                .writer(interestItemWriter)
                .transactionManager(jpaTransactionManager())
                .build();
    }

    public JdbcCursorItemReader<InterestRepayDAO> jdbcCursorItemReader(){
        return new JdbcCursorItemReaderBuilder<InterestRepayDAO>()
                .name("interestItemReader")
                .fetchSize(CHUNK_SIZE)
                .rowMapper(new InterestRepayMapper())
                .dataSource(dataSource)
                .sql(REPAY_LIST_QUERY)
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
