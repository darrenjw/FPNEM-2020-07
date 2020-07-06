# An evening of Scala

Scala has mutable variables and immutable values, and optional type inference.
```scala
var v = 5
// v: Int = 5
v = v + 1
v
// res1: Int = 6

val x = 5
// x: Int = 5
x
// res2: Int = 5
val y: Double = 2.5
// y: Double = 2.5
y
// res3: Double = 2.5
```
Scala is often described as an object-functional hybrid language. Case classes are a nice lightweight way of representing product data types.
```scala
case class Person(name: String, age: Int)
val p1 = Person("Fred", 25)
// p1: Person = Person("Fred", 25)
p1
// res4: Person = Person("Fred", 25)
p1.name
// res5: String = "Fred"
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
