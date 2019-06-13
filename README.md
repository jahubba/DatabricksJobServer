# DatabricksJobServer
This uses [Databricks Connect](https://docs.azuredatabricks.net/user-guide/dev-tools/db-connect.html) to allow remote execution of Spark from [Knime](https://www.knime.com/knime-extension-for-apache-spark) using [SparkJobServer](https://github.com/spark-jobserver/spark-jobserver).
### Installation
1.  Follow the instructions, [here](https://docs.azuredatabricks.net/user-guide/dev-tools/db-connect.html), to install Databricks Connect and configure Databricks Connect to execute on a remote server.  You can stop after you’ve completed step 2 when the connection test is successful.
2.  Download and unzip the latest Spark Job Server from Knime for standard Apache Spark.  As of this writing, this can be downloaded [here](http://download.knime.org/store/3.6/spark-job-server-0.7.0.3-KNIME_spark-2.3_hdp.tar.gz).
3.  Download and unzip a Spark version that matches the Databricks Spark version, 2.4.0 can be downloaded [here](https://archive.apache.org/dist/spark/spark-2.4.0/spark-2.4.0-bin-hadoop2.7.tgz).
4. Copy the jars from the Databricks Connect library into the jars folder with Spark that was downloaded above.  The location can be found with the command below.
```databricks-connect get-jar-dir```
5.  Within environment.conf file in Spark Job Server, add a new line within “context-settings” for the new custom context factory
```context-factory = jahubba.databricks.spark.jobserver.DatabricksSparkContextFactory```
6.  Within settings.sh file in Spark Job Server, modify SPARK_HOME to point to the Spark directory from above.
7.  Clone the DatabricksJobServer code [here](https://github.com/jahubba/DatabricksJobServer).
8.  Compile the DatabricksJobServer code using sbt.
```sbt compile```
9.  Update the Spark Job Server jar with the code compiled above.
```jar uf <spark-job-server-location>/spark-job-server.jar -c target/scala-2.11/classes jahubba/databricks/spark/jobserver/*```
10.  Start the Spark Job Server using the server-start.sh script included in the Spark Job Server downloaded above.
11.  Update the Knime context configuration to use the Spark Job Server started above.
