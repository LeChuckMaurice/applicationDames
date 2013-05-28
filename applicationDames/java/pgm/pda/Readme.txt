Projet de d�veloppement de l'application PDA.

Structure du r�pertoire de d�veloppement
----------------------------------------

    projetPDA/		: projet de d�veloppement PDA
    |-- Readme.txt	: ce fichier
    |-- ant		: fichiers d'automatisation de ANT
    |-- build       	: fichiers .class et autres (r�sultant d'une compilation ou g�n�ration d'une javaDoc ou d'archives)
    |-- data		: donn�es et images de l'application
    |-- lib		: librairies exterieures � conserver
    |-- src		: sources Java
    |   `-- pda     	: paquetage principal de l'application
    `-- ww          	: r�pertoire de travail du d�veloppeur (!! n'existe pas encore apr�s d�sarchivage !!)

Mode d'emploi
-------------

	1re Utilisation
	---------------

	Vous devez ex�cuter cette commande une fois pour toute :
	$ cd projetPDA
	$ ant -f ant/build.xml init

	Ensuite, il faut se placer dans le r�pertoire 'projetPDA/ww' et cr�er les liens symboliques sur ant/build.xml et le r�pertoire data
	$ ln -s ../ant/build.xml build.xml
	$ ln -s ../data data

	En cours de d�veloppement
	-------------------------

	L'environnement est contr�l� par un script ANT ; il faut TOUJOURS se placer dans le r�pertoire 'projetPDA/ww'.

	La commande :
	$ ant -p

	permet d'obtenir la liste des commandes de compilation et de manipulation des sources.

	Lancement du pda
	----------------

	$ ant run


