package org.nhhackaton.aws.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${wap.aws.s3.cores.accessKey}")
    private String ACCESS_KEY;

    @Value("${wap.aws.s3.cores.secretKey}")
    private String SECRET_KEY;

    @Value("${wap.aws.s3.cores.bucket}")
    private String BUCKET;

//    @Bean
//    public S3ConfigVO s3ConfigVO(){
//        return S3Source.createS3Source();
//    }

    @Bean
    public AmazonS3 amazonS3(){
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .build();
    }

    @Bean
    public S3Uploader s3Uploader(){
        return new S3Uploader(BUCKET, amazonS3());
    }
}
