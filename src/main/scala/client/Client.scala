package client

import machine.MachineImpl
import automaticTester.TestAvatar

// imports à suppr
import bdd.BDDImpl

object Client extends App {
  // TestAvatar.check(MachineImpl)
  val list_bdd_xml = BDDImpl.createListFromXML()
  println(list_bdd_xml)

}
