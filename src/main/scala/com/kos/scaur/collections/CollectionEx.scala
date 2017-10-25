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
	}
}
