package tolerance_fautes
import scala.io.Source
import bdd.BDDImpl

class FautesImpl extends FautesTrait{
val mots_verifiant = BDDImpl.recupLieux(Source.fromFile("doc/DonneesInitiales.txt"))

  def correction(mots:List[String]):List[String]={
    mots
  }
  /** 
   * regarde si la distance de Hamming entre deux strings est supérieure ou égal à 1 
   * 
   * @param strtest une string a tester
   * @param strmodele le modele (plus grand que le test)
   * @return true si la string test est à 1 de distance maximum de la string modele 
   */
  def distanceDeHammingSup1(strtest:String,strmodele:String):Boolean={
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
