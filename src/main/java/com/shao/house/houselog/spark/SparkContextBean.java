package com.shao.house.houselog.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spark")
public class SparkContextBean {

//	//spark的安装地址
//	private String sparkHome = "192.168.1.237";
//	//应用的名称
//	private String appName = "sparkTest";
//	//master的地址
//	private String master = "192.168.1.237";

    //spark的安装地址
    private String sparkHome = ".";
    //应用的名称
    private String appName = "sparkTest";
    //master的地址
    private String master = "local";

    @Bean
    @ConditionalOnMissingBean(SparkConf.class)
    public SparkConf sparkConf() throws Exception {
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        return conf;
    }
    @Bean
    @ConditionalOnMissingBean(SparkSession.class)
    public SparkSession sparkSession() throws Exception {
        SparkSession session =  SparkSession.builder().appName(appName).master(master).getOrCreate();
        return session;
    }
    @Bean
    @ConditionalOnMissingBean(JavaSparkContext.class)
    public JavaSparkContext javaSparkContext() throws Exception {
        return new JavaSparkContext(sparkConf());
    }
    public String getSparkHome() {
        return sparkHome;
    }

    public void setSparkHome(String sparkHome) {
        this.sparkHome = sparkHome;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}
