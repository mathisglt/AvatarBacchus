package bdd
import scala.io.{Source, BufferedSource}
import scala.xml.{XML, NodeSeq}
import java.io.{File, PrintWriter}
import java.text.Normalizer
import java.util.ArrayList
import scala.collection.mutable.ArrayBuffer
import bdd.BaseDeDonnees


object BDDImpl extends BaseDeDonnees {
  // TEST2

  val lignesBDD = Source.fromFile("doc/DonneesInitiales.txt").getLines.toArray
  var banwords = Set("")
  var variancesaddr =
    Map(("tnb", "1, Rue Saint-Hélier"), ("hotel", "Place de la Mairie"))
  var varianceslieux =
    Map(("tnb", "Théâtre National de Bretagne"), ("hotel", "Mairie de Rennes"))
  var dictionnaireExpressionsInternationale: List[List[String]] =
    List(List(), List(), List(), List(), List())
  var dictionnairePRNInternationale: List[List[String]] =
    List(List(), List(), List(), List(), List())
  var dictionnairePolitesseInternationale: List[List[String]] =
    List(List(), List(), List(), List(), List())
  val xml = XML.loadFile("partie2/vAr.xml")
  val xmllist = createListFromXML()
  val xmlListLieu = createListLieuFromXML()

  def removeAccents(str: String): String = {
    val normalized = Normalizer.normalize(str, Normalizer.Form.NFD)
    normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
  }

  def removeLiaisonAccentsWords(str: String): String = {
    val liaisonWords =
      List("de", "des", "du", "le", "la", "les", "un", "une", "et")
    val words = str.split("\\s+")
    val result = words.flatMap { word =>
      val lowerWord = word.toLowerCase
      if (liaisonWords.contains(lowerWord)) None
      else Some(lowerWord)
    }
    removeAccents(result.mkString(" ").replaceAll("d'", ""))
  }
  def chercherAdresse(mot: String): String = {
    if (mot.isEmpty()) return "Adresse non trouvée"
    for (ligne <- lignesBDD) {
      val fields = ligne.split(";")
      // Si le mot n'apparait qu'une fois dans les lieux possibles
      if (lignesBDD.filter(x => x.contains(mot)).length <= 1) {
        //Si c'est un mot variant , on récupère son adresse liée
        if (variancesaddr.contains(mot.toLowerCase())) {
          return variancesaddr.getOrElse(mot.toLowerCase(), "")
        }
        //Sinon on récupère son adresse
        else if (
          ((fields(0).toLowerCase).contains(" " + mot.toLowerCase())
            || (fields(0).toLowerCase).contains(mot.toLowerCase() + " "))
          && !banwords.contains(mot.toLowerCase())
        ) {
          return fields(1)
        }
      }
    }
    for ((first, second) <- xmllist) {
      first match {
        case f
            if (removeLiaisonAccentsWords(f) == removeLiaisonAccentsWords(
              mot
            )) =>
          return second
        case _ => // Ne rien faire si le mot n'est pas trouvé
      }
    }
    "Adresse non trouvée"
  }

  def chercherLieu(mot: String): String = {
    if (mot.isEmpty()) { "Lieu non trouvé" }
    for (ligne <- lignesBDD) {
      val fields = ligne.split(";")
      if (varianceslieux.contains(mot.toLowerCase())) {
        return varianceslieux.getOrElse(mot.toLowerCase(), "")
      }
      if (
        (fields(0).toLowerCase).contains(mot.toLowerCase()) && !banwords
          .contains(mot.toLowerCase())
      ) {
        return fields(0)
      }
    }
    for ((first, second) <- xmllist) {
      first match {
        case f
            if (removeLiaisonAccentsWords(f) == removeLiaisonAccentsWords(
              mot
            )) =>
          return f
        case _ => // Ne rien faire si le mot n'est pas trouvé
      }
    }
    "Lieu non trouvé"
  }

  def recupLieux(file: BufferedSource): List[String] = {
    val listeFinale = ArrayBuffer[String]()
    for (line <- file.getLines()) {
      listeFinale += line.split(';')(0)
    }
    listeFinale.toList
  }

