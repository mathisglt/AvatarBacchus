package analyse_html

import library.ProductionResultat
import library.Html
import library.Tag
import library.Texte

object HtmlResultats extends ProductionResultat { 

  /**
    * @param l la liste des solutions sous la forme (titre,URL)
    * @return une page html qui repertorie tous ces resultats sous forme de liens clickables
    */
  def resultatVersHtml(l:List[(String,String)]):Html={
    Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("Résultats"))), Tag(
              "link",
              List(("rel", "stylesheet"), ("href", "style.css")),
              List()
            ))),
      Tag("body", List(), List(
        Tag("center", List(),ListeTag(l)
          )))))
  }

  /**
    * consiste simplement prendre chaque element de la liste l et a le transformer en tag puis de creer une liste de tag avec ceci
    * @param l une liste de doublet de String de la forme (titre,URL)
    * @return une liste de Tag etant la liste prise en entree sous forme Html
    */
  def ListeTag(l:List[(String,String)]):List[Tag]={
    var liste = List[Tag]()
    liste = (Tag("h1", List(),List(Texte("Liste des résultats")))) :: liste
    for ((titre,url)<-l){
      liste = liste :+ (Tag("a", List(("href", url)),List(Texte(titre)))) :+ Tag("br",List(),Nil) 
    }
    liste
  }
}