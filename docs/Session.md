# An evening of Scala

## An evening of Scala

* A quick look at Scala from a functional programmer's perspective
* Darren Wilkinson - [darrenjw.github.io](https://darrenjw.github.io/)

# Classes, objects and values

## Mutable and immutable values

Scala has mutable variables and immutable values, and optional type inference.
```scala mdoc
var v = 5
v = v + 1
v

val x = 5
x
val y: Double = 2.5
y
```

## Case classes

Scala is often described as an object-functional hybrid language. Case classes are a nice lightweight way of representing *product data types*.
```scala mdoc
case class Person(name: String, age: Int)
val p1 = Person("Fred", 25)
p1
p1.name
```

## Sum types

Sealed traits can model *sum types*.
```scala mdoc
sealed trait Pet {
  val name: String
  val age: Int
}
case class Cat(name: String, age: Int) extends Pet
case class Dog(name: String, age: Int) extends Pet

val c = Cat("Garfield", 20)
Dog("Scooby", 10)
```
Note that by default, case class attributes are immutable.

## Pattern matching

Scala has reasonably sophisticated support for *pattern-matching*, and case classes are often used in conjunction with pattern-matching.
```scala mdoc
def petString(p: Pet): String = p match {
	case Cat(n, _) => "A cat named " + n
	case Dog(n, _) => "A dog named " + n
}

petString(c)
```
If a trait is declared `sealed`, then all cases must be defined in the same source file, and incomplete pattern matches will trigger a compiler warning.

# Methods and functions

## Function types

Scala makes a distinction between *methods*, declared using `def`, which is code associated with a particular class or object, and *functions* which are values having a function type. Methods can be converted to functions (sometimes transparently, but sometimes using an `_`). Methods can be type-polymorphic, whereas functions must have an explicit function type.
```scala mdoc
def pairs(n: Int): Int = n*(n-1)/2
pairs(4)

val pairsF: Int => Int = n => n*(n-1)/2
pairsF(4)

pairs _
```

## Type polymorphism and currying

```scala mdoc
def pair[A](a1: A, a2: A): (A, A) = (a1, a2)

pair(1, 2)
pair[Int](2, 3)
pair("foo", "bar")

pair[Double] _
(pair[Double] _).curried
```
The return type of a method or function can usually be inferred, but it is usually considered good practice to declare it.

## HoFs

Since functions are just values, they can be passed around like any other value. Scala therefore supports *higher-order functions* - functions which either accept a function as an argument or return a function as a result (or both). HoFs are very often used in conjunction with collections.

# Collections

## Lists and vectors

Scala has a wide array of collection types, both mutable and immutable, but immutable by default.
```scala mdoc:reset
val l = List(1, 2, 3)
l.head
l.tail
l(1)
0 :: l
l ++ l
```

## Ranges and indexing

```scala mdoc
(1 to 10).toList
(0 until 10).toList
val v = Vector(1, 2, 3, 4)
val v2 = v.updated(2, 5)
v2
v
```

## Monadic collections

`List` has good *push* and *pop* performance, but `Vector` is an immutable data structure with better random access and update performance. The collections are *monadic*, supporting HoFs such as `map` and `flatMap` (called `bind` or `>>=` in Haskell).
```scala mdoc
v map (x => x*2)
v.map(x => x.toDouble)
v flatMap {x => Vector(x, x+1)}
```

## `for` expressions

Scala has `for`-expressions (similar to Haskell's `do`-notation) for writing pure functional monadic expressions in an imperative style.
```scala mdoc
for {
  vi <- v
  vj <- v
} yield (vi, vj)
```

## Scans and folds

The collections also support *scans*, *folds* and *reductions*.
```scala mdoc
v reduce (_ + _)
v reduce (_ * _)
v.foldLeft(0)(_ + _)
v.scanLeft(0)(_ + _)
v.foldLeft(0.0)(_ + _)
```

# Recursion

## Recursion

Scala methods and functions can be recursive, though in this case, the return type must be provided.

```scala mdoc
def logFactorial(n: Int): Double =
	if (n <= 1) 0.0 else math.log(n)+logFactorial(n-1)

logFactorial(4)
logFactorial(100)
logFactorial(1000)
logFactorial(10000)
// logFactorial(100000) // stack overflow
```
Eventually this will blow the stack, since it is not tail-recursive (the function modifies the result of the recursive call). 

## Tail recursion

The compiler recognises tail-recursions, and performs tail call elimination.
```scala mdoc
@annotation.tailrec final 
def logFact(n: Int, acc: Double = 0.0): Double =
    if (n <= 1) acc else logFact(n-1, math.log(n) + acc)

logFact(10000)
logFact(10000000)
```
Note that the `tailrec` annotation is optional - the compiler will eliminate the tail call regardless of whether it is specified. The annotation ensures that an error will be thrown at compile time in the event that for some reason the compiler can not eliminate the tail call. This is typically better than discovering this fact at run time.

# Streams

## Streams

The Scala standard library includes a `Stream` type that is just a lazy list. There are quite a few limitations of such a stream, and the type is actually deprecated in the latest versions of Scala. There are many better stream types provided by other libraries (such as [**monix**](https://monix.io/) and [**fs2**](https://fs2.io/)), but the simple built-in type is good enough to illustrate some of the basic concepts.
```scala mdoc:silent
val naturals = Stream.iterate(1)(_ + 1)
```
```scala mdoc
naturals.take(8).toList
```
```scala mdoc:silent
val triangular = naturals.scanLeft(0)(_ + _).drop(1)
```
```scala mdoc
triangular.take(8).toList
```

## Fibonacci numbers

```scala mdoc:silent
def fib(a: Int = 1, b: Int = 1): Stream[Int] =
    a #:: fib(b, a+b)
```
```scala mdoc
fib().take(8).toList
```
This is actually stack-safe, though it might not be immediately obvious why. Again, you might prefer to use a proper streaming data library.


# Type classes

## Type classes

Scala supports a very powerful (but dangerous) programming feature allowing values and classes to be passed into functions and otherwise "summoned" *implicitly*. There are many potential applications of *implicits*, but in Scala 2 they are often used to support the *type class* programming pattern, popularised by Haskell. Note that Scala 3 ("dotty") provides more direct support for the type class pattern. The [Cats](https://typelevel.org/cats/) library provides the standard type classes from category theory, together with instance definitions for types in the standard library, and convenient syntax. eg. The *monoid* type class is very useful and commonly used, and comes with the syntax `|+|` for the associative combine operation.

## Monoid

```scala mdoc
import cats._
import cats.implicits._
import cats.syntax._

3 |+| 4
"foo" |+| "bar"
List(1, 2) |+| List(3, 4, 5)
Map("a" -> 1, "b" -> 2) |+| Map("b" -> 3, "c" -> 4)
```

## Monoid type class constraint

```scala mdoc
def combineAll[A: Monoid](la: List[A]): A = la match {
    case Nil => implicitly[Monoid[A]].empty
	case x :: xs => x |+| combineAll(xs)
}

combineAll(List(2, 3, 4))
combineAll(List("foo", "bar"))
```

## Functor

Defining a type class like `Monoid` in a language like Scala requires parameterised types (generics). Defining parameterised type classes requires parameterised types that are themselves parameterised - higher kinded types (HKTs). Very few mainstream programming languages support HKTs, but Scala is one of them. These can be used to define type classes like `Functor` and `Monad`.

## Functor type class constraint

```scala mdoc
def doubleAll[F[_]: Functor](fi: F[Int]): F[Int] =
    fi map (_ * 2)

doubleAll(List(1, 2, 3))
doubleAll(Vector(2, 3, 4))
doubleAll(Option(3))
```
Parameterising functions this way ensures that you don't use any idiosyncratic feature of the particular container type that is being used, and will render trivial the switching of the container type in some later refactor of the code.

# Wrap-up

## Conclusions

* Scala is a very powerful strongly typed language, with type inference, higher order functions and higher-kinded types, monadic for-expressions, and excellent support for immutable values and data structures, type classes, and functional programming more generally
* It also has a very mature tooling ecosystem, with excellent build tools, IDEs, testing frameworks, etc.
* It benefits from the JVM ecosystem, including the [Sonatype central repository](https://search.maven.org/), for the sharing of code-signed libraries
* Scala is not a pure functional language, but an increasing number of Scala developers are embracing a pure functional approach to program development in Scala, and are creating a [library ecosystem](https://typelevel.org/) to support this
