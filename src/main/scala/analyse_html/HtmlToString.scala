package analyse_html

import library.{Html, Tag, Texte, HtmlVersString}


object  HtmlToString extends HtmlVersString {
  def traduire(h:Html):String = {
    var str = ""
    h match {
      case Tag(name,Nil,children) => // pas d'attribut et un enfant (ex : <title>My Page</title>)
        str += "<"+name+">"
        for (html <- children) {
          str += traduire(html)
        }
        str += "</"+name+">"
      case Tag(name,attr,Nil) => // balise orpheline (ex : <meta ... />)
        str += "<"+name
        for ((s1,s2) <- attr) {
          str +=" "+ s1 +"=\""+ s2 +"\""
        }
        str += "/>"
      case Tag(name,attr,children) => // attribut et enfant 
        str += "<"+name+" "
        for ((s1,s2) <- attr) {
          str += s1 +"=\""+ s2 +"\" "
        }
        str += ">"
        for (html <- children) {
          str += traduire(html)
        }
        str += "</"+name+">"
      case Texte(s) => str += s // texte mis entre des balises (ex : "My Page" dans <title>My Page</title>)
      case _ => str = "erreur" // si une erreur est detectee, on renvoie la chaine "erreur"
    }
    str
  }
}