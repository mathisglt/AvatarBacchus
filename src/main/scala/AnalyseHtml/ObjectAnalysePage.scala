package application
import library.Html
import library.Tag
import library.Texte
import library.Expression
import library.OutilsWebObjet
import library.AnalysePage
import library.HtmlExample
import library.OutilsWeb
import library.Et
import library.Mot
import library.Expression
import library.Ou

object ObjectAnalysePage extends AnalysePage{
 type URL = String
 type Titre = String
 val objFiltrageUrls:URLFiltres = new URLFiltres
 val objFiltrageHtml: FiltreHTML= new FiltreHTML
   /**
     * @param url une url correspondant à une page html
     * @param exp une expression à vérifier
     * @return une liste de couple (titre,url) correspondant aux pages 
     * presentent sur la page en parametre et qui satisfont la requete
     */
    //TODO faire en sorte qu'il ne mette que les bonnes pages dans la liste des la creation au lieu de 
    //creer une liste avec toutes les annonces puis de supprimer
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
object testAnalysePage extends App{//batterie de test de AnalysePage parce que mon vs code a des problemes pour run les tests 
  //val testChild=List[Html](HtmlExample.exemple,Tag("title",List(),List[Html](Texte("ui"))),Texte("LeBonTitre"),Tag("body",List(),List()))
  //println(ObjectAnalysePage.premierTexte(testChild))
  //println(ObjectAnalysePage.getTitre(HtmlExample.exemple))
  //println(ObjectAnalysePage.traverseeTitre(testChild))
  //println(ObjectAnalysePage.getTitre(OutilsWebObjet.obtenirHtml("https://www.vivastreet.com/immobilier-appartement/etranger-portugal/t1-para-so--jacuzzi-e-barbecu---363232-002-ac9630-/309942233")))
  //on peut considérer que getTitre marche
  //println(ObjectAnalysePage.getResultats(List(("https://www.vivastreet.com/immobilier-appartement/etranger-portugal/t1-para-so--jacuzzi-e-barbecu---363232-002-ac9630-/309942233",OutilsWebObjet.obtenirHtml("https://www.vivastreet.com/immobilier-appartement/etranger-portugal/t1-para-so--jacuzzi-e-barbecu---363232-002-ac9630-/309942233")),
  //                                            ("https://www.google.com/",HtmlExample.exemple))))
  //ce n'est pas getRésultat qui boucle
  val objFiltrageUrls:URLFiltres = new URLFiltres
  val objFiltrageHtml: FiltreHTML= new FiltreHTML
  val liensURL= List[String]("https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=developpeur+nantes+java",
"https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=developpeur+nantes+python",
"https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=developpeur+rennes+java",
"https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=developpeur+rennes+python")
  val exp:Expression=Et(Mot("developpeur"),Et(Ou(Mot("rennes"),Mot("nantes")),Ou(Mot("python"),Mot("java"))))
  val html:Html=OutilsWebObjet.obtenirHtml("https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=developpeur+rennes+python")
  val htmlfiltrée=objFiltrageUrls.filtreAnnonce(html)
  val list=ObjectAnalysePage.listUrlsToListHtmls(htmlfiltrée)
  val listBons=ObjectAnalysePage.coupleListfromRequete(list,exp)
}