#!/bin/bash
javac -encoding utf8 Controleur.java metier/*.java ihm/*.java -d class
cp -r "src" "class/jeu/src"
