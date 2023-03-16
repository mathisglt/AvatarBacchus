package bdd

import org.junit.Test
import org.junit.Assert._

class TestUnitaire {
    bdd = BDDImpl

    @Test
    def test1():Unit ={
        println(bdd.chercherAdresse("Mairie"))
}

}