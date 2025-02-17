package com.github.plokhotnyuk.jsoniter_scala.benchmark

import java.nio.charset.StandardCharsets.UTF_8
import java.time.OffsetTime
import com.avsystem.commons.serialization.json._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.AVSystemCodecs._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.BorerJsonEncodersDecoders._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.DslPlatformJson._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.JacksonSerDesers._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.JsoniterScalaCodecs._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.PlayJsonFormats._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.SprayFormats._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.UPickleReaderWriters._
import com.github.plokhotnyuk.jsoniter_scala.benchmark.WeePickleFromTos._
import com.github.plokhotnyuk.jsoniter_scala.core._
import com.rallyhealth.weejson.v1.jackson.FromJson
import com.rallyhealth.weepickle.v1.WeePickle.ToScala
import io.circe.Decoder
import io.circe.parser._
import org.openjdk.jmh.annotations.Benchmark
import play.api.libs.json.Json
import spray.json._
import zio.json.DecoderOps

class ArrayOfOffsetTimesReading extends ArrayOfOffsetTimesBenchmark {
  @Benchmark
  def avSystemGenCodec(): Array[OffsetTime] = JsonStringInput.read[Array[OffsetTime]](new String(jsonBytes, UTF_8))

  @Benchmark
  def borer(): Array[OffsetTime] = io.bullet.borer.Json.decode(jsonBytes).to[Array[OffsetTime]].value

  @Benchmark
  def circe(): Array[OffsetTime] = decode[Array[OffsetTime]](new String(jsonBytes, UTF_8)).fold(throw _, identity)

  @Benchmark
  def circeJawn(): Array[OffsetTime] = io.circe.jawn.decodeByteArray[Array[OffsetTime]](jsonBytes).fold(throw _, identity)

  @Benchmark
  def circeJsoniter(): Array[OffsetTime] = {
    import com.github.plokhotnyuk.jsoniter_scala.circe.CirceCodecs._
    import com.github.plokhotnyuk.jsoniter_scala.benchmark.CirceJsoniterCodecs._

    Decoder[Array[OffsetTime]].decodeJson(readFromArray[io.circe.Json](jsonBytes)).fold(throw _, identity)
  }

  @Benchmark
  def dslJsonScala(): Array[OffsetTime] = dslJsonDecode[Array[OffsetTime]](jsonBytes)

  @Benchmark
  def jacksonScala(): Array[OffsetTime] = jacksonMapper.readValue[Array[OffsetTime]](jsonBytes)

  @Benchmark
  def jsoniterScala(): Array[OffsetTime] = readFromArray[Array[OffsetTime]](jsonBytes)

  @Benchmark
  def playJson(): Array[OffsetTime] = Json.parse(jsonBytes).as[Array[OffsetTime]]

  @Benchmark
  def playJsonJsoniter(): Array[OffsetTime] = {
    import com.evolutiongaming.jsonitertool.PlayJsonJsoniter._

    deserialize(jsonBytes).fold(throw _, _.as[Array[OffsetTime]])
  }

  @Benchmark
  def sprayJson(): Array[OffsetTime] = JsonParser(jsonBytes).convertTo[Array[OffsetTime]]

  @Benchmark
  def uPickle(): Array[OffsetTime] = read[Array[OffsetTime]](jsonBytes)

  @Benchmark
  def weePickle(): Array[OffsetTime] = FromJson(jsonBytes).transform(ToScala[Array[OffsetTime]])

  @Benchmark
  def zioJson(): Array[OffsetTime] = new String(jsonBytes, UTF_8).fromJson[Array[OffsetTime]].fold(sys.error, identity)
}