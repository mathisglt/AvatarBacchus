package tolerance_fautes
import scala.io.Source
import bdd.BDDImpl

class FautesImpl extends FautesTrait{

val mots_a_verifier:List[String] = BDDImpl.recupLieux(Source.fromFile("doc/DonneesInitiales.txt"))

  /**
    * applique testChaque mot sur toute une liste de mot et en renvoie la string corrigée
    *
    * @param mots une liste de mots
    * @return la meme liste corrigée
    */

  def correction(mots:List[String]):List[String]={
    var result:List[String]=Nil
    for (mot<-mots){
        result=testChaqueMot(mot,mots_a_verifier)::result
    }
    result.reverse
  }

  /**
   * prends un mot et une liste de modele et renvoie 
   * le premier modele suffisamment proche du mot de base ou le mot si il n'y en a pas
   * 
   * @param motATester une String qu'on veut tester
   * @param modeles une liste des modeles
   * @return le mot venant des modeles le plus proche du mot a tester ou celui-ci si il n'y en a pas
   */
  def testChaqueMot(motATester:String,modeles:List[String]):String={
    modeles match{
        case modele :: next => if(distanceDeHammingInf1(motATester,modele)) modele else testChaqueMot(motATester,next)
        case Nil => motATester
    }
  }
  /** 
   * regarde si la distance de Hamming entre deux strings est supérieure ou égal à 1 
   * 
   * @param strtest une string a tester
   * @param strmodele le modele (plus grand que le test)
   * @return true si la string test est à 1 de distance maximum de la string modele 
   */
  def distanceDeHammingInf1(strtest:String,strmodele:String):Boolean={
    (strmodele.length-strtest.length==0 && test1FauteMax(strtest,strmodele))||
    (strmodele.length-strtest.length==1 && testDecalage(strtest,strmodele))
  }

  /**
    * prends deux string de meme taille et renvoie si elles sont décalées de 1 (en gros si il y a une lettre erronée)
    *
    * @param strtest a string de test
    * @param strmodele le modele (> le test)
    * @return si il y a une faute max
    */

  def test1FauteMax(strtest:String,strmodele:String):Boolean={
    strtest.isEmpty() || 
    (strtest(0)==strmodele(0) && test1FauteMax(strtest.tail,strmodele.tail)) || 
    strtest.tail==strmodele.tail
  }

  /**
   * prends deux string un test et un modele et dit s'il manque un caractere dans la string test par rapport a la string modele 
   * 
   * @param strtest une string test
   * @param strmodele un modele (> le test)
   * @return si la string test a bien juste une lettre en moins par rapport au modele
   */

  def testDecalage(strtest:String,strmodele:String):Boolean={
    strtest.isEmpty() || 
    (strtest(0)==strmodele(0) && testDecalage(strtest.tail,strmodele.tail)) || 
    strtest==strmodele.tail
  }
}
