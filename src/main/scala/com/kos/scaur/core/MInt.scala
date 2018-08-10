package com.kos.scaur.core

/**
  * Mutable int
  * @param value value
  */
class MInt(var value:Int){
	def +=(v:Int):Unit = value+=v
	def ++ = value+=1
	def -=(v:Int):Unit = value-=v
	def -- = value-=1
	def apply() = value
	def apply(v:Int) = value = v

	override def toString = value.toString
}
