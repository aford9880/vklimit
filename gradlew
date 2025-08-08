#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

APP_NAME="Gradle"
APP_BASE_NAME=$(basename "$0")

# Resolve links - $0 may be a link
PRG="$0"

while [ -h "$PRG" ]; do
  ls=$(ls -ld "$PRG")
  link=$(expr "$ls" : '.*-> \(.*\)$')
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=$(dirname "$PRG")/"$link"
  fi
done

SAVED=""

# Get standard environment variables
JAVA_HOME=${JAVA_HOME:-}
JAVACMD=${JAVACMD:-}

# Setup Java command
if [ -n "$JAVA_HOME" ]; then
  if [ -x "$JAVA_HOME/bin/java" ]; then
    JAVACMD="$JAVA_HOME/bin/java"
  else
    JAVACMD="java"
  fi
else
  JAVACMD="java"
fi

if [ ! -x "$JAVACMD" ]; then
  echo "ERROR: JAVA_HOME is not defined correctly."
  echo "  Cannot execute $JAVACMD"
  exit 1
fi

# Find the real path of the script
PRGDIR=$(dirname "$PRG")

# Use the wrapper jar and properties in gradle/wrapper
CLASSPATH="$PRGDIR/gradle/wrapper/gradle-wrapper.jar"

# Execute Gradle wrapper
exec "$JAVACMD" $DEFAULT_JVM_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