  def createDicoExpr(): Unit = {
    val lignesInter =
      Source.fromFile("partie2/international.txt").getLines.toList
    var listefr = List[String]()
    var listeen = List[String]()
    var listees = List[String]()
    var listede = List[String]()
    var listeit = List[String]()
    for (lignes <- lignesInter) {
      if (lignes.equals("Français:")) {
        val index = lignesInter.indexOf(lignes)
        for (i <- index + 1 to index + 9) {
          listefr = listefr ::: lignesInter(i) :: Nil
        }
      }
      if (lignes.equals("Anglais:")) {
        val index = lignesInter.indexOf(lignes)
        for (i <- index + 1 to index + 9) {
          listeen = listeen ::: lignesInter(i) :: Nil
        }
      }
      if (lignes.equals("Espagnol:")) {
        val index = lignesInter.indexOf(lignes)
        for (i <- index + 1 to index + 9) {
          listees = listees ::: lignesInter(i) :: Nil
        }

      }
      if (lignes.equals("Allemand:")) {
        val index = lignesInter.indexOf(lignes)
        for (i <- index + 1 to index + 9) {
          listede = listede ::: lignesInter(i) :: Nil
        }

      }
      if (lignes.equals("Italien:")) {
        val index = lignesInter.indexOf(lignes)
        for (i <- index + 1 to index + 9) {
          listeit = listeit ::: lignesInter(i) :: Nil
        }
      }
      dictionnaireExpressionsInternationale =
        listefr :: listeen :: listees :: listede :: listeit :: Nil
    }
  }
  def createDicoPRN(): Unit = {
    val lignesInter =
      Source.fromFile("partie2/international.txt").getLines.toList
    var listefr, listeen, listees, listede, listeit = List[String]()
    for (lignes <- lignesInter) {
      if (lignes.contains("Français:") && !lignes.equals("Français:")) {
        listefr = listefr ::: lignes.split(":")(1).split(",").toList
        listefr = listefr.map(_.replaceAll(" ", ""))
      }
      if (lignes.contains("Anglais:") && !lignes.equals("Anglais:")) {
        listeen = listeen ::: lignes.split(":")(1).split(",").toList
        listeen = listeen.map(_.replaceAll(" ", ""))
      }
      if (lignes.contains("Espagnol:") && !lignes.equals("Espagnol:")) {
        listees = listees ::: lignes.split(":")(1).split(",").toList
        listees = listees.map(_.replaceAll(" ", ""))
      }
      if (lignes.contains("Allemand:") && !lignes.equals("Allemand:")) {
        listede = listede ::: lignes.split(":")(1).split(",").toList
        listede = listede.map(_.replaceAll(" ", ""))
      }
      if (lignes.contains("Italien:") && !lignes.equals("Italien:")) {
        listeit = listeit ::: lignes.split(":")(1).split(",").toList
        listeit = listeit.map(_.replaceAll(" ", ""))
      }
      dictionnairePRNInternationale =
        listefr :: listeen :: listees :: listede :: listeit :: Nil
    }
  }
  def createDicoSalutations(langueActuelleStr: String, langueActuelle: Int): List[String] = {
    val lignesInter = Source.fromFile("partie2/international.txt").getLines.toList
    val listeSalutations = List[String](lignesInter(7 + langueActuelle))
    val listeSansLangue = listeSalutations(0).split(": ")
    val listeFinale = listeSansLangue(1).split(",").toList
    listeFinale
  }

  def createDicoRecherche(langueActuelleStr: String, langueActuelle: Int): List[String] = {
    val lignesInter = Source.fromFile("partie2/international.txt").getLines.toList
    val listeRecherche= List[String](lignesInter(14 + langueActuelle))
    val listeSansLangue = listeRecherche(0).split(": ")
    val listeFinale = listeSansLangue(1).split(",").toList
    listeFinale
  }

  def langueDuMot(mot: String): String = {
    createDicoPRN()
    if (dictionnairePRNInternationale(0).contains(mot.toLowerCase())) {
      return "français"
    } else if (dictionnairePRNInternationale(1).contains(mot.toLowerCase())) {
      return "english"
    } else if (dictionnairePRNInternationale(2).contains(mot.toLowerCase())) {
      return "español"
    } else if (dictionnairePRNInternationale(3).contains(mot.toLowerCase())) {
      return "deutsch"
    } else if (dictionnairePRNInternationale(4).contains(mot.toLowerCase())) {
      return "italiano"
    } else "langue non détectée"
  }

  def getDicoExpr(): List[List[String]] = {
    createDicoExpr; dictionnaireExpressionsInternationale
  }
  def getDicoPRN(): List[List[String]] = {
    createDicoPRN; dictionnairePRNInternationale
  }

  def recuplieuxBases() : List[String] ={
    val dico = lignesBDD.flatMap(_.split(";").head.split("\\s+")).toList
    dico.map(mot => removeAccents(mot.toLowerCase))
  }
  def createListFromXML(): List[(String, String)] = {
    val organizations = xml \\ "organization"
    organizations.flatMap { organization =>
      val name = (organization \\ "name").headOption.map(_.text.trim).getOrElse("")
      val streetName = (organization \\ "street" \\ "name").text.trim
      val streetNumber = (organization \\ "street" \\ "number").text.trim
      val cityName = (organization \\ "city").text.trim // pour ne prendre que les adresses de Rennes
      val fullStreet = if (streetNumber.nonEmpty) s"$streetNumber, $streetName" else streetName
      if (name.nonEmpty && streetName.nonEmpty && cityName.equals("Rennes")) Some(name -> fullStreet)
      else None
    }.toList
  }



}
