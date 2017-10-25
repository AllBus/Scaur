package com.kos.scaur.common

import java.io.{BufferedReader, File, FileInputStream, InputStreamReader}

import scala.io.StdIn

/**
  * Created by Kos on 18.09.2017.
  */
object WhileReadLine {
	def apply(endLine:String)( body : String ⇒ Unit): Unit = {
		var sx = StdIn.readLine()
		while (sx != endLine) {
			body(sx)
			sx = StdIn.readLine()
		}
	}

	def readFile(file: File, encoding: String)( body: (String) ⇒ Unit): Unit = {
		val reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
		try {
			var s = ""
			while ( {
				s = reader.readLine()
				s != null
			}) {
				body(s)
			}
		} finally {
			reader.close()
		}
	}


}
