package tolerance_fautes
import scala.io.Source
import org.apache.commons.lang3.StringUtils.{stripAccents}
object FautesImpl extends FautesTrait {
//ici la liste de mots 'modele' en quelque sorte
  /** applique testChaque mot sur toute une liste de mot et en renvoie la string corrigée
    *
    * @param mots une liste de mots
    * @return la meme liste corrigée
    */

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

  /** prends un mot et une liste de modele et renvoie
    * son emplacement dans la liste modele ou -1 si il n'y figure pas
    *
    * @param motATester une String qu'on veut tester
    * @param modeles une liste des modeles
    * @return l'emplacement du mot le plus proche dans la liste ou -1 s'il n'y en a pas
    */
  def testChaqueMot(motATester: String, modeles: List[String]): Int = {
    modeles.indexWhere((modele) => distanceDeHammingInf1(motATester, modele))
  }

  /** regarde si la distance de Hamming entre deux strings est supérieure ou égal à 1
    * si on voit que la chaine a tester est plus petite que la chaine modele on regarde si c'est du a un decalage
    * si on voit qu'elles sont de meme taille on regarde si il n'y a bien qu'une faute max
    *
    * @param strtest une string a tester
    * @param strmodele le modele (plus grand que le test)
    * @return true si la string test est à 1 de distance maximum de la string modele
    */
  def distanceDeHammingInf1(strtest: String, strmodele: String): Boolean = {
    (strmodele.length - strtest.length == 0 && test1FauteMax(
      strtest,
      strmodele
    )) ||
    (strmodele.length - strtest.length == 1 && testDecalage(strtest, strmodele))
  }

  /** prends deux string de meme taille et renvoie si elles sont décalées de 1 (en gros si il y a une lettre erronée)
    *
    * @param strtest a string de test
    * @param strmodele le modele (> le test)
    * @return si il y a une faute max
    */
  def test1FauteMax(strtest: String, strmodele: String): Boolean = {
    strtest.isEmpty() ||
    (strtest(0) == strmodele(0) && test1FauteMax(
      strtest.tail,
      strmodele.tail
    )) ||
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
    (strtest(0) == strmodele(0) && testDecalage(
      strtest.tail,
      strmodele.tail
    )) ||
    strtest == strmodele.tail
  }

  /** renvoie la liste en parametre sans accents et en majuscules
    *
    * @param mots une liste de mots (chaines de caracteres)
    * @return la liste de ces memes mots sans accents et en majuscules
    */
  def clearAccentToMaj(mots: List[String]): List[String] = {
    mots match {
      case head :: next =>
        stripAccents(head).toUpperCase :: clearAccentToMaj(next)
      case Nil => Nil
    }
  }
}
