package org.nhhackaton.chun;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobLauncherCommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableJpaRepositories(basePackages = {"org.nhhackaton.loan", "org.nhhackaton.interest", "org.nhhackaton.member"})
//@EntityScan(basePackages = {"org.nhhackaton.loan", "org.nhhackaton.interest", "org.nhhackaton.member"})
@EnableJpaRepositories(basePackages = {"org.nhhackaton"})
@EntityScan(basePackages = {"org.nhhackaton"})
@SpringBootApplication
@EnableBatchProcessing
public class BatchApplication {
	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}
}


