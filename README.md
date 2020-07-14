# FPNEM-2020-07

Materials for "An evening of Scala" at the [FPNE Meetup](https://www.meetup.com/fpnortheast/) for July 2020

## Some links

* [Scala](https://www.scala-lang.org/) - main language web site
    * [Documentation](https://docs.scala-lang.org/)
	    * [Getting started](https://docs.scala-lang.org/getting-started/)
		* [Scala tour](https://docs.scala-lang.org/tour/tour-of-scala.html)
	* [Download](https://www.scala-lang.org/download/)
* [Scastie](https://scastie.scala-lang.org/) - experiment with Scala in the browser
* [Scala exercises](https://www.scala-exercises.org/) - learn Scala in the browser
* [sbt](https://www.scala-sbt.org/) - the most popular Scala build tool
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - the most popular Scala IDE

## Tonight's interactive session

### Setup

The material for the session are given below. To follow along, most of the examples can be easily replicated using [Scastie](https://scastie.scala-lang.org) in the browser (in *worksheet* mode). Or better, if you have `sbt` (which can be installed via the link above), clone this repo and run `sbt console` from *this* directory. You will get a REPL with everything pre-configured ready to go.

### Materials

* [Markdown document](target/mdoc/Session.md) - read along
* [PDF slides](target/mdoc/Session.pdf) - read along
* [mdoc markdown source document](docs/Session.md) - the source [mdoc](https://scalameta.org/mdoc/) document
* [Scala code](target/mdoc/Session.scala) - Scala code extracted from the markdown doc

### Follow-up

* If you haven't been following along, maybe now would be a good time to reproduce the examples in a Scala REPL.
* To learn more, [Scala exercises](https://www.scala-exercises.org/) is a fast way to start getting to grips with the [standard library](https://www.scala-exercises.org/std_lib/), or commonly used libraries such as [cats](https://www.scala-exercises.org/cats/) or [monocle](https://www.scala-exercises.org/monocle/). 
* To create a new Scala `sbt` project, use a template. eg. `sbt new darrenjw/fps.g8` will generate a template project with a dependency on some commonly used FP libraries.
* If you are interested in Scala for functional programming, check out the [typelevel libraries](https://typelevel.org/).

## My Scala courses

* [Scala for statistical computing and data science](https://github.com/darrenjw/scala-course) - aimed at statisticians and data scientists transitioning to Scala from R or Python - all essential materials are freely available
* [Category theory for pure functional programming in Scala](https://github.com/darrenjw/fps-course) - a course for experienced Scala developers wanting to adopt a more principled, functional programing style - some exercises and code fragments are available

