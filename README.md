# AvatarBACCHUS

NOS VALEURS AJOUTÉES :
- une interface de qualité réelle
- un bouton pour activer/désactiver la voix
- envoyer le message avec 'entrée'
- dites juste "ça va" et le bot répondra en français uniquement
- drapeau de la bretagne comme icône
- scrollbar horizontale

ERREURS TROUVÉES :
- nous sommes obligés de retirer le mot "rennes" de la requête (voir AnalyseImpl.filtrePolitesseRecherche)
- il ne lit pas la première phrase "Bonjour, comment puis-je vous aider ?"

DIRECTION ARTISTIQUE :
- lorsqu'une requête comporte plusieurs langues, le robot va répondre dans la langue du premier mot détectée
  ex : bonjour where is la gare => en français
- pour l'analyse, on va retirer un certain nombres de "parasytes" (accents, déterminants, mots de politesse, mots de recherche, etc.)
  puis, XXX (kaw : je vais continuer)

UML :
 - XXX expliquer uml