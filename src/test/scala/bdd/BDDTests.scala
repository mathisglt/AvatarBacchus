package bdd

import org.junit.Test
import org.junit.Assert._

class TestUnitaire {
    val bdd = BDDImpl

    @Test
    def test1():Unit ={
        println(bdd.chercherAdresse("Mairie"))
}

}