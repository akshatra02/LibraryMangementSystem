package com.example.lib_compose_sqlite

open class Person(name :String)

data class Admin(val adminId : Int,val adminName:String, val adminPassword:String):Person(adminName)
data class Student(val studentId : Int,val studentName:String):Person(studentName)
