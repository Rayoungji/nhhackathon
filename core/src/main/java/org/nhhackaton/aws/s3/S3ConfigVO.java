package org.nhhackaton.aws.s3;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class S3ConfigVO {
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;
}
