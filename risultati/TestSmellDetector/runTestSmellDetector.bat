@echo off
:: ------------------------------------------
:: Script per eseguire TestSmellDetector.jar
:: ------------------------------------------

:: Cambia directory nella posizione dello script
cd /d "%~dp0"

echo ==========================================
echo [INFO] Avvio del Test Smell Detector
echo ==========================================

:: Verifica se il file JAR esiste nella directory corrente
echo [INFO] Controllo del file TestSmellDetector.jar...
if not exist "TestSmellDetector.jar" (
    echo [ERRORE] Il file TestSmellDetector.jar non e' stato trovato.
    echo [SUGGERIMENTO] Assicurati che il file si trovi nella stessa directory di questo script.
    pause
    exit /b 1
)

:: Verifica se il file input.csv esiste nella directory corrente
echo [INFO] Controllo del file input.csv...
if not exist "input.csv" (
    echo [ERRORE] Il file input.csv non e' stato trovato.
    echo [SUGGERIMENTO] Assicurati che il file input.csv sia presente e correttamente configurato.
    pause
    exit /b 1
)

:: Esegui il comando Java
echo [INFO] Esecuzione del Test Smell Detector...
java -jar TestSmellDetector.jar input.csv

:: Controlla se il comando è stato eseguito correttamente
if %ERRORLEVEL% NEQ 0 (
    echo [ERRORE] Il comando e' fallito con codice errore %ERRORLEVEL%.
    echo [SUGGERIMENTO] Controlla che il file JAR sia valido e che Java sia installato correttamente.
    pause
    exit /b %ERRORLEVEL%
)

echo [SUCCESSO] Il Test Smell Detector e' stato eseguito correttamente.

pause
:: Fine dello script
exit /b 0
