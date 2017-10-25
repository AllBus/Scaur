package com.kos.scaur.common

import scala.io.StdIn

/**
  * Created by Kos on 27.09.2016.
  */
class ArgReader(val args:Array[String]) {

	var tekPos=0
	val argsLength=args.length

	def nextString(helpText:String): String ={
		if (tekPos<argsLength){
			tekPos+=1
			args(tekPos-1)
		}else{
			println(helpText)
			StdIn.readLine
		}
	}

	def nextInt(helpText:String): Int ={
		(if (tekPos<argsLength){
			tekPos+=1
			args(tekPos-1)
		}else{
			println(helpText)
			StdIn.readLine
		}).toInt
	}

}
