#!/bin/bash
javac -encoding utf8 Controleur.java metier/*.java ihm/*.java -d class 2>&1 | tee compileErrors.log
cp -r "src" "class/jeu/src"
