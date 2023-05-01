package analyse_html

import library.{Html, Tag, Texte, Expression, OutilsWebObjet, AnalysePage, HtmlExample, OutilsWeb, Et, Mot, Ou}


object ObjectAnalysePage extends AnalysePage{
 type URL = String
 type Titre = String
 val objFiltrageUrls: URLFiltres = new URLFiltres
 val objFiltrageHtml: FiltreHTML= new FiltreHTML
   
  /**
    * @param url une url correspondant à une page html
    * @param exp une expression à vérifier
    * @return une liste de couple (titre,url) correspondant aux pages 
    * presentent sur la page en parametre et qui satisfont la requete
    */
  override def resultats(url:String,exp:Expression):List[(String,String)]={
    val leHtml : Html = OutilsWebObjet.obtenirHtml(url)
    val lUrls : List[String]= objFiltrageUrls.filtreAnnonce(leHtml)
    val lURLsHtml : List[(String,Html)]= listUrlsToListHtmls(lUrls)
    var listeBonsCouples: List[(String,Html)]= coupleListfromRequete(lURLsHtml,exp)
    return getResultats(listeBonsCouples)
  }
 
  /**
    * @param urls une liste d'urls
    * @return la liste de couple avec l'url et la page html
    */
  def listUrlsToListHtmls(urls:List[String]):List[(String,Html)]={
    var result= List[(String,Html)]()
    for (url<-urls){
      result=(url,OutilsWebObjet.obtenirHtml(url))::result
    }
    return result
  }
 
  /**
    * @param Urls une liste d'Urls accompagnés de leur page Html
    * @param exp une expression
    * @return la liste des urls ayant leur page Html qui satisfait l'expression
    */
  def coupleListfromRequete(Urls:List[(String,Html)],exp:Expression):List[(String,Html)]={
    var result= List[(String,Html)]()
    for ((url,html)<-Urls){
      if(objFiltrageHtml.filtreHtml(html,exp)){
        result= (url,html)::result
      }
    }
    return result
  }

  /**
    * @param Urls une liste d'urls ccompagnés de leurs pages html
    * @return la meme liste mais ou sont inscrit le titre au lieu de la page html
    */
  def getResultats(Urls:List[(URL,Html)] ) : List[(Titre,URL)]= {
    Urls match {
      case (url,html)::queue => (getTitre(html),url)::getResultats(queue)
      case Nil => Nil
    }
  }
  
  /**
    * @param h une page Html
    * @return son titre
    */
  def getTitre(h:Html):String={
    h match {
      case Tag(name, _, children) => if(name=="title") return premierTexte(children) else traverseeTitre(children)
      case Texte(content) => ""
    }
  }
 
  /**
    * @param child une liste d'objets html 
    * @return le titre du premier objet html en ayant un
    */
  def traverseeTitre(child:List[Html]):String ={
    child match{
      case head :: next => if(getTitre(head)=="") traverseeTitre(next) else getTitre(head)
      case Nil => ""
    }
  }

  /**
    * @param child une liste d'objet html 
    * @return la String du premier texte
    */
  def premierTexte(child:List[Html]):String={
    child match{
      case l::ls=> l match {
         case Texte(content) => return content
         case _ => return premierTexte(ls)
      }
      case Nil => ""
    }
  }
}