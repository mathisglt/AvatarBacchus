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
- pour l'analyse, on va retirer un certain nombres de "parasytes" (accents, déterminants, mots de politesse, mots de recherche, etc.) puis, XXX (kaw : je vais continuer)

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