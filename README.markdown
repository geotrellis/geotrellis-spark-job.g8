# geotrellis-spark-job.g8

This is a Giter8 template for performing ingests using
[GeoTrellis](https://github.com/locationtech/geotrellis). In addition
to loading, formatting, and saving data, this template also serves as
a reference for how to perform such operations in GeoTrellis.

## Requirements

| Requirement | Version |
|:-----------:|:-------:|
|    Spark    |  >=2.0  |
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

|     Command     |                                                        Description                                                       |
|:---------------:|:------------------------------------------------------------------------------------------------------------------------:|
|   --inputPath   | The path to the target GeoTiff to be read. This option can be set multiple times when trying to read more than one file. |
|      --name     |                                             The name of the resulting layer.                                             |
|      --zoom     |                       Optional, the max zoom level the resulting layer should have. Default is, 13.                      |
| --numPartitions |          Optional, the number of partitions to use during the ingest. Default is the number of available cores.          |
|   --outputPath  |                       The path which the ingest layer should be saved to. Must be in a URI format.                       |


An example command would look like:

```
sbt:geotrellis-spark-job> run --inputPath /tmp/cropped.tif --name test-layer --outputPath file:///tmp/test-catalog
```

### Caveats

**Note:** This template is in its early stages of development and thus
is not yet feature complete.

As of now, this template is only able to ingest spatial data to
ZoomedLayout in the WebMercator projection. Support for more
operations/control during the ingest will added at a later date.
