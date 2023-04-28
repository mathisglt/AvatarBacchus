package application

import library.FiltrageURLs
import library.Html
import library.Tag
import library.Texte
import java.net.URL
import library.OutilsWebObjet

class URLFiltres extends FiltrageURLs{
    /**
     * @param h un objet html
     * @return une liste des URLs d'annonce de vivastreet de cet objet html
     */ 
    def filtreAnnonce(h:Html):List[String]={
        h match {
            case Tag("a", attributes, children) => clearVoid(fonctionSurListe[Html,String](children,filtreAnnonce):+trouveURL(attributes))
            case Tag(name, attributes, children) => fonctionSurListe[Html,String](children,filtreAnnonce)
            case _: Html => Nil
        }
    }
     def filtreAnnonceAdresse(h:Html):List[String]={
 //       val h2=trouvePartieAnnonce(h)
        h match {
            case Tag("li", attributes, children) =>clearVoid(fonctionSurListe[Html,String](children,filtreAnnonceAdresse):+trouveAdresse(attributes,children))
            case Tag(name, attributes, children) => fonctionSurListe[Html,String](children,filtreAnnonceAdresse)
            case _: Html => Nil
        }
    }
     def filtreAnnonceNom(h:Html):List[String]={
 //       val h2=trouvePartieAnnonce(h)
        h match {
            case Tag("div", attributes, children) =>clearVoid(fonctionSurListe[Html,String](children,filtreAnnonceNom):+trouveNom(attributes,children))
            case Tag(name, attributes, children) => fonctionSurListe[Html,String](children,filtreAnnonceNom)
            case _: Html => Nil
        }
    }


    /** 
      * @param list une list de String
      * @return la meme liste dont les elements vides ("") furent soustraits
      */
    def clearVoid(list:List[String]):List[String]={
        var result= List[String]()
        list match{
            case head::next=>if (head=="") return clearVoid(next) else head::clearVoid(next)
            case Nil=>Nil
        }
        }


    /**
     * @param l une liste
     * @tparam A le type des éléments de la liste A
     * @param f une fonction de A vers liste de B
     * @tparam B le type d'élément des listes produites par f
     * @return la concaténation des résultats de f sur chaque élément de l
     */
    def fonctionSurListe[A,B](l:List[A],f:A=>List[B]):List[B]={
        var result = List[B]() 
        for (a<-l){
            result= result ++ f(a)
        }
        result
    }
    /**
      * @param l une liste de couple de String (dans ce tp (nom, URL)) 
      * @return l'url de la liste qui a pour nom href et qui contient un lien vivastreet la liste vide sinon
      */
    def trouveURL(l:List[(String,String)]):String={
        for ((name,url)<-l){
            if (name=="href"&& url.startsWith("/restaurant/restaurant/")) return url
        }
        return ""
    }
    def trouveAdresse(l:List[(String,String)],children: List[Html]):String={
        for ((name,url)<-l){
            if (name=="class"&& url.contains("icomoon-location")) return children(1) match{
                case Tag(name, attributes, children2) => children2.head match{
                    case Texte(content) => content
                }
            }
        }
        return ""
    }
    def trouveNom(l:List[(String,String)],children: List[Html]):String={
        for ((name,url)<-l){
            if (name=="class"&& url.contains("grid_left w80")) return children(1) match{
                case Tag(name, attributes, children2) => children2.head match{
                    case Texte(content) => content.replaceAll("&#039","\'")
                }
            }
        }
        return ""
    }
    
}
object testFiltreAnnonce extends App{//batterie de test de urlFiltres parce que mon vs code a des problemes pour run les tests 
    val a=new URLFiltres
    val htmlTest=OutilsWebObjet.obtenirHtml("https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=train&cat_1=&geosearch_text=&searchGeoId=")
    val bs=a.filtreAnnonce(htmlTest)
    for (b<-bs){
        println(b)
    }
}
