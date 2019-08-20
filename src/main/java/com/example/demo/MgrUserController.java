package com.example.demo;
import org.apache.spark.*;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 类说明，之后空一行
 */
@RestController
public class MgrUserController {

    @RequestMapping("hello")
    public String demo(){
        return "Hello world !";
    }


    public void sprakStreamToMySqlDemo(){
        SparkConf conf = new SparkConf().setAppName("creativity").setMaster("local[*]");
        JavaStreamingContext ssc = new JavaStreamingContext(conf, new Duration(1000));
        //kafka的 topics
        //[ZK quorum], [consumer group id], [per-topic number of Kafka partitions to consume]
        Map<String,Integer> map = new HashMap<>();
        /**
         * 读取kafka第一种方式：
         *  使用此方式是Receiver接收数据，调用kafka高级api消费的，。与所有接收器一样，
         *  从Kafka通过Receiver接收的数据存储在Spark执行器中，然后由Spark Streaming启动的作业处理数据。
         *  注意问题：此方法可能会在接收消息失败时丢失数据，所以需要把数据写入到分布式文件存储系统(HDFS)
         *          故要启动该配置
         * */
        //SparkStreaming + kafka 消息接收者 MEMORY_AND_DISK_SER 使用了复制文件系统(HDFS) 启用了预读日志,
        JavaPairReceiverInputDStream<String,String> kafkaStream = KafkaUtils.createStream(ssc,"","", map, StorageLevel.MEMORY_AND_DISK_SER());
        //基于zookeeper的kafka, 在无接收器模式下是不会更新偏移量的, 可以在批处理的情况下自行更行zookeeper的偏移量
        /**
         * 读取kafka第二种方式：
         *   该方式不需要建立消费者线程，使用 createDirectStream 接口直接去读取 Kafka 的 WAL，
         *   将 Kafka 分区与 RDD 分区做一对一映射，相较于第一种方法，不需再维护一份 WAL 数据，
         *   提高了性能。读取数据的偏移量由 Spark Streaming 程序通过检查点机制自身处理，
         *   避免在程序出错的情况下重现第一种方法重复读取数据的情况，
         *   消除了 Spark Streaming 与 ZooKeeper/Kafka 数据不一致的风险。
         *   保证每条消息只会被 Spark Streaming 处理一次。
         *   以下代码片通过第二种方式读取 Kafka 中的数据：
         * */
        /**
         * JavaPairInputDStream<String, String> directKafkaStream =
         *      KafkaUtils.createDirectStream(streamingContext,
         *          [key class], [value class], [key decoder class], [value decoder class],
         *          [map of Kafka parameters], [set of topics to consume]);
         * */

    }

}