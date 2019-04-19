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
