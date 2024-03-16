package com.example.lib_compose_sqlite

open class Person(val name :String,val password:String)

 class Admin(
  val adminId: Int,
  val adminName: String,
  val adminPassword: String,
 ):Person(adminName,adminPassword)
 class Student(
  val studentId: Int,
  val studentName: String,
  val studentPassword:String
 ):Person(studentName,studentPassword)
