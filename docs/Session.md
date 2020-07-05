# An evening of Scala


```scala mdoc

val x = 5
x
case class Person(name: String, age: Int
                 )
val p1 = Person("Fred", 25
               )
p1
sealed trait Pet {
  val name: String
  val age: Int
}
case class Cat(name: String, age: Int) extends Pet
case class Dog(name: String, age: Int) extends Pet
Cat("Garfield", 20)
Dog("Scooby", 10)


```
