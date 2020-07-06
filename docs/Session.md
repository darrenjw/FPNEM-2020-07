# An evening of Scala

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
Scala is often described as an object-functional hybrid language. Case classes are a nice lightweight way of representing product data types.
```scala mdoc
case class Person(name: String, age: Int)
val p1 = Person("Fred", 25)
p1
p1.name
```
Sealed traits can model sum types.
```scala
sealed trait Pet {
  val name: String
  val age: Int
}
case class Cat(name: String, age: Int) extends Pet
case class Dog(name: String, age: Int) extends Pet

Cat("Garfield", 20)
Dog("Scooby", 10)
```



* SBT - console
* Scastie?? Scala in the browser thing? Scala tutorials, intros, exercises, etc.
* Mutable and immutable objects
* Case classes
* Pattern matching
* HoFs
* Collections
* compose / andThen
* type inference
* tail recursion
* Streams
* Cats - Monoids, Functors and Monads - HKTs
* Monoid instance for map!
* Look at my FPS notes for inspiration...

