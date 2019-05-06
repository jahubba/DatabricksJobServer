package jahubba.databricks.spark.jobserver

import com.typesafe.config.Config
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import spark.jobserver._
import spark.jobserver.context._
import spark.jobserver.util.SparkJobUtils

class DatabricksSparkContextFactory extends ScalaContextFactory {

  type C = SparkContext with ContextLike

  def makeContext(sparkConf: SparkConf, config: Config, contextName: String): C = {
    val spark = SparkSession.builder()
      .master("local")
      .getOrCreate()

    val sc = new SparkContext(sparkConf) with ContextLike {
      def sparkContext: SparkContext = this

      override def addJar(path: String) {
        if (!path.endsWith("spark-job-server.jar")) super.addJar(path)
      }
    }

    for ((k, v) <- SparkJobUtils.getHadoopConfig(config)) sc.hadoopConfiguration.set(k, v)
    sc
  }

  def isValidJob(job: api.SparkJobBase): Boolean = {
    job.isInstanceOf[SparkJob] || job.isInstanceOf[api.SparkJob]
  }

}
