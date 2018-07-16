/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metron.spark.es;
import com.metron.conf.Config;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;


import java.io.Console;
import java.util.Scanner;


/**
 *
 * @author MichelSumbul <michelsumbul@gmail.com>
 */
public class reIndexing {
    public static void main(String[] args) {

        reIngestJson(parseInput(args));
    }

    public static void reIngestJson(Config configJob) {

        SparkConf conf = new SparkConf().setAppName(configJob.getAppName());
        conf.set("es.nodes",configJob.getEs_node());
        conf.set("es.index.auto.create", "true");

        if(configJob.getUser() != null){
            conf.set("es.net.http.auth.user",configJob.getUser());
            conf.set("es.net.http.auth.pass",configJob.getPassword());
            conf.set("es.net.ssl","true");
        }
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> rddSource;

        if (configJob.getNumPartition() > 0) {
            rddSource = sc.textFile(configJob.getSourcePath(), configJob.getNumPartition());
        } else {
            rddSource = sc.textFile(configJob.getSourcePath());
        }

        JavaEsSpark.saveJsonToEs(rddSource, configJob.getIndexName());

        sc.close();

    }

    public static Config parseInput(String[] args) {
        // --index is the ESindex name
        // --source is the HDFS source folder/file
        // --partition is the number of partition to read hdfs file
        // --appName is the application name
        // --help print the help syntax

        Config configJob = new Config();
        int checkParams = 0;

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--index")) {
                configJob.setIndexName(args[i + 1]);
                checkParams += 1;
            } else if (args[i].equals("--source")) {
                configJob.setSourcePath(args[i + 1]);
                checkParams += 1;
            } else if (args[i].equals("--partition")) {
                configJob.setNumPartition(Integer.valueOf(args[i + 1]));
            } else if (args[i].equals("--appName")) {
                configJob.setAppName(args[i + 1]);
            } else if (args[i].equals("--secure-xpack")){
                Scanner sc=new Scanner(System.in);
                System.out.print("XPack username: ");
                configJob.setUser(sc.nextLine());

               Console console =  System.console();


                System.out.print("XPack password: ");
                configJob.setPassword(new String(console.readPassword()));
              //  System.out.println(configJob.getPassword());
            } else if(args[i].equals("--es-nodes")){
                configJob.setEs_node(args[i+1]);
            }

        }
        if (checkParams != 2) {
            System.out.println("Syntax error!");
            System.out.println("the minimum needed option are:");
            System.out.println("--index indexEsName");
            System.out.println("--source HDFSSourcePath");
            System.out.println("--secure-xpack");
            System.exit(0);
        }

        return configJob;
    }
}
