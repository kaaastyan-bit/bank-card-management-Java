@echo off
setlocal enabledelayedexpansion

set WRAPPER_JAR=".mvn\wrapper\maven-wrapper.jar"
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

if not "%JAVA_HOME%"=="" (
  set JAVA_HOME=%JAVA_HOME%
)

if not "%MAVEN_OPTS%"=="" (
  set MAVEN_OPTS=%MAVEN_OPTS%
)

java %MAVEN_OPTS% -jar %WRAPPER_JAR% %WRAPPER_LAUNCHER% %*