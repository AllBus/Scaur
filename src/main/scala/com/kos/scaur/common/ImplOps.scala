package com.kos.scaur.common

/**
  * Created by Kos on 22.09.2017.
  */
object ImplOps {

	implicit final class StringOps(val self: String) extends AnyVal {
		def splitAll(separator: Char): Array[String] = {
			val ab = Array.newBuilder[String]

			val le = self.length
			var i = 0
			var pred = 0
			while (i < le) {
				if (self(i) == separator) {
					ab += self.substring(pred, i)
					pred = i + 1
				}
				i += 1
			}
			ab += self.substring(pred, le)

			ab.result()
		}
	}

	implicit final class NullOpsAssoc[T](private val self: T) extends AnyVal {
		@inline def ??(nullElement: ⇒ T): T = if (self == null) nullElement else self

		@inline def ?>(nullElement: ⇒ Unit): Unit = if (self != null) nullElement

		/**
		  * pipe
		  * @param f
		  * @tparam U
		  * @return
		  */
		@`inline` def pipe[U] (f: T => U) = f(self)

		/**
		  * tap
		  * @param f
		  * @tparam U
		  * @return
		  */
		@`inline` def tap[U](f: T => U): T = {f(self); self}

		@`inline` def #!(str: String = ""): T = {println(str + self); self}
	}

	@`inline` def using[A<:AutoCloseable,T](x : A)(body: A ⇒ T): T ={
		try{
			body(x)
		}finally{
			if (x!=null)
				x.close()
		}
	}

}
