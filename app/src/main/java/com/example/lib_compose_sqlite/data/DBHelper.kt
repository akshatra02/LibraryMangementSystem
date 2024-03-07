package com.example.lib_compose_sqlite.data
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getStringOrNull
import com.example.lib_compose_sqlite.*

class DBHelper(private val context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "LibraryDatabse.db"
        private const val DATABASE_VERSION = 1
        private const val ADMIN_TABLE_NAME = "Admin"
        private const val ADMIN_COLUMN_ID = "id"
        private const val ADMIN_COLUMN_NAME = "username"
        private const val ADMIN_COLUMN_PASSWORD = "password"

        private const val STUDENT_TABLE_NAME = "Student"
        private const val STUDENT_COLUMN_ID = "StudentId"
        private const val STUDENT_COLUMN_NAME = "StudentName"
        private const val STUDENT_COLUMN_PASSWORD = "StudentPassword"
        private const val STUDENT_COLUMN_RESERVEDBOOKS = "StudentReservedBooks"
        private const val STUDENT_COLUMN_RESERVEDBOOKS_COUNT = "BookCount"

        private const val BOOK_TABLE_NAME = "Books"
        private const val BOOK_COLUMN_ID = "BookId"
        private const val BOOK_COLUMN_TITLE = "BookTitle"
        private const val BOOK_COLUMN_AUTHOR = "BookAuthor"
        private const val BOOK_COLUMN_TYPE = "BookType"
        private const val BOOK_COLUMN_STATUS = "BookStatus"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createAdminTableQuery = ("CREATE TABLE $ADMIN_TABLE_NAME (" +
                "$ADMIN_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$ADMIN_COLUMN_NAME TEXT," +
                "$ADMIN_COLUMN_PASSWORD TEXT)")
        val createStudentTableQuery = ("CREATE TABLE $STUDENT_TABLE_NAME (" +
                "$STUDENT_COLUMN_ID INTEGER PRIMARY KEY ," +
                "$STUDENT_COLUMN_NAME TEXT," +
                "$STUDENT_COLUMN_PASSWORD TEXT," +
                "$STUDENT_COLUMN_RESERVEDBOOKS TEXT," +
                "$STUDENT_COLUMN_RESERVEDBOOKS_COUNT INTEGER)")
        val createBookTableQuery = ("CREATE TABLE $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_ID INTEGER PRIMARY KEY ," +
                "$BOOK_COLUMN_TITLE TEXT," +
                "$BOOK_COLUMN_AUTHOR TEXT," +
                "$BOOK_COLUMN_TYPE TEXT," +
                "$BOOK_COLUMN_STATUS TEXT)")
        //Book entiries
        val b1 = Book(101, "Dravidian Lang tech", "Ramaswamy", BookType.Journal, BookStatus.Available)
        val b2 = Book(102, "Nine Tale Fox", "Wil Tal", BookType.Fiction, BookStatus.Reserved)
        val b3 = Book(103, "Forbes", "Kim", BookType.Magazine, BookStatus.Available)
        val b4 = Book(104, "M.S.Dhoni", "Aaradhiya", BookType.Biography, BookStatus.Reserved)
        val b5 = Book(105, "1990's Vision", "Rukmani", BookType.Historic, BookStatus.Available)

        val addBooksQuery1 = ("INSERT INTO $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_ID," +
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$BOOK_COLUMN_STATUS) VALUES (${b1.bookId},\"${b1.title}\",\"${b1.author}\",\"${b1.bookType}\",\"${b1.status}\")")
        val addBooksQuery2 = ("INSERT INTO $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_ID," +
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$BOOK_COLUMN_STATUS) VALUES (${b2.bookId},\"${b2.title}\",\"${b2.author}\",\"${b2.bookType}\",\"${b2.status}\")")
        val addBooksQuery3 = ("INSERT INTO $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_ID," +
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$BOOK_COLUMN_STATUS) VALUES (${b3.bookId},\"${b3.title}\",\"${b3.author}\",\"${b3.bookType}\",\"${b3.status}\")")
        val addBooksQuery4 = ("INSERT INTO $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_ID," +
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$BOOK_COLUMN_STATUS)VALUES (${b4.bookId},\"${b4.title}\",\"${b4.author}\",\"${b4.bookType}\",\"${b4.status}\")")
        val addBooksQuery5 = ("INSERT INTO $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_ID," +
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$BOOK_COLUMN_STATUS) VALUES (${b5.bookId},\"${b5.title}\",\"${b5.author}\",\"${b5.bookType}\",\"${b5.status}\")")

        db?.execSQL(createAdminTableQuery)
        db?.execSQL(createStudentTableQuery)
        db?.execSQL(createBookTableQuery)
        db?.execSQL(addBooksQuery1)
        db?.execSQL(addBooksQuery2)
        db?.execSQL(addBooksQuery3)
        db?.execSQL(addBooksQuery4)
        db?.execSQL(addBooksQuery5)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery1 = "DROP TABLE IF EXISTS $ADMIN_TABLE_NAME"
        val dropTableQuery2 = "DROP TABLE IF EXISTS $STUDENT_TABLE_NAME"
        val dropTableQuery3 = "DROP TABLE IF EXISTS $BOOK_TABLE_NAME"
        db?.execSQL(dropTableQuery1)
        db?.execSQL(dropTableQuery2)
        db?.execSQL(dropTableQuery3)
        onCreate(db)

    }

    fun addAdmin(admin: Admin?): Long {
        val values = ContentValues().apply {
            put(ADMIN_COLUMN_NAME, admin?.admin_name)
            put(ADMIN_COLUMN_PASSWORD, admin?.admin_password)
        }
        val db = writableDatabase
        return db.insert(ADMIN_TABLE_NAME, null, values)
    }

    fun loginadmin(admin: Admin?): Boolean {
        val db = this.readableDatabase
        val selection = "$ADMIN_COLUMN_NAME =? AND $ADMIN_COLUMN_PASSWORD =?"
        val selectionArgs = arrayOf(admin?.admin_name, admin?.admin_password)
//        val cursor:Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ")
        val cursor = db.query(false, ADMIN_TABLE_NAME, null, selection, selectionArgs, null, null, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    fun addStudent(student: Student?, StudentReservedBook: String = "", StudentReservedBookCount: Int = 0): Long {
        val values = ContentValues().apply {
            put(STUDENT_COLUMN_ID, student?.student_id)
            put(STUDENT_COLUMN_NAME, student?.student_name)
            put(STUDENT_COLUMN_PASSWORD, student?.student_password)
            put(STUDENT_COLUMN_RESERVEDBOOKS, StudentReservedBook)
            put(STUDENT_COLUMN_RESERVEDBOOKS_COUNT, StudentReservedBookCount)
        }
        val db = writableDatabase

        return db.insert(STUDENT_TABLE_NAME, null, values)
    }

    fun loginstudent(student: Student): Boolean {
        val db = this.readableDatabase
        val selection = "$STUDENT_COLUMN_ID =? AND $STUDENT_COLUMN_NAME =? AND $STUDENT_COLUMN_PASSWORD =?"
        val selectionArg =
            arrayOf(student.student_id.toString(), student.student_name.toString(), student.student_password.toString())
        val cursor = db.query(false, STUDENT_TABLE_NAME, null, selection, selectionArg, null, null, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    fun getBooks(): List<Book> {
        var book_list = mutableListOf<Book>()
        val db = this.readableDatabase
        val readBook = "SELECT * FROM $BOOK_TABLE_NAME"
        val cursor = db.rawQuery(readBook, null)
        while (cursor.moveToNext()) {
            val bid = cursor.getInt(cursor.getColumnIndexOrThrow(BOOK_COLUMN_ID))
            val btitle = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_TITLE))
            val bauthor = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_AUTHOR))
            val btypeString = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_TYPE))
            val btype = BookType.valueOf(btypeString)
            val bstatusString = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_STATUS))
            val bstatus = BookStatus.valueOf(bstatusString)

            val book = Book(bid, btitle, bauthor, btype, bstatus)
            book_list.add(book)
        }
        cursor.close()
        db.close()
        return book_list
    }

    fun issuebook(bid: Int, student_id: Int): Int {
        val db_read = this.readableDatabase
        val db_write = this.writableDatabase
        val selection = "$BOOK_COLUMN_ID =?"
        val selectionArgs = arrayOf(bid.toString())
        val book_cursor = db_read.query(false, BOOK_TABLE_NAME, null, selection, selectionArgs, null, null, null, null)
        if (book_cursor.moveToFirst()) {
            val bstatus = book_cursor.getString(book_cursor.getColumnIndexOrThrow(BOOK_COLUMN_STATUS))
            val bname = book_cursor.getString(book_cursor.getColumnIndexOrThrow(BOOK_COLUMN_TITLE))
            if (bstatus == "Available") {
                val selection = "$STUDENT_COLUMN_ID =?"
                val selectionArgs = arrayOf(student_id.toString())
                val student_cursor =
                    db_read.query(false, STUDENT_TABLE_NAME, null, selection, selectionArgs, null, null, null, null)

//            val assign_book_to_student = "SELECT * FROM $STUDENT_TABLE_NAME WHERE StudentId = $bid"
//            val student_cursor = db_read.rawQuery(assign_book_to_student.to,null)
                if (student_cursor.moveToFirst()) {
                    var student_name = student_cursor.getInt(
                        student_cursor.getColumnIndexOrThrow(
                            STUDENT_COLUMN_NAME
                        )
                    )
                    var student_book_limit: Int = student_cursor.getInt(
                        student_cursor.getColumnIndexOrThrow(
                            STUDENT_COLUMN_RESERVEDBOOKS_COUNT
                        )
                    )
                    if (student_book_limit > 3) {
                        db_read.close()
                        db_write.close()
                        student_cursor.close()
                        book_cursor.close()
                        return 1
                    } else {
                        student_book_limit++
                        val book_status_reserve = "Reserved"
                        val book_status_values = ContentValues()
                        book_status_values.put(BOOK_COLUMN_STATUS, book_status_reserve)
                        val book_status_selection = "$BOOK_COLUMN_ID = ?"
                        val book_status_selectionArgs = arrayOf(bid.toString())
                        db_write.update(BOOK_TABLE_NAME, book_status_values, book_status_selection, book_status_selectionArgs)

                        val student_book_count_values = ContentValues()
                        student_book_count_values.put(STUDENT_COLUMN_RESERVEDBOOKS_COUNT, student_book_limit)
                        val student_book_count_selection = "$STUDENT_COLUMN_ID = ?"
                        val student_book_count_selectionArgs = arrayOf(student_id.toString())
                        db_write.update(STUDENT_TABLE_NAME, student_book_count_values, student_book_count_selection, student_book_count_selectionArgs)

                        val student_book_name_values = ContentValues()
                        student_book_name_values.put(STUDENT_COLUMN_RESERVEDBOOKS, bname)
                        val student_book_name_selection = "$STUDENT_COLUMN_ID = ?"
                        val student_book_name_selectionArgs = arrayOf(bid.toString())
                        db_write.update(STUDENT_TABLE_NAME, student_book_name_values, student_book_name_selection, student_book_name_selectionArgs)
                        db_read.close()
                        db_write.close()
                        student_cursor.close()
                        book_cursor.close()
                        return 2
                    }
                }
                return 0
            } else {
                db_read.close()
                db_write.close()
                book_cursor.close()
                return 3
            }
            return 0
        } else {
            return 0
        }
    }
    fun addbook(book:Book) : Long{
        val db_write = this.writableDatabase
        val values = ContentValues()
        values.put(BOOK_COLUMN_ID,book.bookId)
        values.put(BOOK_COLUMN_TITLE,book.title)
        values.put(BOOK_COLUMN_AUTHOR,book.author)
        values.put(BOOK_COLUMN_TYPE,book.bookType.toString())
        values.put(BOOK_COLUMN_STATUS,book.status.toString())
        return db_write.insert(BOOK_TABLE_NAME,null,values)

    }
    fun removebook(bookid : String):Boolean{
        val db_write = this.writableDatabase
        val db_read = this.readableDatabase
        val selection = "$BOOK_COLUMN_ID=?"
        val selectionArgs = arrayOf(bookid)
//        val get_book_cursor = db_read.query(false,BOOK_TABLE_NAME,null,selection,selectionArgs,null,null,null,null)
//        if (get_book_cursor.moveToFirst()){
//
//        }
       return if (db_write.delete(BOOK_TABLE_NAME,selection,selectionArgs) > 0) true else false
    }

    fun getbook_byid(bookid: String):Boolean{
        val db_read = this.readableDatabase
        val selection = "$BOOK_COLUMN_ID=?"
        val selectionArgs = arrayOf(bookid)
        val get_book_cursor = db_read.query(false,BOOK_TABLE_NAME,null,selection,selectionArgs,null,null,null,null)
        return if (get_book_cursor.count > 0) true else false
    }
}