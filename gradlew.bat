@echo off
setlocal

set DIRNAME=%~dp0
set APP_BASE_NAME=%~n0

if defined JAVA_HOME goto findJavaFromJavaHome

set JAVACMD=java
goto gotJava

:findJavaFromJavaHome
set JAVACMD=%JAVA_HOME%\bin\java.exe

:gotJava
if exist "%JAVACMD%" goto init

echo ERROR: JAVA_HOME is not set correctly.
echo  Cannot find java executable.
exit /b 1

:init
set CLASSPATH=%DIRNAME%gradle\wrapper\gradle-wrapper.jar

"%JAVACMD%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*