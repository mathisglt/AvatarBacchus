package langue

object LangueImpl extends LangueTrait{
    private var langueActuelle = 0
    
    def changementLangue(langue : String) : Unit={

    }

    def langueSuivante() : Unit ={
        langueActuelle = (langueActuelle + 1) % 5
    }

    def getLangueActuelle() : Int ={
        langueActuelle
    }

    def setLangueActuelle(langue : String) : Unit={
        langue match {
            case "english" => langueActuelle = 1
            case "español" => langueActuelle = 2
            case "deutsch" => langueActuelle = 3
            case "italiano" => langueActuelle = 4
            case _ => langueActuelle = 0
        }
    }
  
}
