
Spark reingestion of Metron data in ES

The Goal of this POC is to allow the reingestion of indexed metron data in ES with the help of a spark job

The code has been tested with Spark 2.2 and ES 5.6.8
It support ES with and without authentification. SSL is not supported.

To job take specific parameter as input:
- "--index indexEsName" the ES index name
- "--source HDFSSourcePath" the hdfs file/folder to reingest in ES
- "--partition Number" is you want to change the partitionning of the RDD
- "--appName TheName" the application name to show in YARN
- "--secure-xpack" specify if the ES is secure or not. If this parameter is present when you submit the job in shell script the java app will ask for login/password

To start the job you can use as example the following spark-submit command:

/usr/hdp/current/spark2-client/bin/spark-submit --master yarn-client --class com.metron.spark.es.reIndexing /paf/jar/metronSparkReIndexing-1.0-SNAPSHOT-jar-with-dependencies.jar --index checkpoint --source /apps/metron/indexing/indexed/checkpoint/ --secure-xpack --appName checkpointReindexingES --partition 500
