@echo off
setlocal enabledelayedexpansion

set SRC_DIR=src\main\java
set BIN_DIR=bin

if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

echo Compiling all sources under v\ ...
set "SRC_LIST=%TEMP%\sources.txt"
if exist "%SRC_LIST%" del "%SRC_LIST%" 2>nul
for /R "%SRC_DIR%\v" %%F in (*.java) do @echo "%%F" >> "%SRC_LIST%"
javac -Xlint:none -d "%BIN_DIR%" -cp "%SRC_DIR%" @"%SRC_LIST%"
if errorlevel 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

echo Running application...
java -cp "%BIN_DIR%" v.V

del "%SRC_LIST%" 2>nul
