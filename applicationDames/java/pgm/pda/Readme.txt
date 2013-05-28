Projet de développement de l'application PDA.

Structure du répertoire de développement
----------------------------------------

    projetPDA/		: projet de développement PDA
    |-- Readme.txt	: ce fichier
    |-- ant		: fichiers d'automatisation de ANT
    |-- build       	: fichiers .class et autres (résultant d'une compilation ou génération d'une javaDoc ou d'archives)
    |-- data		: données et images de l'application
    |-- lib		: librairies exterieures à conserver
    |-- src		: sources Java
    |   `-- pda     	: paquetage principal de l'application
    `-- ww          	: répertoire de travail du développeur (!! n'existe pas encore après désarchivage !!)

Mode d'emploi
-------------

	1re Utilisation
	---------------

	Vous devez exécuter cette commande une fois pour toute :
	$ cd projetPDA
	$ ant -f ant/build.xml init

	Ensuite, il faut se placer dans le répertoire 'projetPDA/ww' et créer les liens symboliques sur ant/build.xml et le répertoire data
	$ ln -s ../ant/build.xml build.xml
	$ ln -s ../data data

	En cours de développement
	-------------------------

	L'environnement est contrôlé par un script ANT ; il faut TOUJOURS se placer dans le répertoire 'projetPDA/ww'.

	La commande :
	$ ant -p

	permet d'obtenir la liste des commandes de compilation et de manipulation des sources.

	Lancement du pda
	----------------

	$ ant run


