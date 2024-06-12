@echo off
rem Compilation Java
javac -encoding utf8 Controleur.java metier\*.java ihm\*.java -d class 2>&1 

rem Vérification du statut de la compilation
if %ERRORLEVEL% NEQ 0 (
    echo Erreurs de compilation. Veuillez consulter le fichier compileErrors.log pour plus de détails.
    exit /b %ERRORLEVEL%
)

rem Copie des fichiers source dans le répertoire de sortie
xcopy /s /i "src" "class\jeu\src"

