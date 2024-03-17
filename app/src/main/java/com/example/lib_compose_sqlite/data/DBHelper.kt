package com.example.lib_compose_sqlite.data
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.lib_compose_sqlite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class DBHelper(private val context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

//    val DATABASE_NAME1 = "library"

    companion object {
//        val DATABASE_NAME2 = "library"

        private const val DATABASE_NAME = "LibraryDatabse.db"
        private const val DATABASE_VERSION = 1
        private const val ADMIN_TABLE_NAME = "Admin"
        private const val ADMIN_COLUMN_ID = "id"
        private const val ADMIN_COLUMN_NAME = "username"
        private const val ADMIN_COLUMN_PASSWORD = "password"

        private const val STUDENT_TABLE_NAME = "Student"
        private const val STUDENT_COLUMN_ID = "StudentId"
        private const val STUDENT_COLUMN_NAME = "StudentName"
        private const val STUDENT_COLUMN_PASSWORD= "StudentPassword"
        private const val STUDENT_COLUMN_RESERVEDBOOKS_COUNT = "BookCount"

        private const val BOOK_TABLE_NAME = "Books"
        private const val BOOK_COLUMN_ID = "BookId"
        private const val BOOK_COLUMN_TITLE = "BookTitle"
        private const val BOOK_COLUMN_AUTHOR = "BookAuthor"
        private const val BOOK_COLUMN_TYPE = "BookType"
        private const val RESERVED_STUDENT_ID = "ReservedStudentId"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createAdminTableQuery = ("CREATE TABLE $ADMIN_TABLE_NAME (" +
                "$ADMIN_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$ADMIN_COLUMN_NAME TEXT NOT NULL," +
                "$ADMIN_COLUMN_PASSWORD TEXT NOT NULL)")
        val createStudentTableQuery = ("CREATE TABLE $STUDENT_TABLE_NAME (" +
                "$STUDENT_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$STUDENT_COLUMN_NAME TEXT NOT NULL," +
                "$STUDENT_COLUMN_PASSWORD TEXT NOT NULL," +
                "$STUDENT_COLUMN_RESERVEDBOOKS_COUNT INTEGER)")
        val createBookTableQuery = ("CREATE TABLE $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$BOOK_COLUMN_TITLE TEXT NOT NULL," +
                "$BOOK_COLUMN_AUTHOR TEXT NOT NULL," +
                "$BOOK_COLUMN_TYPE TEXT NOT NULL," +
//                "$BOOK_COLUMN_STATUS TEXT NOT NULL," +
                "$RESERVED_STUDENT_ID INTEGER)")
        //Book entries
        val b1 = Book(1, "Dravidian Lang tech", "Ramaswamy", BookType.Journal,null)
        val b2 = Book(1, "Nine Tale Fox", "Wil Tal", BookType.Fiction,null)
        val b3 = Book(1, "Forbes", "Kim", BookType.Magazine, null)
        val b4 = Book(1, "M.S.Dhoni", "Aaradhiya", BookType.Biography, null)
        val b5 = Book(1, "1990's Vision", "Rukmani", BookType.Historic,null)

        val addBooksQuery1 = ("INSERT INTO $BOOK_TABLE_NAME ("+
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$RESERVED_STUDENT_ID ) VALUES (\"${b1.title}\",\"${b1.author}\",\"${b1.bookType}\",\"${b1.reservedStudentId}\")")
        val addBooksQuery2 = ("INSERT INTO $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$RESERVED_STUDENT_ID) VALUES (\"${b2.title}\",\"${b2.author}\",\"${b2.bookType}\",\"${b2.reservedStudentId}\")")
        val addBooksQuery3 = ("INSERT INTO $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$RESERVED_STUDENT_ID) VALUES (\"${b3.title}\",\"${b3.author}\",\"${b3.bookType}\",\"${b3.reservedStudentId}\")")
        val addBooksQuery4 = ("INSERT INTO $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$RESERVED_STUDENT_ID)VALUES (\"${b4.title}\",\"${b4.author}\",\"${b4.bookType}\",\"${b4.reservedStudentId}\")")
        val addBooksQuery5 = ("INSERT INTO $BOOK_TABLE_NAME (" +
                "$BOOK_COLUMN_TITLE," +
                "$BOOK_COLUMN_AUTHOR," +
                "$BOOK_COLUMN_TYPE," +
                "$RESERVED_STUDENT_ID) VALUES (\"${b5.title}\",\"${b5.author}\",\"${b5.bookType}\",\"${b5.reservedStudentId}\")")

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

    fun addAdmin(admin: Person): Long {
        val values = ContentValues().apply {
            put(ADMIN_COLUMN_NAME, admin.name)
            put(ADMIN_COLUMN_PASSWORD,admin.password)
        }
        val db = writableDatabase
        return db.insert(ADMIN_TABLE_NAME, null, values)
    }

    fun loginAdmin(admin: Admin): Boolean {
        val db = this.readableDatabase
        val selection = "$ADMIN_COLUMN_ID =?  AND $ADMIN_COLUMN_PASSWORD =?"
        val selectionArgs = arrayOf(admin.adminId.toString(), admin.adminPassword)
        val cursor = db.query(false, ADMIN_TABLE_NAME, null, selection, selectionArgs, null, null, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    fun addStudent(student: Person): Long {
        val values = ContentValues().apply {
            put(STUDENT_COLUMN_NAME, student.name)
            put(STUDENT_COLUMN_PASSWORD, student.password)
            put(STUDENT_COLUMN_RESERVEDBOOKS_COUNT, 0)
        }
        val db = writableDatabase
        return db.insert(STUDENT_TABLE_NAME, null, values)
    }

    fun loginStudent(student: Student): Int {
        val db = this.readableDatabase
        val selection = "$STUDENT_COLUMN_ID =? AND $STUDENT_COLUMN_PASSWORD =?"
        val selectionArg =
            arrayOf(student.studentId.toString(), student.studentPassword)
        val cursor = db.query(false, STUDENT_TABLE_NAME, null, selection, selectionArg, null, null, null, null, null)
        if (cursor.count > 0) {
            val studentId = student.studentId
            cursor.close()
            return studentId
        }
        return 0
    }

    suspend fun getBooks(): List<Book> = withContext(Dispatchers.IO) {
        try {
            val db = readableDatabase
            val bookList = mutableListOf<Book>()
            val readBook = "SELECT * FROM $BOOK_TABLE_NAME"
            val cursor = db.rawQuery(readBook, null)
            while (cursor.moveToNext()) {
                val bid = cursor.getInt(cursor.getColumnIndexOrThrow(BOOK_COLUMN_ID))
                val bookTitle = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_TITLE))
                val bookAuthor = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_AUTHOR))
                val bookTypeString = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_COLUMN_TYPE))
                val bookType = BookType.valueOf(bookTypeString)
                val reservedStudentId = cursor.getInt(cursor.getColumnIndexOrThrow(RESERVED_STUDENT_ID))

                val book = Book(bid, bookTitle, bookAuthor, bookType, reservedStudentId)
                bookList.add(book)
            }
            cursor.close()
            db.close()
            return@withContext bookList
        } catch (e: SQLiteConstraintException) {
            return@withContext emptyList<Book>()
        }
    }

    fun issueBook(bid: Int, student_id: Int): BookIssueStatus {
        try {
            val dbRead = this.readableDatabase
            val dbWrite = this.writableDatabase
            val selection = "$BOOK_COLUMN_ID =?"
            val selectionArgs = arrayOf(bid.toString())
            val bookCursor =
                dbRead.query(false, BOOK_TABLE_NAME, null, selection, selectionArgs, null, null, null, null)
            if (bookCursor.moveToFirst()) {
                val reserveStudentId = bookCursor.getInt(bookCursor.getColumnIndexOrThrow(RESERVED_STUDENT_ID))
                if (reserveStudentId == 0) {
                    val studentSelection = "$STUDENT_COLUMN_ID =?"
                    val studentSelectionArgs = arrayOf(student_id.toString())
                    val studentCursor =
                        dbRead.query(
                            false,
                            STUDENT_TABLE_NAME,
                            null,
                            studentSelection,
                            studentSelectionArgs,
                            null,
                            null,
                            null,
                            null
                        )
                    if (studentCursor.moveToFirst()) {
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
                            return BookIssueStatus.StudentLimitExceeded
                        } else {
                            studentBookLimit++
                            val bookStatusValues = ContentValues().apply {
                                put(RESERVED_STUDENT_ID, student_id)
                            }
                            val bookStatusSelection = "$BOOK_COLUMN_ID = ?"
                            val bookStatusSelectionArgs = arrayOf(bid.toString())
                            dbWrite.update(
                                BOOK_TABLE_NAME,
                                bookStatusValues,
                                bookStatusSelection,
                                bookStatusSelectionArgs
                            )

                            val studentBookCountValues = ContentValues().apply {
                                put(STUDENT_COLUMN_RESERVEDBOOKS_COUNT, studentBookLimit)
                            }
                            val studentBookCountSelection = "$STUDENT_COLUMN_ID = ?"
                            val studentBookCountSelectionArgs = arrayOf(student_id.toString())
                             dbWrite.update(
                                STUDENT_TABLE_NAME,
                                studentBookCountValues,
                                studentBookCountSelection,
                                studentBookCountSelectionArgs
                            )
                            dbRead.close()
                            dbWrite.close()
                            studentCursor.close()
                            bookCursor.close()
                            return BookIssueStatus.Successful
                        }
                    }
                    else {
                        studentCursor.close()
                        dbRead.close()
                        dbWrite.close()
                        bookCursor.close()
                        return BookIssueStatus.InvalidInput
                    }
                }
                else{
                    dbRead.close()
                    dbWrite.close()
                    bookCursor.close()
                    return BookIssueStatus.AlreadyReserved
                }
            } else {
                dbRead.close()
                dbWrite.close()
                bookCursor.close()
                return BookIssueStatus.InvalidInput
            }
        }
        catch (e: SQLiteConstraintException) {
            return BookIssueStatus.Failed
        }
    }

    fun addBook(booksTitle:String, booksAuthor:String,bookType:BookType): Long {
        try {
            val dbWrite = this.writableDatabase
            val values = ContentValues()
            values.put(BOOK_COLUMN_TITLE,booksTitle)
            values.put(BOOK_COLUMN_AUTHOR,booksAuthor)
            values.put(BOOK_COLUMN_TYPE, bookType.name)
            values.put(RESERVED_STUDENT_ID, 0)
            return dbWrite.insert(BOOK_TABLE_NAME, null, values)
        } catch (e: SQLiteConstraintException) {
            return 0L
        }

    }

    fun removeBook(bookid: Int): Boolean {
        try {
            val dbWrite = this.writableDatabase
            val selection = "$BOOK_COLUMN_ID=?"
            val selectionArgs = arrayOf(bookid.toString())
            return if (dbWrite.delete(BOOK_TABLE_NAME, selection, selectionArgs) > 0) true else false
        } catch (e: SQLiteConstraintException) {
            return false
        }
    }

    fun getStudentMyBook(studentid: Int): Flow<List<Book>> = flow {
        try {
            val dbRead = readableDatabase
            val getBooksCursor = dbRead.rawQuery("SELECT * FROM $BOOK_TABLE_NAME", null)
            while (getBooksCursor.moveToNext()) {
                val reserveStudentId = getBooksCursor.getInt(
                    getBooksCursor.getColumnIndexOrThrow(
                        RESERVED_STUDENT_ID
                    )
                )
                val bookId = getBooksCursor.getInt(
                    getBooksCursor.getColumnIndexOrThrow(
                        BOOK_COLUMN_ID
                    )
                )
                if (reserveStudentId == studentid) {
                    val books: MutableList<Book> = mutableListOf()
                    val bookTitle = getBooksCursor.getString(getBooksCursor.getColumnIndexOrThrow(BOOK_COLUMN_TITLE))
                    val bookAuthor =
                        getBooksCursor.getString(getBooksCursor.getColumnIndexOrThrow(BOOK_COLUMN_AUTHOR))
                    val bookType = getBooksCursor.getString(getBooksCursor.getColumnIndexOrThrow(BOOK_COLUMN_TYPE))
                    val book = Book(
                        bookId,
                        bookTitle,
                        bookAuthor,
                        BookType.valueOf(bookType),
                        studentid
                    )
                    books.add(book)
                    emit(books)
                }
            }
            getBooksCursor.close()
        }catch (e: SQLiteConstraintException) {
            Toast.makeText(context, "Failed to retrieve the book. Please try again later.", Toast.LENGTH_LONG).show()
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

    fun returnBook(bid: Int, studentid: Int): BookReturnStatus {
        try {
            val dbRead = this.readableDatabase
            val dbWrite = this.writableDatabase
            val selection = "$BOOK_COLUMN_ID = ?"
            val selectionArguments = arrayOf(bid.toString())
            val getReturnBookCursor =
                dbRead.query(false, BOOK_TABLE_NAME, null, selection, selectionArguments, null, null, null, null)
            if (getReturnBookCursor.moveToFirst()) {
                val reserveStudentId = getReturnBookCursor.getInt(
                    getReturnBookCursor.getColumnIndexOrThrow(
                        RESERVED_STUDENT_ID
                    )
                )
                if (reserveStudentId == studentid) {
                    val studentSelection = "$STUDENT_COLUMN_ID = ?"
                    val studentSelectionArg = arrayOf(studentid.toString())
                    val getStudent = dbRead.query(
                        false,
                        STUDENT_TABLE_NAME,
                        null,
                        studentSelection,
                        studentSelectionArg,
                        null,
                        null,
                        null,
                        null
                    )
                    if (getStudent.moveToFirst()) {
                        var studentBookCount = getStudent.getInt(
                            getStudent.getColumnIndexOrThrow(
                                STUDENT_COLUMN_RESERVEDBOOKS_COUNT
                            )
                        )
//                        if (studentBookCount > 0) {
                        val values = ContentValues().apply {
                            put(RESERVED_STUDENT_ID, 0)
                        }
                        val updateBookCursor =
                            dbWrite.update(BOOK_TABLE_NAME, values, selection, selectionArguments)
                        val studentValuesBookCount = ContentValues().apply {
                            put(STUDENT_COLUMN_RESERVEDBOOKS_COUNT, --studentBookCount)
                        }
                        val updateStudentBooksCount = dbWrite.update(
                            STUDENT_TABLE_NAME,
                            studentValuesBookCount,
                            studentSelection,
                            studentSelectionArg
                        )
                        if (updateStudentBooksCount > 0 && updateBookCursor > 0) {
                            getReturnBookCursor.close()
                            getStudent.close()
                            dbWrite.close()
                            dbRead.close()
                            return BookReturnStatus.Successful
                        }
                        else {
                            getReturnBookCursor.close()
                            getStudent.close()
                            dbWrite.close()
                            dbRead.close()
                            return BookReturnStatus.Failed
                         }
                    }
                }
                else{
                    getReturnBookCursor.close()
                    dbWrite.close()
                    dbRead.close()
                    return BookReturnStatus.NoBookReserved
                }
            }
            else{
                getReturnBookCursor.close()
                dbWrite.close()
                dbRead.close()
                return BookReturnStatus.WrongBookId
            }
        }catch (e: SQLiteConstraintException) {
            return BookReturnStatus.Failed
        }
        return BookReturnStatus.Failed
    }
}
