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
                "$ADMIN_COLUMN_ID INTEGER PRIMARY KEY  ," +
                "$ADMIN_COLUMN_NAME TEXT," +
                "$ADMIN_COLUMN_PASSWORD TEXT)")
        val createStudentTableQuery = ("CREATE TABLE $STUDENT_TABLE_NAME (" +
                "$STUDENT_COLUMN_ID INTEGER PRIMARY KEY ," +
                "$STUDENT_COLUMN_NAME TEXT," +
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

    fun addAdmin(admin: Admin): Long {
        val values = ContentValues().apply {
            put(ADMIN_COLUMN_ID, admin.adminId)
            put(ADMIN_COLUMN_NAME, admin.adminName)
            put(ADMIN_COLUMN_PASSWORD, admin.adminPassword)
        }
        val db = writableDatabase
        return db.insert(ADMIN_TABLE_NAME, null, values)
    }

    fun loginadmin(admin: Admin): Boolean {
        val db = this.readableDatabase
        val selection = "$ADMIN_COLUMN_ID =? AND $ADMIN_COLUMN_NAME =? AND $ADMIN_COLUMN_PASSWORD =?"
        val selectionArgs = arrayOf(admin.adminId.toString(),admin.adminName, admin.adminPassword)
//        val cursor:Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ")
        val cursor = db.query(false, ADMIN_TABLE_NAME, null, selection, selectionArgs, null, null, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    fun addStudent(student: Student, StudentReservedBook: String = "", StudentReservedBookCount: Int = 0): Long {
        val values = ContentValues().apply {
            put(STUDENT_COLUMN_ID, student.studentId)
            put(STUDENT_COLUMN_NAME, student.studentName)
            put(STUDENT_COLUMN_RESERVEDBOOKS, StudentReservedBook)
            put(STUDENT_COLUMN_RESERVEDBOOKS_COUNT, StudentReservedBookCount)
        }
        val db = writableDatabase
        return db.insert(STUDENT_TABLE_NAME, null, values)
    }

    fun loginstudent(student: Student): Int {
        val db = this.readableDatabase
        val selection = "$STUDENT_COLUMN_ID =? AND $STUDENT_COLUMN_NAME =?"
        val selectionArg =
            arrayOf(student.studentId.toString(), student.studentName.toString())
        val cursor = db.query(false, STUDENT_TABLE_NAME, null, selection, selectionArg, null, null, null, null, null)
        if(cursor.count>0) {
            val studentId = student.studentId
            cursor.close()
            return studentId
        }
        return 0
    }

    fun getBooks(): List<Book> {
        val bookList = mutableListOf<Book>()
        val db = this.readableDatabase
        val readBook = "SELECT * FROM $BOOK_TABLE_NAME"
        val cursor = db.rawQuery(readBook, null)
        while (cursor.moveToNext()) {
            val bid = cursor.getInt(cursor.getColumnIndexOrThrow(BOOK_COLUMN_ID))
            val bookTitle = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_TITLE))
            val bookAuthor = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_AUTHOR))
            val bookTypeString = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_TYPE))
            val bookType = BookType.valueOf(bookTypeString)
            val bookStatusString = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_STATUS))
            val bookStatus = BookStatus.valueOf(bookStatusString)

            val book = Book(bid, bookTitle, bookAuthor, bookType, bookStatus)
            bookList.add(book)
        }
        cursor.close()
        db.close()
        return bookList
    }

    fun issuebook(bid: Int, student_id: Int): Int {
        val dbRead = this.readableDatabase
        val dbWrite = this.writableDatabase
        val selection = "$BOOK_COLUMN_ID =?"
        val selectionArgs = arrayOf(bid.toString())
        val bookCursor = dbRead.query(false, BOOK_TABLE_NAME, null, selection, selectionArgs, null, null, null, null)
        if (bookCursor.moveToFirst()) {
            val bookStatus = bookCursor.getString(bookCursor.getColumnIndexOrThrow(BOOK_COLUMN_STATUS))
            if (bookStatus == "Available") {
                val studentSelection = "$STUDENT_COLUMN_ID =?"
                val studentSelectionArgs = arrayOf(student_id.toString())
                val studentCursor =
                    dbRead.query(false, STUDENT_TABLE_NAME, null, studentSelection, studentSelectionArgs, null, null, null, null)

//            val assign_book_to_student = "SELECT * FROM $STUDENT_TABLE_NAME WHERE StudentId = $bid"
//            val studentCursor = dbRead.rawQuery(assign_book_to_student.to,null)
                if (studentCursor.moveToFirst()) {
                    var studentBookId = studentCursor.getString(
                        studentCursor.getColumnIndexOrThrow(
                            STUDENT_COLUMN_RESERVEDBOOKS
                        )
                    )
                    var studentBookLimit = studentCursor.getInt(
                        studentCursor.getColumnIndexOrThrow(
                            STUDENT_COLUMN_RESERVEDBOOKS_COUNT
                        )
                    )
                    if (studentBookLimit > 3) {
                        dbRead.close()
                        dbWrite.close()
                        studentCursor.close()
                        bookCursor.close()
                        return 1
                    } else {
                        studentBookLimit++
//                        studentBookId = studentBookId + "," +bid.toString()
                        studentBookId = studentBookId.plus(",${bid}")
                        val bookStatusReserve = "Reserved"
                        val bookStatusValues = ContentValues()
                        bookStatusValues.put(BOOK_COLUMN_STATUS, bookStatusReserve)
                        val bookStatusSelection = "$BOOK_COLUMN_ID = ?"
                        val bookStatusSelectionArgs = arrayOf(bid.toString())
                        dbWrite.update(BOOK_TABLE_NAME, bookStatusValues, bookStatusSelection, bookStatusSelectionArgs)

                        val studentBookCountValues = ContentValues()
                        studentBookCountValues.put(STUDENT_COLUMN_RESERVEDBOOKS_COUNT, studentBookLimit)
                        val studentBookCountSelection = "$STUDENT_COLUMN_ID = ?"
                        val studentBookCountSelectionArgs = arrayOf(student_id.toString())
                        dbWrite.update(STUDENT_TABLE_NAME, studentBookCountValues, studentBookCountSelection, studentBookCountSelectionArgs)

                        val studentBookNameValues = ContentValues()
                        studentBookNameValues.put(STUDENT_COLUMN_RESERVEDBOOKS, studentBookId)
                        val studentBookNameSelection = "$STUDENT_COLUMN_ID = ?"
                        val studentBookNameSelectionArgs = arrayOf(student_id.toString())
                        dbWrite.update(STUDENT_TABLE_NAME, studentBookNameValues, studentBookNameSelection, studentBookNameSelectionArgs)
                        dbRead.close()
                        dbWrite.close()
                        studentCursor.close()
                        bookCursor.close()
                        return 2
                    }
                }
                return 0
            } else {
                dbRead.close()
                dbWrite.close()
                bookCursor.close()
                return 3
            }
        } else {
            return 0
        }
    }
    fun addbook(book:Book) : Long{
        val dbWrite = this.writableDatabase
        val values = ContentValues()
        values.put(BOOK_COLUMN_ID,book.bookId)
        values.put(BOOK_COLUMN_TITLE,book.title)
        values.put(BOOK_COLUMN_AUTHOR,book.author)
        values.put(BOOK_COLUMN_TYPE,book.bookType.name)
        values.put(BOOK_COLUMN_STATUS,book.status.name)
        return dbWrite.insert(BOOK_TABLE_NAME,null,values)

    }
    fun removebook(bookid : Int):Boolean{
        val dbWrite = this.writableDatabase
        val selection = "$BOOK_COLUMN_ID=?"
        val selectionArgs = arrayOf(bookid.toString())
       return if (dbWrite.delete(BOOK_TABLE_NAME,selection,selectionArgs) > 0) true else false
    }
    fun get_student_my_book(studentid : Int):List<Book>{
        val dbRead = this.readableDatabase
        val selection = "$STUDENT_COLUMN_ID = ?"
        val selectionArgs = arrayOf(studentid.toString())
        val getStudentInfocursor = dbRead.query(false, STUDENT_TABLE_NAME,null,selection,selectionArgs,null,null,null,null)
        if (getStudentInfocursor.moveToFirst()) {
            val studentBookId = getStudentInfocursor.getString(
                getStudentInfocursor.getColumnIndexOrThrow(
                    STUDENT_COLUMN_RESERVEDBOOKS
                )
            )
            val bookIds = studentBookId.split(",")
            val studentBookIdList = mutableListOf<String>(*bookIds.toTypedArray())
            val books:MutableList<Book> = mutableListOf()
            for (bookId in studentBookIdList) {
                val bookSelection = "$BOOK_COLUMN_ID=?"
                val bookSelectionArgs = arrayOf(bookId)
                val getBookCursor = dbRead.query(false, BOOK_TABLE_NAME,null,bookSelection,bookSelectionArgs,null,null,null,null)
                if (getBookCursor.moveToFirst()){
                    val bookTitle = getBookCursor.getString(getBookCursor.getColumnIndexOrThrow(BOOK_COLUMN_TITLE))
                    val bookAuthor = getBookCursor.getString(getBookCursor.getColumnIndexOrThrow(BOOK_COLUMN_AUTHOR))
                    val bookType= getBookCursor.getString(getBookCursor.getColumnIndexOrThrow(BOOK_COLUMN_TYPE))
                    val bookStatus = getBookCursor.getString(getBookCursor.getColumnIndexOrThrow(BOOK_COLUMN_STATUS))
                    val book = Book(bookId.toInt(),bookTitle,bookAuthor,BookType.valueOf(bookType),BookStatus.valueOf(bookStatus))
                    books.add(book)
                }
                getBookCursor.close()
            }

            getStudentInfocursor.close()
            dbRead.close()
            return books
        }
        else{
            getStudentInfocursor.close()
            dbRead.close()
            return emptyList()
        }
    }

    fun return_book(bid:Int,studentid: Int):Int{
        val dbRead = this.readableDatabase
        val dbWrite = this.writableDatabase
        val selection = "$BOOK_COLUMN_ID = ?"
        val selectionArguments = arrayOf(bid.toString())
        val getReturnBookCursor = dbRead.query(false, BOOK_TABLE_NAME,null,selection,selectionArguments,null,null,null,null)
        if (getReturnBookCursor.moveToFirst()){
            val values = ContentValues()
            values.put(BOOK_COLUMN_STATUS,"Available")
            val updateBookCursor = dbWrite.update(BOOK_TABLE_NAME,values,selection,selectionArguments)
            val studentSelection = "$STUDENT_COLUMN_ID = ?"
            val studentSelectionArg = arrayOf(studentid.toString())
            val getStudent = dbRead.query(false, STUDENT_TABLE_NAME,null,studentSelection,studentSelectionArg,null,null,null,null)


//            val studentSelection = "$STUDENT_COLUMN_ID = ?"
//            val studentSelectionArg = arrayOf(studentid.toString())
//            val getStudent = dbRead.query(false, STUDENT_TABLE_NAME,null,studentSelection,studentSelectionArg,null,null,null,null)
            if (getStudent.moveToFirst()){
                val studentReservedBooks = getStudent.getString(getStudent.getColumnIndexOrThrow(
                    STUDENT_COLUMN_RESERVEDBOOKS))
                var studentBookCount = getStudent.getInt(getStudent.getColumnIndexOrThrow(
                    STUDENT_COLUMN_RESERVEDBOOKS_COUNT))
                if (studentBookCount >0) {
                    val updatedStudentReservedBooks = studentReservedBooks.replace(",$bid", "")
                    val studentValuesBook = ContentValues()
                    studentValuesBook.put(STUDENT_COLUMN_RESERVEDBOOKS, updatedStudentReservedBooks)
                    val updateStudentBooks =
                        dbWrite.update(STUDENT_TABLE_NAME, studentValuesBook, studentSelection, studentSelectionArg)
                    val studentValuesBookCount = ContentValues()
                    studentValuesBookCount.put(STUDENT_COLUMN_RESERVEDBOOKS_COUNT, --studentBookCount)
                    val updateStudentBooksCount = dbWrite.update(
                        STUDENT_TABLE_NAME,
                        studentValuesBookCount,
                        studentSelection,
                        studentSelectionArg
                    )
                    if (updateStudentBooksCount > 0 && updateStudentBooks > 0 && updateBookCursor > 0) {
                        getReturnBookCursor.close()
                        getStudent.close()
                        dbWrite.close()
                        dbRead.close()
                        return 1
                    }
                }
                else{
                    getReturnBookCursor.close()
                    getStudent.close()
                    dbWrite.close()
                    dbRead.close()
                    return -2
                }
            }
                else{
                    getReturnBookCursor.close()
                    getStudent.close()
                    dbWrite.close()
                    dbRead.close()
                    return -1
                }
        }

        getReturnBookCursor.close()
        dbWrite.close()
        dbRead.close()
        return 0
    }
}