package java
package lang

import scala.js._

class Runtime private {
  def exit(status: Int) {
    halt0(status)
  }

  def addShutdownHook(hook: Thread) {}
  def removeShutdownHook(hook: Thread) {}

  def halt(status: Int) {
    halt0(status)
  }

  private def halt0(status: Int): Unit = {
    // Well, it is not possible to implement this, is it?
    throw new SecurityException("Cannot terminate a JavaScript program")
  }

  def availableProcessors() = 1
  def freeMemory(): scala.Long = sys.error("Runtime.freeMemory() not implemented")
  def totalMemory(): scala.Long = sys.error("Runtime.totalMemory() not implemented")
  def maxMemory(): scala.Long = Long.MAX_VALUE

  def gc(): Unit = {
    // Ignore
  }

  def runFinalization() {}
  def traceInstructions(on: scala.Boolean) {}
  def traceMethodCalls(on: scala.Boolean) {}

  def load(filename: String): Unit = sys.error("Runtime.load() not implemented")
  def loadLibrary(filename: String): Unit = sys.error("Runtime.loadLibrary() not implemented")
}

object Runtime {
  private val currentRuntime = new Runtime

  def getRuntime() = currentRuntime
}
