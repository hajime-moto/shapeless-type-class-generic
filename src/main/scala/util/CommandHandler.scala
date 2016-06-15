package util

import shapeless._
import shapeless._
import CommandHandler._

trait CommandHandler[C] {
  def processCommand(command: C): Unit
}

object CommandHandler {

  implicit val cnilCommandHandler: CommandHandler[CNil] =
    new CommandHandler[CNil] {
      override def processCommand(t: CNil): Unit = ()
    }

  implicit def coproductConsCommandHandler[L, R <: Coproduct](implicit
                                                              lch: CommandHandler[L],
                                                              rch: CommandHandler[R]
                                                             ): CommandHandler[L :+: R] =
    new CommandHandler[L :+: R] {
      override def processCommand(t: L :+: R): Unit = t match {
        case Inl(l) => lch.processCommand(l)
        case Inr(r) => rch.processCommand(r)
      }
    }

  implicit def genericCommandHandler[A, G](implicit
                                           gen: Generic.Aux[A, G],
                                           cch: Lazy[CommandHandler[G]]
                                          ): CommandHandler[A] =
    new CommandHandler[A] {
      def processCommand(a: A): Unit = cch.value.processCommand(gen.to(a))
    }
}
