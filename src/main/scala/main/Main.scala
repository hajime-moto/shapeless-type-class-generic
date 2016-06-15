package main

import user.{CommandHandlerRunner, CreateUser, UpdatePassword, UserCommand}
import util.CommandHandler

object Main extends App {

  import shapeless._
  import UserCommand._
  import CommandHandler._

  val cmd1: UserCommand = CreateUser("foo")
  val cmd2 = UpdatePassword("id", "open sesame")

  CommandHandlerRunner.processCommand(cmd1)
}
