package tech.icoding.commons.oss.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import tech.icoding.commons.oss.config.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author huang
 * @Date: 2022/2/23
 * @Description: OSS文件处理
 */
@Slf4j
@Component
public class BestOssTemplate implements InitializingBean {

    private final OssProperties ossProperties;
    private AmazonS3 amazonS3;

    public void createBucket(String bucketName) {
        if (!this.amazonS3.doesBucketExistV2(bucketName)) {
            this.amazonS3.createBucket(bucketName);
        }

    }

    public List<Bucket> getAllBuckets() {
        return this.amazonS3.listBuckets();
    }

    public Optional<Bucket> getBucket(String bucketName) {
        return this.amazonS3.listBuckets().stream().filter((b) -> {
            return b.getName().equals(bucketName);
        }).findFirst();
    }

    public void removeBucket(String bucketName) {
        this.amazonS3.deleteBucket(bucketName);
    }

    public List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix) {
        ObjectListing objectListing = this.amazonS3.listObjects(bucketName, prefix);
        return new ArrayList(objectListing.getObjectSummaries());
    }

    public String getObjectURL(String bucketName, String objectName, int minutes) {
        return this.getObjectURL(bucketName, objectName, Duration.ofMinutes((long)minutes));
    }

    public String getObjectURL(String bucketName, String objectName, Duration expires) {
        return this.getObjectURL(bucketName, objectName, expires, HttpMethod.GET);
    }

    public String getPutObjectURL(String bucketName, String objectName, int minutes) {
        return this.getPutObjectURL(bucketName, objectName, Duration.ofMinutes((long)minutes));
    }

    public String getPutObjectURL(String bucketName, String objectName, Duration expires) {
        return this.getObjectURL(bucketName, objectName, expires, HttpMethod.PUT);
    }

    public String getObjectURL(String bucketName, String objectName, int minutes, HttpMethod method) {
        return this.getObjectURL(bucketName, objectName, Duration.ofMinutes((long)minutes), method);
    }

    public String getObjectURL(String bucketName, String objectName, Duration expires, HttpMethod method) {
        Date expiration = Date.from(Instant.now().plus(expires));
        URL url = this.amazonS3.generatePresignedUrl((new GeneratePresignedUrlRequest(bucketName, objectName)).withMethod(method).withExpiration(expiration));
        return url.toString();
    }

    public String getObjectURL(String bucketName, String objectName) {
        URL url = this.amazonS3.getUrl(bucketName, objectName);
        return url.toString();
    }

    public S3Object getObject(String bucketName, String objectName) {
        return this.amazonS3.getObject(bucketName, objectName);
    }

    public void putObject(String bucketName, String objectName, InputStream stream) throws IOException {
        this.putObject(bucketName, objectName, stream, (long)stream.available(), "application/octet-stream");
    }

    public void putObject(String bucketName, String objectName, String contextType, InputStream stream) throws IOException {
        this.putObject(bucketName, objectName, stream, (long)stream.available(), contextType);
    }

    public PutObjectResult putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contextType);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, stream, objectMetadata);
        putObjectRequest.getRequestClientOptions().setReadLimit(Long.valueOf(size).intValue() + 1);
        return this.amazonS3.putObject(putObjectRequest);
    }

    public S3Object getObjectInfo(String bucketName, String objectName) {
        return this.amazonS3.getObject(bucketName, objectName);
    }

    public void removeObject(String bucketName, String objectName) {
        this.amazonS3.deleteObject(bucketName, objectName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(this.ossProperties.getEndpoint(), this.ossProperties.getRegion());
        AWSCredentials awsCredentials = new BasicAWSCredentials(this.ossProperties.getAccessKeyId(), this.ossProperties.getAccessKeySecret());
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        this.amazonS3 = (AmazonS3)((AmazonS3ClientBuilder)((AmazonS3ClientBuilder)((AmazonS3ClientBuilder)((AmazonS3ClientBuilder)((AmazonS3ClientBuilder) AmazonS3Client.builder().withEndpointConfiguration(endpointConfiguration)).withClientConfiguration(clientConfiguration)).withCredentials(awsCredentialsProvider)).disableChunkedEncoding()).withPathStyleAccessEnabled(this.ossProperties.getPathStyleAccess())).build();
    }

    public BestOssTemplate(final OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }
}
