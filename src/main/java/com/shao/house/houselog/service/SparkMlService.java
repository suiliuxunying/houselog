package com.shao.house.houselog.service;

import com.shao.house.houselog.mapper.CommendMapper;
import com.shao.house.houselog.mapper.RatingMapper;
import com.shao.house.houselog.mapper.RatingMapperExtend;
import com.shao.house.houselog.model.Commend;
import com.shao.house.houselog.model.Rating;
import mx4j.log.Log;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.spark.ml.recommendation.ALS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.spark.ml.linalg.*;

@Service
public class SparkMlService {
//    private static final Pattern SPACE = Pattern.compile(" ");

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SparkMlService.class);

    @Autowired
    private SparkSession sparkSession;
    @Autowired
    private CommendMapper commendMapper;

    @Autowired
    private RatingMapperExtend ratingMapperExtend;
    public int dataProcess () {
        List<Rating> list;
        list = ratingMapperExtend.getRatingList();
//        JavaRDD<Rating> rdd = sparkSession..parallelize(list);
        Dataset<Row> ratings = sparkSession.createDataFrame(list, Rating.class);
        Dataset<Row>[] splits = ratings.randomSplit(new double[]{0.8, 0.2});
        Dataset<Row> training = splits[0];
        Dataset<Row> test = splits[1];
        training.show();
        // Build the recommendation model using ALS on the training data
        ALS als = new ALS()
                .setMaxIter(5)
                .setRegParam(0.01)
                .setUserCol("uid")
                .setItemCol("hid")
                .setRatingCol("rating");
        ALSModel model = als.fit(training);
// Evaluate the model by computing the RMSE on the test data
// Note we set cold start strategy to 'drop' to ensure we don't get NaN evaluation metrics
        model.setColdStartStrategy("drop");
        Dataset<Row> predictions = model.transform(test);

        RegressionEvaluator evaluator = new RegressionEvaluator()
                .setMetricName("rmse")
                .setLabelCol("rating")
                .setPredictionCol("prediction");
        Double rmse = evaluator.evaluate(predictions);
        System.out.println("Root-mean-square error = " + rmse);
        try {
            model.save("spark/model");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("modle保存失败！");
        }

// Generate top 10 movie recommendations for each user
//        Dataset<Row> userRecs = model.recommendForAllUsers(10);
//// Generate top 10 user recommendations for each movie
//        Dataset<Row> movieRecs = model.recommendForAllItems(10);

//        userRecs.show(false);
        getreCommend();
        return 0;
    }

    public List<Commend> getreCommend(){
        List<Commend> listCommend =new ArrayList<>();
        ALSModel model=ALSModel.load("spark/model");
        // Generate top 10 movie recommendations for each user
        Dataset<Row> userRecs=model.recommendForAllUsers(10);
        userRecs.show();
        userRecs.toJavaRDD().collect().forEach((Row i) ->{
//            Commend commend =new Commend();
            Integer uid =i.getInt(0);
            for(Object o:i.getList(1)){
                String string=o.toString();
                String[] list = string.substring(1,string.length()-1).split(",");
                Commend commend = new Commend();
                commend.setUid(uid);
                commend.setHid(Integer.valueOf(list[0]));
                commend.setLevel(Double.valueOf(list[1]));
                commendMapper.insertSelective(commend);
                listCommend.add(commend);
            }

        });

        return listCommend;
    }

}
