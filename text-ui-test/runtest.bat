@ECHO OFF
chcp 65001 > nul

REM Set script directory
SET SCRIPT_DIR=%~dp0

REM Set project paths
SET BIN_DIR=%SCRIPT_DIR%..\bin
SET SRC_DIR=%SCRIPT_DIR%..\src\main\java

REM Create bin directory if it doesn't exist
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

REM Delete previous ACTUAL.TXT if it exists
if exist "%SCRIPT_DIR%ACTUAL.TXT" del "%SCRIPT_DIR%ACTUAL.TXT"

echo Compiling...
javac -Xlint:none -d "%BIN_DIR%" "%SRC_DIR%\V.java" "%SRC_DIR%\Task.java" "%SRC_DIR%\Todo.java" "%SRC_DIR%\Deadline.java" "%SRC_DIR%\Event.java"
if errorlevel 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

echo Running tests...
java -classpath "%BIN_DIR%" V < "%SCRIPT_DIR%input.txt" > "%SCRIPT_DIR%ACTUAL.TXT" 2> "%SCRIPT_DIR%error.log"
echo Test run finished.

echo Comparing with expected output...
FC /N "%SCRIPT_DIR%ACTUAL.TXT" "%SCRIPT_DIR%EXPECTED.TXT"

if errorlevel 1 (
    echo ********** TEST FAILED **********
    exit /b 1
) else (
    echo ********** TEST PASSED **********
    exit /b 0
)
