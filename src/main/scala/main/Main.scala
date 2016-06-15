package main

import user.{CreateUser, UpdatePassword, UserCommand}
import util.CommandHandler

object Main extends App {

  import shapeless._
  import UserCommand._
  import CommandHandler._

  object CommandHandlerRunner {
    def processCommand[C](command: C)(implicit commandHandler: CommandHandler[C]) =
      commandHandler.processCommand(command)
  }

  val cmd1: UserCommand = CreateUser("foo")
  val cmd2 = UpdatePassword("id", "open sesame")

  CommandHandlerRunner.processCommand(cmd1)
}
