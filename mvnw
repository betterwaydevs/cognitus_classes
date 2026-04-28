#!/bin/sh
# Maven Wrapper script
# This script downloads and runs Maven if not present

MAVEN_VERSION="3.9.6"
MAVEN_HOME="$HOME/.m2/wrapper/dists/apache-maven-$MAVEN_VERSION"
MAVEN_ZIP="$MAVEN_HOME/apache-maven-$MAVEN_VERSION-bin.zip"

if [ ! -f "$MAVEN_HOME/bin/mvn" ]; then
  echo "Maven not found, downloading..."
  mkdir -p "$MAVEN_HOME"
  curl -sL "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION-bin.zip" -o "$MAVEN_ZIP"
  unzip -q "$MAVEN_ZIP" -d "$MAVEN_HOME"
  rm "$MAVEN_ZIP"
fi

exec "$MAVEN_HOME/apache-maven-$MAVEN_VERSION/bin/mvn" "$@"
