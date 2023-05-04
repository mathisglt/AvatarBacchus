# AvatarBACCHUS

NOS VALEURS AJOUTÉES :
  - une interface de qualité réelle
  - un bouton pour activer/désactiver la voix + un raccourci calvier (F2 dans la zone de texte)
  - envoyer le message avec 'entrée'
  - dites juste "ça va" et le bot répondra en français uniquement
  - drapeau de la bretagne comme icône de l'interface
  - scrollbar horizontale

ERREURS TROUVÉES :
  - nous sommes obligés de retirer le mot "rennes" de la requête (voir AnalyseImpl.filtrePolitesseRecherche)
  - il ne prononce pas la première phrase "Bonjour, comment puis-je vous aider ?"

DIRECTION ARTISTIQUE :
  - lorsqu'une requête comporte plusieurs langues, le robot va répondre dans la langue du premier mot détectée (ex : bonjour where is la gare => en français)
  - on ne traite que les lieux étant dans la ville de Rennes (<city>Rennes</city> dans vAr.xml)
  - on ne renvoie "Je ne comprends pas votre demande" pour un lieu qui n'a pas d'adresse dans vAr.xml (les balises <name> et <number> dans <address> ne doivent pas être vides)
  - pour l'analyse, on va retirer un certain nombres de "parasites" (accents, déterminants, mots de politesse, mots de recherche, etc.) puis, on va chercher dans un premier temps si la demande concerne "tnb" ou l'hotel de ville. Ensuite, on va traiter le cas de la recherche d'une pizzeria, d'un restaurant ou d'une creperie via le site "linternaute". Si aucun résultat n'est encore renvoyé, on va séparer les mots restants dans deux listes : les mots apparaissant dans "liste_mots_bdd_xml" (la liste des mots composant les lieux de vAr.xml) et les mots qui n'y apparaissent pas. On tente une correction de la 2e liste puis on les concatene : cela nous donne notre liste de mots finale sur laquelle on lance une recherche mot par mot dans la bdd via analyserList et quiContient. Avec ces fonctions, on obtient une liste de lieux dont le user demande potentiellement l'adresse. (ex : à ce stade "gare sncf" renvoie => List("Gare SNCF","Gare SNCF","Service Fret - SNCF"))
    - si la liste est vide on renvoit ("","") et ConstructionImpl s'occupera de renvoyer une réponse adéquate
    - s'il n'y a qu'un élément dans la liste, on renvoie le couple (lieu, adresse)
    - s'il y a plusieurs éléments, le processus consiste à prendre le lieu ayant le plus d'occurence dans la liste, on renverra alors le couple (lieu, adresse). Si plusieurs éléments ont le nombre d'occurence maximal, on renverra la liste de ces couples (lieu, adresse).

UML (résumé) :
  - Le programme commence dans l'Objet Interface Graphique
  - L'Interface appelle Machine avec la requête de l'utilisateur
  - Machine appelle Construction pour construire la réponse du robot
  - Machine à également accès à Langue pour la fonction de réinitialisation (remettre la langue sur fr)
  - Construction appelle Analyse pour analyser la requête du client et savoir ce qu'il cherche
  - Analyse appelle premièrement Fautes pour corriger d'éventuelles fautes de l'utilisateur
  - Analyse appelle Langue pour savoir dans quelle langue répondre à l'utilisateur
  - Puis, Analyse utilise AnalyseHtml qui correspond au projet Robot Web afin de transformer les pages html du site de référence pour obtenir les informations
  - Enfin, Analyse appelle BDD pour rechercher les mots clés obtenus dans la base de données
  - Ensuite la réponse remonte étape par étape à Machine puis à Interface
  - Interface affiche les réponses obtenues et les envoie à Voice qui les lit