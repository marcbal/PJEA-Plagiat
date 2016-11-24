PJE-A Plagiat VEMP
==================

*VEMP = Veïs, Eddy, Marc, Pape*

PJE A - Master 1 Informatique - 2016-2017 - Université Lille 1

Contributeurs
-------------
* Veïs Oudjail
* Eddy El Khatib
* Marc Baloup
* Pape Badiane

Liens
---------------
* https://gitlab-etu.fil.univ-lille1.fr/baloup/PJEA-Plagiat (intranet Lille 1 seulement)
* https://github.com/marcbal/PJEA-Plagiat (dépôt privé)
* http://ci.pandacube.fr/job/PJEA-Plagiat_dev (Intégration continue de la branche `dev` sur un serveur Jenkins)

Développement
-------------
Version de Java : 1.8

Utiliser de préférence Eclipse Neon pour la configuration du projet (en plus de celle apportée par la configuration de Maven). Utiliser l'option d'importation "General/Existing project into workspace" et non "Maven/Existing maven project".

Évolution du projet
-------------------

### Premier rendu **v1.0** du 6 octobre

Compare simplement les fichiers deux à deux, avec un algorithme simple. Une hiérarchie de classe permet facilement de switcher d'un algorithme à un autre juste en changeant la classe du comparateur appelé dans le `main()`.

Algorithmes développés :

* `StrictEqualsFilesComparator` : 100% quand les fichiers sont identiques (sauf commentaires et caractères non-imprimables), sinon 0% (le plus simple)
* `NaiveSuccessivesTokensFileComparator` : Compare les fichiers tokens par tokens.
Plus techniquement : divise le nombre de paire de tokens identique d'un fichier à l'autre par le nombre total de token du fichier qui en a le plus. Les tokens sont comparés dans l'ordre, donc l'ajout ou suppression d'une ligne de code au début d'un des fichier réduit drastiquement le
pourcentage de similitude.

### Deuxième rendu **v2.0** du 20 octobre

* Ajout d'une classe permettant la génération de l'empreinte du fichiers. Pour ce faire, les algorithmes utilisé pour le générateur sont :
    * `RabinHashCodeBuilder` : Permet de creer les hashs suivant le formalisme de Rabin.
    * `WinnowingFootprintBuilder` : Permet de creer les empreintes suivant de Winnowing avec les fenetres glissantes.

La comparaison effectué par le main est la suivante :
* `WinnowingAndAlignmentFileComparator` : Comparaison de deux fichiers avec l'algorithmes d'alignement de Needleman-Wunsch. Le filtrage des fichiers trop différents (en comparant les empreintes) n'est pas implémenté


### Troisième rendu **v3.0** du 10 novembre

* Fonctionnalités : On compare toujours les fichiers passé en argument du main, en utilisant l'algorithme d'alignement (en attendant la validation du test du Winnowing, et l'algo sur les fins de lignes)
* Ajout d'un grand nombre de tests unitaires.
* Développement (non terminé) d'algorithme de fusion de fichiers .java (pour tester la comparaison en gros volume)
* Développement (non terminé) d'algorithme qui compare les fins de ligne des fichiers (caractères non-imprimables)
* Installation du projet sur un serveur d'intégration `Jenkins` (lien au dessus) :
    * Affichage graphique des tests unitaires Junit fonctionnels et non-fonctionnels
    * Affichage de la couverture de code des tests unitaires (en relation avec un plugin Maven qui effectue l'analyse)
    * Un build est lancé sur Jenkins à chaque push sur le dépôt Github.


### Quatrième rendu **v4.0** du 24 novembre

* Fonctionnalités : On compare toujours les fichiers passé en argument du main, en utilisant l'algorithme d'alignement (en attendant la validation du test du Winnowing, et l'algo sur les fins de lignes). Ajout : Les déclarations de `package` et `import` ne sont pas pris en compte dans la comparaison.
* Refactorign : réoraganisation générale de tous les packages et classes du projet. Code plus porpre dans pas mal de classes.
* Développement Terminée de l'algorithme de fusion de fichiers `.java`. Des gros fichiers (provenant de projets Java sur Github) sont comparés dans nos classes de tests. Possibilité de fournir ces fichiers pour que les autres projets puissent être testés.
* Amélioration de l'algorithme de comparaison des fins de ligne des fichiers (caractères non-imprimables)
* Avancement des commentaires Javadoc du code source.
