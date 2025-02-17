#!/bin/bash
sbt -batch -java-home /usr/lib/jvm/graalvm-ee-java17 -Dmacro.settings=print-codecs clean 'jsoniter-scala-benchmarkJVM/jmh:run -p size=128 -prof gc -rf json -rff graalvm-ee-java17.json .*' 2>&1 | tee graalvm-ee-java17.txt
sbt -batch -java-home /usr/lib/jvm/graalvm-ee-java11 -Dmacro.settings=print-codecs clean 'jsoniter-scala-benchmarkJVM/jmh:run -p size=128 -prof gc -rf json -rff graalvm-ee-java11.json .*' 2>&1 | tee graalvm-ee-java11.txt
sbt -batch -java-home /usr/lib/jvm/graalvm-ce-java17 -Dmacro.settings=print-codecs clean 'jsoniter-scala-benchmarkJVM/jmh:run -p size=128 -prof gc -rf json -rff graalvm-ce-java17.json .*' 2>&1 | tee graalvm-ce-java17.txt
sbt -batch -java-home /usr/lib/jvm/graalvm-ce-java11 -Dmacro.settings=print-codecs clean 'jsoniter-scala-benchmarkJVM/jmh:run -p size=128 -prof gc -rf json -rff graalvm-ce-java11.json .*' 2>&1 | tee graalvm-ce-java11.txt
sbt -batch -java-home /usr/lib/jvm/openjdk-19 -Dmacro.settings=print-codecs clean 'jsoniter-scala-benchmarkJVM/jmh:run -p size=128 -prof gc -rf json -rff openjdk-19.json .*' 2>&1 | tee openjdk-19.txt
sbt -batch -java-home /usr/lib/jvm/zulu-17 -Dmacro.settings=print-codecs clean 'jsoniter-scala-benchmarkJVM/jmh:run -p size=128 -prof gc -rf json -rff zulu-17.json .*' 2>&1 | tee zulu-17.txt
sbt -batch -java-home /usr/lib/jvm/zulu-11 -Dmacro.settings=print-codecs clean 'jsoniter-scala-benchmarkJVM/jmh:run -p size=128 -prof gc -rf json -rff zulu-11.json .*' 2>&1 | tee zulu-11.txt
