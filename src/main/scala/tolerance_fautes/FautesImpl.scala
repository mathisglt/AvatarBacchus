package tolerance_fautes

import scala.io.Source
import org.apache.commons.lang3.StringUtils.{stripAccents}

object FautesImpl extends FautesTrait {

  def correction(mots: List[String], modeles: List[String]): List[String] = {
    var result: List[String] = Nil
    val modelesCleared = clearAccentToMaj(modeles)
    val motsTestsCleared = clearAccentToMaj(mots)
    for (incr <- 0 to motsTestsCleared.length - 1) {
      testChaqueMot(motsTestsCleared(incr), modelesCleared) match {
        case -1  => result = mots(incr) :: result
        case num => result = modeles(num) :: result
      }
    }
    result.reverse
  }

  def correctionAvecPetitsMots(mots: List[String], modeles: List[String]): List[String] = {
    var result: List[String] = Nil
    val modelesCleared = clearAccentToMaj(modeles)
    val motsTestsCleared = clearAccentToMaj(mots)
    for (incr <- 0 to motsTestsCleared.length - 1) {
      testChaqueMotAvecPetitsMots(motsTestsCleared(incr),modelesCleared) match {
        case -1  => result = mots(incr) :: result
        case num => result = modeles(num) :: result
      }
    }
    result.reverse
  }

  /** prends un mot et une liste de modele et renvoie
    * son emplacement dans la liste modele ou -1 si il n'y figure pas
    *
    * @param motATester une String qu'on veut tester
    * @param modeles une liste des modeles
    * @return l'emplacement du mot le plus proche dans la liste ou -1 s'il n'y en a pas (si c'est un petit mot on ne regarde plus)
    */
  def testChaqueMot(motATester: String, modeles: List[String]): Int = {
    if (motATester.length <= 2) { return -1 }
    else {
      val test = modeles.indexWhere((modele) => modele == motATester)
      if (test != (-1)) { test }
      else {
        modeles.indexWhere((modele) => distanceDeHammingInf1(motATester, modele))
      }
    }
  }

  /** prends un mot et une liste de modele et renvoie
    * son emplacement dans la liste modele ou -1 si il n'y figure pas
    *
    * @param motATester une String qu'on veut tester
    * @param modeles une liste des modeles
    * @return l'emplacement du mot le plus proche dans la liste ou -1 s'il n'y en a pas
    */
  def testChaqueMotAvecPetitsMots(motATester: String, modeles: List[String]): Int = {
    val test = modeles.indexWhere((modele) => modele == motATester)
    if (test != (-1)) { 
      test 
    } else {
      modeles.indexWhere((modele) => distanceDeHammingInf1(motATester, modele))
    }
  }

  /** regarde si la distance de Hamming entre deux strings est supérieure ou égal à 1
    * si on voit que la chaine a tester est plus petite que la chaine modele on regarde si c'est du a un decalage
    * si on voit qu'elles sont de meme taille on regarde si il n'y a bien qu'une faute max
    *
    * @param strtest une string a tester
    * @param strmodele le modele
    * @return true si la string test est à 1 de distance maximum de la string modele
    */
  def distanceDeHammingInf1(strtest: String, strmodele: String): Boolean = {
    (strmodele.length - strtest.length == 0 && test1FauteMax(strtest,strmodele)) || 
    (strmodele.length - strtest.length == 1 && testDecalage(strtest,strmodele)) || 
    (strtest.length - strmodele.length == 1 && testDecalage(strmodele,strtest))
  }

  /** prends deux string de meme taille et renvoie si elles sont décalées de 1 (en gros si il y a une lettre erronée)
    *
    * @param strtest a string de test
    * @param strmodele le modele (> le test)
    * @return si il y a une faute max
    */
  def test1FauteMax(strtest: String, strmodele: String): Boolean = {
    strtest.isEmpty() ||
    (strtest(0) == strmodele(0) && test1FauteMax(strtest.tail,strmodele.tail)) ||
    strtest.tail == strmodele.tail
  }

  /** prends deux string un test et un modele et dit s'il manque un caractere dans la string test par rapport a la string modele
    *
    * @param strtest une string test
    * @param strmodele un modele (> le test)
    * @return si la string test a bien juste une lettre de decalage ou moins par rapport au modele
    */
  def testDecalage(strtest: String, strmodele: String): Boolean = {
    strtest.isEmpty() ||
    (strtest(0) == strmodele(0) && testDecalage(strtest.tail,strmodele.tail)) ||
    strtest == strmodele.tail
  }

  /** renvoie la liste en parametre sans accents et en majuscules
    *
    * @param mots une liste de mots (chaines de caracteres)
    * @return la liste de ces memes mots sans accents et en majuscules
    */
  def clearAccentToMaj(mots: List[String]): List[String] = {
    mots match {
      case head :: next => stripAccents(head).toUpperCase :: clearAccentToMaj(next)
      case Nil => Nil
    }
  }
}
