//Ca marche 
package application
import library.FiltrageHtml
import library.Html
import library.Expression
import library.Et
import library.Ou
import library.Mot

import application.HtmlToString
import library.Tag
import library.Texte
import library.HtmlExample
class FiltreHTML extends FiltrageHtml{
 /**
   * @param h un document Html
   * @param e une expression
   * @return si le document satisfait l'expression
   */
  def filtreHtml(h:Html,e:Expression):Boolean = {
    e match {
      case Et(e1, e2) => filtreHtml(h,e1) && filtreHtml(h,e2)
      case Ou(e1, e2) => filtreHtml(h,e1) || filtreHtml(h,e2)
      case Mot(w) => estContenu(h,w)
    }
  }

  /**
    * @param h un document html
    * @param mot un mot
    * @return si le mot est contenu dans le texte du document
    */
    def estContenu(h:Html,mot:String):Boolean={
      h match {
        case Tag(_, _, children) => estContenuEnfants(children,mot)
        case Texte(content) => return content.contains(mot)
      }
    }
    /**
      * @param hs une liste d'objets html
      * @param mot une string
      * @return si le mot se trouve dans une des pages html
      */
    def estContenuEnfants(hs:List[Html],mot:String):Boolean={
      for (h<-hs){
        if (estContenu(h,mot)) return true
      }
      return false
    }

}
object testFiltreHtml extends App{//batterie de test de filtreHTML parce que mon vs code a des problemes pour run les tests 
  val obj:FiltreHTML=new FiltreHTML
  println(obj.filtreHtml(HtmlExample.exemple,Mot("Lien")))//true
  println(obj.filtreHtml(HtmlExample.exemple,Mot("Titre")))//false
  println(obj.filtreHtml(HtmlExample.exemple,Ou(Mot("Lien"),Mot("Titre"))))//true
  println(obj.filtreHtml(HtmlExample.exemple,Ou(Mot("Liens"),Mot("Titre"))))//false
  println(obj.filtreHtml(HtmlExample.exemple,Et(Mot("Mauv"),Ou(Mot("Lien"),Mot("Titre")))))//true
  println(obj.filtreHtml(HtmlExample.exemple,Et(Ou(Mot("Mauv"),Mot("sesursededasn")),Ou(Mot("Lien"),Mot("Titre")))))//true
  println(obj.filtreHtml(HtmlExample.exemple,Et(Mot("Mauv"),Et(Mot("vais"),Et(Mot("Mauvais"),Mot("titre"))))))//true
}
