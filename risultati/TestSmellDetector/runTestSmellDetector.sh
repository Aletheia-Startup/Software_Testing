#!/bin/bash
# ------------------------------------------
# Script per eseguire TestSmellDetector.jar
# ------------------------------------------

# Cambia directory nella posizione dello script
cd "$(dirname "$0")"

echo "=========================================="
echo "[INFO] Avvio del Test Smell Detector"
echo "=========================================="

# Verifica se il file JAR esiste nella directory corrente
echo "[INFO] Controllo del file TestSmellDetector.jar..."
if [ ! -f "TestSmellDetector.jar" ]; then
    echo "[ERRORE] Il file TestSmellDetector.jar non è stato trovato."
    echo "[SUGGERIMENTO] Assicurati che il file si trovi nella stessa directory di questo script."
    echo "Premi un tasto per chiudere..."
    read -n 1
    exit 1
fi

# Verifica se il file input.csv esiste nella directory corrente
echo "[INFO] Controllo del file input.csv..."
if [ ! -f "input.csv" ]; then
    echo "[ERRORE] Il file input.csv non è stato trovato."
    echo "[SUGGERIMENTO] Assicurati che il file input.csv sia presente e correttamente configurato."
    echo "Premi un tasto per chiudere..."
    read -n 1
    exit 1
fi

# Esegui il comando Java
echo "[INFO] Esecuzione del Test Smell Detector..."
java -jar TestSmellDetector.jar input.csv
STATUS=$?

# Controlla se il comando è stato eseguito correttamente
if [ $STATUS -ne 0 ]; then
    echo "[ERRORE] Il comando è fallito con codice errore $STATUS."
    echo "[SUGGERIMENTO] Controlla che il file JAR sia valido e che Java sia installato correttamente."
    echo "Premi un tasto per chiudere..."
    read -n 1
    exit $STATUS
fi

echo "[SUCCESSO] Il Test Smell Detector è stato eseguito correttamente."
echo "Premi un tasto per chiudere..."
read -n 1

# Fine dello script
exit 0
