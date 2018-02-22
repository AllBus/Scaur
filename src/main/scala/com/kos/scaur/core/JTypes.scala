package com.kos.scaur.core

import java.util.Collections

import scala.language.{experimental, implicitConversions}
import scala.reflect.ClassTag

/**
  * Created by Kos on 21.12.2015.
  */
object JTypes {
	type JStringBuilder = java.lang.StringBuilder

	type JInt = java.lang.Integer
	type JLong = java.lang.Long
	type JFloat = java.lang.Float
	type JDouble = java.lang.Double

	type JArrayList[T] = java.util.ArrayList[T]

	type JCollection[T] = java.util.Collection[T]
	type JList[T] = java.util.List[T]
	type JMap[T, K] = java.util.Map[T, K]


	implicit final class JCollectionImplicit[T](val collection: JCollection[T]) extends AnyRef{

		@inline
		def  map[S](f: T => S):JList[S]={
			val iterator=collection.iterator()
			val result=new JArrayList[S]
			while(iterator.hasNext)
				result.add(f(iterator.next()))
			result
		}

		@inline
		def filterMap[S](p: T ⇒ Boolean, f: T => S):JList[S]={
			val iterator=collection.iterator()
			val result=new JArrayList[S]
			while(iterator.hasNext) {
				val it=iterator.next()
				if (p(it))
					result.add(f(it))
			}
			result
		}

		@inline
		def filter(p: T ⇒ Boolean):JList[T]={
			val iterator=collection.iterator()
			val result=new JArrayList[T]
			while(iterator.hasNext) {
				val it=iterator.next()
				if (p(it))
					result.add(it)
			}
			result
		}

		@inline
		def  mapSeq[S](f: T => S):Seq[S]={
			val iterator=collection.iterator()
			val result=Seq.newBuilder[S]
			while(iterator.hasNext)
				result+= f(iterator.next())
			result.result()
		}


		@inline
		def  mapSet[S](f: T => S):Set[S]={
			val iterator=collection.iterator()
			val result=Set.newBuilder[S]
			while(iterator.hasNext)
				result+= f(iterator.next())
			result.result()
		}

		@inline
		def  mapArrayList(f: T => Integer):JArrayList[Integer]={
			val iterator=collection.iterator()
			val result=new JArrayList[Integer]
			while(iterator.hasNext)
				result.add(f(iterator.next()))
			result
		}

		@inline
		def foreach(f: T => Unit):Unit ={
			val iterator=collection.iterator()
			while(iterator.hasNext)
				f(iterator.next())
		}

	}

	implicit final class JListImplicit[T](val collection: JList[T]) extends AnyRef{
		@inline
		def apply(index: Int) : T = collection.get(index)

		@inline
		def +=(element:T) = collection.add(element)

		@inline
		def -=(element:T) = collection.remove(element)
	}

	implicit final class JMapImplicit[K,T](val collection: JMap[K,T]) extends AnyRef{

		@inline
		def map[S](f: (K,T) => S):JList[S]={
			val iterator=collection.keySet().iterator()
			val result=new JArrayList[S]
			while (iterator.hasNext) {
				val item=iterator.next()
				result.add(f(item, collection(item)))
			}
			result
		}

		@inline
		def  reverseMap[S](f: (K,T) => S):JList[S]={
			val iterator=collection.keySet().iterator()
			val result=new JArrayList[S]
			while (iterator.hasNext) {
				val item=iterator.next()
				result.add(f(item, collection(item)))
			}
			Collections.reverse(result)
			result
		}

		@inline
		def foreach(f: (K,T) => Unit):Unit = {
			val iterator = collection.keySet().iterator()
			while (iterator.hasNext) {
				val item=iterator.next()
				f(item, collection(item))
			}
		}

		@inline
		def apply(key: K) : T = collection.get(key)

	}

	implicit final class Allimplicit[A](val obj:A) extends AnyRef{

		@inline
		def as[B: ClassTag](f : B => Unit): Unit ={
//			if (obj.isInstanceOf[B]){
//				f(obj.asInstanceOf[B])
//			}
			obj match {
				case t: B => f(t)
				case _ =>
			}
		}
	}
}
