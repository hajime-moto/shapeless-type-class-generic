package util

object CommandHandlerRunner {
  def processCommand[C](command: C)(implicit commandHandler: CommandHandler[C]) =
    commandHandler.processCommand(command)
}