
var v = 5
v = v + 1
v

val x = 5
x
val y: Double = 2.5
y


case class Person(name: String, age: Int)
val p1 = Person("Fred", 25)
p1
p1.name


sealed trait Pet {
  val name: String
  val age: Int
}
case class Cat(name: String, age: Int) extends Pet
case class Dog(name: String, age: Int) extends Pet

val c = Cat("Garfield", 20)
Dog("Scooby", 10)


def petString(p: Pet): String = p match {
	case Cat(n, _) => "A cat named " + n
	case Dog(n, _) => "A dog named " + n
}

petString(c)


def pairs(n: Int): Int = n*(n-1)/2
pairs(4)

val pairsF: Int => Int = n => n*(n-1)/2
pairsF(4)

pairs _


def pair[A](a1: A, a2: A): (A, A) = (a1, a2)

pair(1, 2)
pair[Int](2, 3)
pair("foo", "bar")

pair[Double] _
(pair[Double] _).curried


val l = List(1, 2, 3)
l.head
l.tail
l(1)
0 :: l
l ++ l


(1 to 10).toList
(0 until 10).toList
val v = Vector(1, 2, 3, 4)
val v2 = v.updated(2, 5)
v2
v


v map (x => x*2)
v.map(x => x.toDouble)
v flatMap {x => Vector(x, x+1)}


for {
  vi <- v
  vj <- v
} yield (vi, vj)


for {
  vi <- v
  vj <- v
} yield (vi, vj)


v flatMap {vi => (v map {vj => (vi, vj)})}


v reduce (_ + _)
v reduce (_ * _)
v.foldLeft(0)(_ + _)
v.scanLeft(0)(_ + _)
v.foldLeft(0.0)(_ + _)


def logFactorial(n: Int): Double =
	if (n <= 1) 0.0 else math.log(n)+logFactorial(n-1)

logFactorial(4)
logFactorial(100)
logFactorial(1000)
logFactorial(10000)


@annotation.tailrec final 
def logFact(n: Int, acc: Double = 0.0): Double =
    if (n <= 1) acc else logFact(n-1, math.log(n) + acc)

logFact(10000)
logFact(10000000)


val naturals = Stream.iterate(1)(_ + 1)


naturals.take(8).toList


val triangular = naturals.scanLeft(0)(_ + _).drop(1)


triangular.take(8).toList


def fib(a: Int = 1, b: Int = 1): Stream[Int] =
    a #:: fib(b, a+b)


fib().take(8).toList


import cats._
import cats.implicits._
import cats.syntax._

3 |+| 4
"foo" |+| "bar"
List(1, 2) |+| List(3, 4, 5)
Map("a" -> 1, "b" -> 2) |+| Map("b" -> 3, "c" -> 4)


def combineAll[A: Monoid](la: List[A]): A = la match {
    case Nil => implicitly[Monoid[A]].empty
	case x :: xs => x |+| combineAll(xs)
}

combineAll(List(2, 3, 4))
combineAll(List("foo", "bar"))


def doubleAll[F[_]: Functor](fi: F[Int]): F[Int] =
    fi map (_ * 2)

doubleAll(List(1, 2, 3))
doubleAll(Vector(2, 3, 4))
doubleAll(Option(3))

