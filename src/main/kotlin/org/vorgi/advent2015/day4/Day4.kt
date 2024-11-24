package org.vorgi.org.vorgi.advent2015.day4

import java.security.MessageDigest
import kotlin.experimental.and

fun checkMD5(input: String): Boolean {
  val md = MessageDigest.getInstance("MD5")
  val digest = md.digest(input.toByteArray())

  val first=digest[0]
  val second=digest[1]
  val third=digest[2] and 0xf0.toByte()

  return first==0x00.toByte() && second==0x00.toByte() && third==0x00.toByte()
}

fun checkMD5_6(input: String): Boolean {
  val md = MessageDigest.getInstance("MD5")
  val digest = md.digest(input.toByteArray())

  val first=digest[0]
  val second=digest[1]
  val third=digest[2]

  return first==0x00.toByte() && second==0x00.toByte() && third==0x00.toByte()
}

fun findMD5(code: String): String {
  var index=0
  while(!checkMD5("$code$index")) {
    index++
  }
  return "$index"
}

fun findMD5_6(code: String): String {
  var index=0L
  while(!checkMD5_6("$code$index")) {
    index++
  }
  return "$index"
}

fun main() {
  val inputString = "pqrstuv"
  val md5Hash = findMD5(inputString)
  println("MD5 hash: $md5Hash")

  check(md5Hash=="1048970")

  println(findMD5("ckczppom"))

  println(findMD5_6("ckczppom"))
}


