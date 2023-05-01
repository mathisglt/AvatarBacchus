package analyse_html

import library.{Html, Et, Ou, Mot, Expression, ParserExpression, AnalysePage}, analyse_html.ObjectAnalysePage, java.io.FileWriter


object Application extends App{
  
  val exp=ParserExpression.lireExpression
  val liensURL= OutilsApp.creeURL(exp)
  var coupleResultats= List[(String,String)]()
  for(url<-liensURL){
    coupleResultats=ObjectAnalysePage.resultats(url,exp).concat(coupleResultats) 
  }
  val result=HtmlResultats.resultatVersHtml(coupleResultats)
  val resultTraduit=HtmlToString.traduire(result)
  val file = new FileWriter("Resultat.html")
  try{
    file.write(resultTraduit)
  } finally file.close()
}

object OutilsApp {
  /**
    * @param listFinURL la liste des expressions a mettre dans l'url
    *       ca revient à faire creeFinURL(Et(Mot("https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords="),listeFinURL))
    *       mais sans le +
    * @return la liste des URLs a chercher
    */
  def creeURL(exp: Expression):List[String]={
    var result = List[String]() 
    for (e<-creeFinURL(exp).distinct){
      result="https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords="+e::result
    }
    return result
  }

  /**
    * @param requete une expression
    * @return les urls satisfaisant l'expression
    */
  def creeFinURL(requete:Expression):List[String]={
    requete match{
      case Et(e1, e2) => SoccupeDeEt(creeFinURL(e1),creeFinURL(e2))
      case Ou(e1, e2) => return creeFinURL(e1).concat(creeFinURL(e2))
      case Mot(w) => return w::Nil
    }
  }

  /**
    * @param e1 une expression
    * @param e2 une expression
    * @return le "Et" la concatenation de chaque string entre-elles pour creer une chaque URL
    */
  def SoccupeDeEt(e1:List[String],e2:List[String]):List[String]={
    var result=List[String]()
    for (element1<-e1){
      for (element2<-e2){
        result=(element1+"+"+element2)::result
      }
    }
    return result
  }
}
object testcreationURL extends App{//batterie de test de creeURL parce que mon vs code a des problemes pour run les tests 
  val test:Expression=Et(
                      Ou(
                        Et(
                          Ou(
                            Mot("mot1"),
                            Mot("mot2")),
                          Ou(
                            Mot("mot3"),
                            Mot("mot4"))),
                        Mot("mot5")),
                      Mot("mot6"))
  val URL:List[String]=OutilsApp.creeURL(test)
  for (url<-URL){
    println(url)
  }
}
