# sae201Psyche
sae201Psyche

Fonctionnalités à démontrer :

Menu prncipal : 
 - Ouverture de la frame de démarrage
 - Faire la même chose avec "Scenario" qui utilise un fichier de scénario. Les raccourcis fonctionnent et ouvrent les fenetres.
 - Quitter et Alt F4 fonctionnent
 - Bouton jouer et modifier


Editeur de carte

Frame Modification : 

 - Modification de la position des sommets avec la souris ( et ajustement des routes en fonction )		(FAIT)

 - Importer un thème 	(FAIT)

 - Créer un sommet		(FAIT)
 - Créer une route		(FAIT)

 - Supprimer			(FAIT)
 - Réinitialiser		(FAIT)

 - Enregistrer et quitter	(FAIT)
 - Quitter sans enregistrer		(FAIT)

Montrer que le système de sauvegarde des modifications marche correctement.		(FAIT)


Jeu 

Frame Choix de joueur
 - Changement de noms des joueurs
 - Keybinds ( Entrée pour lancer la partie et Echap pour annuler )

Frame Jeu ( mode classique )

 - Routine de jeu classique
	- Prise de chemin par le joueur qui joue
	- Affectation de la ressource au joueur qui joue
	- Mise a jour des points pour le joeur qui joue
	- Mise ajour éventuelle si le chemin d'un autre joueur est utilisé
	- Changement de tour

	- Respect des règles
		- Prise de route possible SEULEMENT SI elle est conectée au départ ET il reste des jetons possessions.
		- Ajout correct des points en foncction de la mine prise ( attention aux mines d'or )
		- Il ne peut pas y avoir plus de 4 même ressource dans la même colonne (essayer avec 5 du même)

 - Quitter 
 	- sans enregistrer n'enregistre pas la partie
	- avec la croix enregistre la partie
	- garde le joueur qui était en train de jouer

Frame Jeu ( mode scénario )

 - bouton Annuler
 - bouton Rétablir


Frame Score Final

 - Calcul de score correct
	- toutes les catégories de score sont bonnes
	- Montrer une fin de partie avec une égalité

------------------------------------------------------------------------------------------------------------------------------------

Scénario 1 : Lancement de jeu + Déroulement d'une partie







Scénario 2 : Fin de partie + tableau des scores









Scénario 3 : Modification + ajout de thèmes

 - Lancement de l'app
 - Choix du thème
 - Cliquer sur "Modifier"

 - Cliquer sur la modification des villes
	- Créer une ville. Ne pas choisir des coordonées correctes
	- Modifier le nom et la position d'une ville. Ne pas choisir des coordonées correctes
	- Supprimer une ville

 - Cliquer sur la modification d'une route
	- Créer une route. Ne pas choisir des coordonées correctes
	- Modifier une route. Ne pas choisir des coordonées correctes
	- Supprimer une route

 - Cliquer sur Enregistrer et quitter
 - Ouvrir le même thème
 - Effectuer de nouvaux changements 
 - Cliquer sur Quitter sans sauvegarder
 - Ouvrir le même thème
 - Cliquer sur Réinitialiser
 - Cliquer sur Supprimer
 - Sauvegarder + fermer, puis ouvrir
-----
 - Ouvrir un autre thème 
 - Importer un autre thème