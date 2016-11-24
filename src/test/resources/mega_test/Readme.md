# Jeu de test

**Team VEMP** "Mega test"

## Contenu du jeu de test

* `BC-lastbuild-A-E.java` : 250 kio, 5792 lignes, 32446 tokens
    * Source : https://github.com/marcbal/BungeeCord/tree/f8dd58621cb115041d5ea1c45d53a6c3299dce85
    * Fichiers .java fusionné avec notre outils de fusion. Seules les classes de A à E ont été conservés.
* `BC-2yearsbefore-A-E.java` : 208 kio, 4834 lignes, 24945 tokens
    * Source : https://github.com/SpigotMC/BungeeCord/tree/f19cc7fe4fb915d085783d8b29c6818577ad7b06
    * Fichiers .java fusionné avec notre outils de fusion. Seules les classes de A à E ont été conservés.

Dans les deux fichiers, les commentaires et les lignes vides ne sont pas présents.
  


## But du test

Vérifier que les programmes supportes des gros fichiers (ne contenant pas uniquement des import) et éventuellement si ces programmes sont bien optimisés en espace (StackOverflowError ou OutOfMemoryError) ou en temps.
