package main

import shapeless._

import adtuser._
import util._

object Main extends App {

  // we cannot add these implicits to the UserCommand companion object
  // you could add them to the CommandHandler companion object or in a
  // different object (but then you would need to import them of course)
  implicit val createUserCommandHandler: CommandHandler[CreateUser] =
    new CommandHandler[CreateUser] {
      override def processCommand(command: CreateUser) = println(command)
    }

  implicit val updateUserPasswordCommandHandler: CommandHandler[UpdatePassword] =
    new CommandHandler[UpdatePassword] {
      override def processCommand(command: UpdatePassword) = println(command)
    }


  val cmd1: UserCommand = CreateUser("foo")
  val cmd2 = UpdatePassword("id", "open sesame")

  val cmds = List(cmd1, cmd2)

  CommandHandlerRunner.processCommand(cmd1)
  cmds.foreach(CommandHandlerRunner.processCommand[UserCommand] _)
}
