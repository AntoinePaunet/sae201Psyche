@echo off

rem Compilation Java
javac -encoding utf8 Controleur.java metier\*.java ihm\*.java -d class > compileErrors.log 2> compileErrors.log

rem Vérification du statut de la compilation
if %ERRORLEVEL% NEQ 0 (
    echo Erreurs de compilation. Veuillez consulter le fichier compileErrors.log pour plus de détails.
    exit /b %ERRORLEVEL%
)

rem Afficher la structure du répertoire src

exit 1
