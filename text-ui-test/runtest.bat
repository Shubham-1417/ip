@ECHO OFF
chcp 65001 > nul

REM Set script directory to the project root
cd /d "%~dp0.."
setlocal EnableDelayedExpansion

REM Set project paths
SET BIN_DIR=bin
SET SRC_DIR=src\main\java

REM Create bin directory if it doesn't exist
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

REM Delete previous ACTUAL.TXT if it exists
if exist "text-ui-test\ACTUAL.TXT" del "text-ui-test\ACTUAL.TXT"

echo Compiling...
REM Gather all Java sources under v\ (including subpackages) using relative paths (no spaces)
set "SRC_LIST=%TEMP%\sources.txt"
if exist "%SRC_LIST%" del "%SRC_LIST%" 2>nul
for /R "%SRC_DIR%\v" %%F in (*.java) do (
  set "FILE=%%F"
  set "REL=!FILE:%CD%\=!"
  echo !REL!>> "%SRC_LIST%"
)
javac -Xlint:none -d "%BIN_DIR%" -cp "%SRC_DIR%" @"%SRC_LIST%"
if errorlevel 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

echo Running tests...
cd /d "%~dp0.."
java -classpath "%BIN_DIR%" v.V < "text-ui-test\input.txt" > "text-ui-test\ACTUAL.TXT" 2> "text-ui-test\error.log"
echo Test run finished.

echo Comparing with expected output...
fc /A /L "text-ui-test\ACTUAL.TXT" "text-ui-test\EXPECTED.TXT"
if %ERRORLEVEL% EQU 0 (
    echo Test result: PASSED
    exit /b 0
) else (
    echo Test result: FAILED
    exit /b 1
)

del "%SRC_LIST%" 2>nul
