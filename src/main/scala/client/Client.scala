package client

import machine.MachineImpl
import automaticTester.TestAvatar

// imports à suppr
import bdd.BDDImpl

object Client extends App {
  TestAvatar.check(MachineImpl)
}
