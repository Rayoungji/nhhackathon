package org.nhhackaton.chun.batch;

import lombok.RequiredArgsConstructor;
import org.nhhackaton.chun.batch.item.InterestItemProcessor;
import org.nhhackaton.chun.batch.item.InterestItemReader;
import org.nhhackaton.chun.batch.item.InterestItemWriter;
import org.nhhackaton.chun.batch.item.JpaItemListWriter;
import org.nhhackaton.chun.dao.InterestReaderDAO;
import org.nhhackaton.chun.dao.InterestReaderDAOMapper;
import org.nhhackaton.interest.entity.Interest;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InterestJobConfiguration extends DefaultBatchConfigurer {
    private final InterestItemReader interestItemReader;
    private final InterestItemProcessor interestItemProcessor;
//    private final InterestItemWriter interestItemWriter;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final DataSource dataSource;
    private final EntityManagerFactory entityManagerFactory;

    private final int CHUNK_SIZE = 10;

    @Bean
    public Job interestRepaymentJob() {
        return jobBuilderFactory.get("interestRepaymentJob22")
                .start(interestRepaymentStep())
                .build();
    }



    @Bean
    public Step interestRepaymentStep() {
        return stepBuilderFactory.get("interestRepaymentStep22").<List<InterestReaderDAO>, List<Interest>>chunk(CHUNK_SIZE)
                .reader(interestItemReader)
//                .reader(jdbcCursorItemReader())
                .processor(interestItemProcessor)
                .writer(writer())
                .transactionManager(jpaTransactionManager())
//                .taskExecutor(taskExecutor())
                .build();
    }



    private JpaItemListWriter<Interest> writer(){
        JpaItemWriter<Interest> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);

        return new JpaItemListWriter<>(writer);
    }

//    public JdbcCursorItemReader<InterestReaderDAO> jdbcCursorItemReader() {
//        return new JdbcCursorItemReaderBuilder<InterestReaderDAO>()
//                .name("interestItemReader")
//                .fetchSize(CHUNK_SIZE)
//                .rowMapper(new InterestReaderDAOMapper())
//                .dataSource(dataSource)
//                .sql(REPAY_LIST_QUERY)
//                .build();
//    }

    @Bean
    public JpaItemWriter<Interest> jpaItemWriter() {
        JpaItemWriter<Interest> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobListener();
    }


    @Bean
    @Primary
    public JpaTransactionManager jpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor("batch_");
//    }

//    @Bean
//    public JobLauncherApplicationRunner jobLauncherApplicationRunner(){
//        new JobLauncherApplicationRunner()
//    }
}
