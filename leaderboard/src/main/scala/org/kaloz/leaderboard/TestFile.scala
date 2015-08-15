package org.kaloz.leaderboard

import scala.concurrent.Future
import scala.util.Try
import scalaz._
import Scalaz._
import scala.concurrent.ExecutionContext.Implicits.global
//import scalaz.concurrent.Task

object TestFile extends App{

  val convert:Int => Option[Int] = i => Some(i+1)

  List(1,2,3).traverse(convert)

  println(List(1,2,3) <*> List({(x:Int) => x + 1}, {(x:Int) => x * 2}))

  val f: Int => Future[String] = x => Future.successful(x.toString)

  List(1,2,3).traverse(f).onSuccess{case x:List[String] => println(x.mkString)}

  (List(1,2,3).traverse(f) <*> Future.successful({ (x:List[String]) => x.map(_ + "aaa")}))
    .onSuccess{case x:List[String] => println(x.mkString)}

  Future{
    6 / 0
  }.recover{ case x:ArithmeticException => 5}.onSuccess{ case x => println(x)}

  Future.failed(new RuntimeException).recover{ case x:RuntimeException => 5}.onSuccess{ case x => println(x)}

  val f0 = Future{"5"}
  val f1 = Future.fromTry[Int](Try(6/0))
  val f2 = Future{List(1,2,3)}

  (f0 |@| f1 |@| f2)((_, _ ,_ )).onComplete{ case x => println(x)}

  val r:Future[(String, Int, List[Int])] = for{
    r0 <- f0
    r1 <- f1
    r2 <- f2
  } yield (r0, r1, r2)

  r.onComplete{ case x => println(x)}

  some(1) <*> some((x: Int) => x + 1)
  some(1) <*> (some(2) <*> some((x: Int) => (y: Int) => x + y))

  (some(1) |@| some(2)){ _ + _ }


//  val t = Task{
//    5
//  }
//
//  val c = t.attemptRun
//  println(c)
  Thread.sleep(1000)


}

/**
 * Find a way to avoid overloaded methods with the magnet pattern.
 * The three overloaded methods are:
 * `def complete(s: String): Boolean`
 * `def complete(n: Int): Boolean`
 * `def complete(b: Boolean): Boolean`
 *
 * See http://spray.io/blog/2012-12-13-the-magnet-pattern/
 *
 * If all overloads have the same return type there is no need for a type member on the magnet type.
 */
object MagnetPattern extends App {

  sealed trait CompletionMagnet {
    type Result
    def apply(): Result
  }

  class Completable {
    def complete(magnet: CompletionMagnet): magnet.Result =
      magnet()
  }

  object CompletionMagnet {
    implicit def fromString(s: String) =
      new CompletionMagnet {
        type Result = Boolean
        def apply(): Result = if (s == "success") true else false
      }
    implicit def fromInt(i: Int) =
      new CompletionMagnet {
        type Result = Boolean
        def apply(): Result = if (i > 0) true else false
      }
    implicit def fromBoolean(b: Boolean) =
      new CompletionMagnet {
        type Result = Boolean
        def apply(): Result = b
      }
//    implicit def fromFuture[A](b: Future[A]) =
//      new CompletionMagnet {
//        type Result = A
//        def apply(): Result = b.onComplete{
//          case x => x
//        }
//      }
//    implicit def fromHttpResponseFuture[A](future: Future[A]): CompletionMagnet =
//      new CompletionMagnet {
//        type Result = A
//        override def apply(): Result = future.onComplete{
//          case Success(s) => s.asInstanceOf[A]
//          case Failure(e)=> e.
//        }
//
//        override def toString = s"Magnet from future of response = $future"
//      }
  }

  println(new Completable().complete("failure"))
  println(new Completable().complete("success"))
  println(new Completable().complete(0))
  println(new Completable().complete(1))
  println(new Completable().complete(false))
  println(new Completable().complete(true))
//  println(new Completable().complete(Future{5}))

}
//object Applicatives {
//
//  implicit def FutureApplicative(implicit executor: ExecutionContext) = new Applicative[Future] {
//    def point[A](a: => A) = Future(a)
//    def ap[A,B](fa: => Future[A])(f: => Future[A => B]) =
//      (f zip fa) map { case (f1, a1) => f1(a1) }
//  }
//}

//object Roman extends App{
//
//  val converter: Char => Either[String, Int] = c => c match {
//    case 'I' => Right(1)
//    case 'V' => Right(5)
//    case 'X' => Right(10)
//    case 'L' => Right(50)
//    case 'C' => Right(100)
//    case 'D' => Right(500)
//    case 'M' => Right(1000)
//    case 'v' => Right(5000)
//    case 'x' => Right(10000)
//    case 'l' => Right(50000)
//    case 'c' => Right(100000)
//    case 'd' => Right(500000)
//    case 'm' => Right(1000000)
//    case x => Left(s"Not valid characted $x")
//  }
//
//  val t = "vMCDLIII".map(converter).foldLeft((0,0))((acc, current) => {
//    val (accumulator, store) = acc
//    val remove = if(store < current) 2 * store else 0
//    (accumulator + current - remove, current)
//  })._1
//
//  println(t)
//}
