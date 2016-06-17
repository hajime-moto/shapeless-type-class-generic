package adtuser

sealed trait UserCommand
final case class CreateUser(id: String) extends UserCommand
final case class UpdatePassword(id: String, password: String) extends UserCommand