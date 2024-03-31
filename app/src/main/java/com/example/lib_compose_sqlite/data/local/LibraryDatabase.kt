package com.example.lib_compose_sqlite.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lib_compose_sqlite.data.local.admin.AdminDao
import com.example.lib_compose_sqlite.data.local.admin.AdminEntity
import com.example.lib_compose_sqlite.data.local.books.BookDao
import com.example.lib_compose_sqlite.data.local.books.BookEntity
import com.example.lib_compose_sqlite.data.local.student.StudentDao
import com.example.lib_compose_sqlite.data.local.student.StudentEntity

@Database(entities = [BookEntity::class, AdminEntity::class, StudentEntity::class], version = 1,exportSchema = true)
abstract class LibraryDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun adminDao(): AdminDao
    abstract fun studentDao(): StudentDao

    companion object{
        @Volatile
        private var INSTANCE: LibraryDatabase? = null
        fun getDatabase(context: Context): LibraryDatabase {
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(context, LibraryDatabase::class.java,"room_library_database")
                    .build()
                    .also {
                        INSTANCE = it

                }
            }
        }

    }
}