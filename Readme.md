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

Dépôts distants
---------------
* https://gitlab-etu.fil.univ-lille1.fr/baloup/PJEA-Plagiat (intranet Lille 1 seulement)
* https://github.com/marcbal/PJEA-Plagiat (dépôt privé)

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

