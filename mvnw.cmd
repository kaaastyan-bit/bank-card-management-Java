@echo off
setlocal enabledelayedexpansion

set MAVEN_OPTS=-Xmx1024m
set MAVEN_HOME=%~dp0.mvn\wrapper\maven

if not exist "%MAVEN_HOME%" (
    echo Downloading Maven...
    mkdir "%MAVEN_HOME%" 2>nul
    echo Downloading from https://archive.apache.org/dist/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip
    powershell -Command "& { [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip' -OutFile '%TEMP%\maven.zip' }"
    echo Extracting...
    powershell -Command "Expand-Archive -Path '%TEMP%\maven.zip' -DestinationPath '%MAVEN_HOME%\..' -Force"
    powershell -Command "Move-Item '%MAVEN_HOME%\..\apache-maven-3.9.9\*' '%MAVEN_HOME%\' -Force"
    del "%TEMP%\maven.zip" 2>nul
    echo Maven downloaded and extracted successfully
)

"%MAVEN_HOME%\bin\mvn.cmd" %*
