package com.example.lib_compose_sqlite

open class Person(name :String)

 class Admin(val adminId : Int,val adminName:String, val adminPassword:String):Person(adminName)
 class Student(val studentId : Int,val studentName:String):Person(studentName)
