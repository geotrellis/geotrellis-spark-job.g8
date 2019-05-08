# geotrellis-spark-job.g8

This is a Giter8 template for performing ingests using
[GeoTrellis](https://github.com/locationtech/geotrellis). In addition
to loading, formatting, and saving data, this template also serves as
a reference for how to perform such operations in GeoTrellis.

## Requirements

| Requirement | Version |
|:-----------:|:-------:|
|    Spark    |  >=2.4  |
|    Scala    |   2.11  |


Along with the above requirements, the environment variable, `SPARK_HOME`, must be set.

## Setup

To setup the template, run the following command:

```sh
g8 geotrellis/geotrellis-spark-job.g8
# or
sbt new geotrellis/geotrellis-spark-job.g8
```

Once the command is run, a series of prompts regarding the new
project will be presented. These values can be changed as needed.

## Running

To run an ingest, first go to the root of the project and enter the
`sbt` console with:

```
sbt
```

Then type, `run` followed by the parameters listed bellow to perform the
ingest.

|     Command     | Description                                                       |
|:---------------:|:-----------------------------------------------------------------:|
| --outputPath    | URI of input file                                                  |
| --inputPath     | URI of output file                                                 |
| --numPartitions | Optional, the number of partitions to use during the ingest.      |

An example command would look like:

```
sbt:geotrellis-spark-job> run --inputPath /tmp/cropped.tif --outputPath file:///tmp/test-catalog
```

## EMR Configuration

By default, sbt-lighter plugin allocates only a 32Gb EBS volume, and as a consequence you may experience issues with Spark jobs completion. Consider allocating larger EBS volumes in such cases using the following configurations. These should be placed into a build.sbt file. The other option would be to use a different instance type that doesn't require EBS volumes usage.

This project uses [sbt-lighter plugin](https://github.com/pishen/sbt-lighter) to simplify EMR cluster configuration and Spark jobs deployment. It's important to remember, that some of the Amazon instances are EBS only instances, so check out [instances types description](https://aws.amazon.com/ru/ec2/instance-types/) carefully. By default, [sbt-lighter plugin](https://github.com/pishen/sbt-lighter) allocates only a 32Gb EBS volume, and as a consequence you may experience issues with Spark jobs completion. Consider allocating larger EBS volumes in such cases using the following configurations: 

```scala
// size in GBs
// setting to None would disable EBS volumes provisioning
sparkMasterEbsSize := Some(64)
sparkCoreEbsSize := Some(64)
```

These should be placed into a `build.sbt` file. The other option would be to use a different instance type that doesn't require EBS volumes usage. 
