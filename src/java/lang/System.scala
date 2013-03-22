package java
package lang

import scala.js._
import JSDynamic.window

object System {
  var out: java.io.PrintStream = StandardOutPrintStream
  var err: java.io.PrintStream = StandardErrPrintStream
  var in: java.io.InputStream = null

  def currentTimeMillis(): scala.Long = {
    (new Date).getTime().toLong
  }

  def arraycopy(src: Object, srcPos: scala.Int,
      dest: Object, destPos: scala.Int, length: scala.Int): Unit = {
    val jsSrc = reflect.Array.getUnderlying(src)
    val jsDest = reflect.Array.getUnderlying(dest)
    var i = 0
    while (i < length) {
      jsSrc(srcPos+i) = jsDest(destPos+i)
      i += 1
    }
  }

  def identityHashCode(x: Object): scala.Int = {
    // TODO
    42
  }

  def getProperties(): java.util.Properties = sys.error("System.getProperties() not implemented")
  def getProperty(key: String): String = sys.error("System.getProperty() not implemented")
  def getProperty(key: String, default: String): String = sys.error("System.getProperty() not implemented")
  def clearProperty(key: String): String = sys.error("System.clearProperty() not implemented")
  def setProperty(key: String, value: String): String = sys.error("System.setProperty() not implemented")

  def getenv(): java.util.Map[String,String] = sys.error("System.getenv() not implemented")
  def getenv(name: String): String = sys.error("System.getenv() not implemented")

  def exit(status: scala.Int) = Runtime.getRuntime().exit(status)
  def gc() = Runtime.getRuntime().gc()
}

private[lang] object StandardOutPrintStream extends io.PrintStream(StandardOut, true) {
  override protected[lang] def writeString(s: String): Unit = {
    if (window.console && ((s != "\n"):JSBoolean))
      window.console.log(s)
  }
}

private[lang] object StandardErrPrintStream extends io.PrintStream(StandardErr, true) {
  override protected[lang] def writeString(s: String): Unit = {
    if (window.console && ((s != "\n"):JSBoolean))
      window.console.error(s)
  }
}

private[lang] object StandardOut extends io.OutputStream {
  def write(b: Int) = StandardOutPrintStream.writeString(b.toChar.toString)
}

private[lang] object StandardErr extends io.OutputStream {
  def write(b: Int) = StandardErrPrintStream.writeString(b.toChar.toString)
}
