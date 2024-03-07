package com.example.lib_compose_sqlite

open class Person(name :String?)

class Admin(val admin_name:String?, val admin_password:String?):Person(admin_name)
class Student(val student_id : Int?,val student_name:String, val student_password:String?):Person(student_name)
