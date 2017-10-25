package com.kos.scaur.common

import java.io.{BufferedWriter, File, FileOutputStream, OutputStreamWriter}

/**
  * Created by Kos on 21.09.2017.
  */
object WriteToFile {
	def apply(fileName:String)(body : BufferedWriter ⇒ Unit): Unit = {
		val file = new File(fileName)
		val writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))
		try {
			body(writer)
		} finally {
			writer.close()
		}
	}
}
