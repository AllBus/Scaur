package com.kos.scaur.collections

import scala.collection.generic.CanBuildFrom

object CollectionEx {

	implicit class ImplIterableEx[A,T <: Iterable[A]](val a:T) extends AnyVal {
//
//		def checkAndNext(p:T ⇒ Boolean): T ={
//			if (p(a))
//				a
//			else
//				a
//		}

		/**
		  * Проверяет что предикат выполнен для всех соседних элементов
		  * @param p предикат
		  * @return true если выполнен для всех соседей
		  */
		def checkNeighbor(p:(A,A) ⇒ Boolean): Boolean = {

			if (a.isEmpty)
				true
			else{
				var pred= a.head
				var x= a.tail
				while (x.nonEmpty){
					val h=x.head
					if (!p(pred,h))
						return false
					pred=h
					x=x.tail
				}
				true
			}
		}


		def repr: T = a.asInstanceOf[T]

		def filterMap[B, That](p: A ⇒ Boolean, f: A => B)(implicit bf: CanBuildFrom[T, B, That]): That = {
			def builder = { // extracted to keep method size under 35 bytes, so that it can be JIT-inlined
				val b = bf(repr)
				b.sizeHint(a)
				b
			}

			val b = builder
			for (x <- a) {
				if (p(x))
					b += f(x)
			}
			b.result
		}

	}
}
