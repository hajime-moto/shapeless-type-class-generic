package user

import util.CommandHandler
import shapeless._
import CommandHandler._

sealed trait UserCommand

final case class CreateUser(id: String) extends UserCommand

final case class UpdatePassword(id: String, password: String) extends UserCommand



object UserCommand {
  implicit val createUserCommandHandler: CommandHandler[CreateUser] =
    new CommandHandler[CreateUser] {
      override def processCommand(command: CreateUser) = println(command)
    }

  implicit val updateUserPasswordCommandHandler: CommandHandler[UpdatePassword] =
    new CommandHandler[UpdatePassword] {
      override def processCommand(command: UpdatePassword) = println(command)
    }
}