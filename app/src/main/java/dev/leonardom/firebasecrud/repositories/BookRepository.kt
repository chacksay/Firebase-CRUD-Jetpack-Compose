package dev.leonardom.firebasecrud.repositories
import androidx.constraintlayout.helper.widget.Flow
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import dev.leonardom.firebasecrud.model.Book
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

import kotlinx.coroutines.tasks.await


@Singleton
class BookRepository
@Inject
constructor (
    private val bookList:CollectionReference
) {
    fun addNewBook(book: Book) {

        try {
            bookList.document(book.id).set(book)
        } catch (e:Exception){
e.printStackTrace()
        }
    }
    fun getBookList() : kotlinx.coroutines.flow.Flow<Result<List<Book>>> = flow {

        try {
            emit(Result.Loading<List<Book>>())
            val bookList = bookList.get().await().map{document ->
                document.toObject(Book::class.java)

            }
            emit(Result.Success<List<Book>>(data= bookList))
        }catch(e:Exception){
            emit(Result.Error<List<Book>>(message = e.localizedMessage?:"Error desconocido"))
        }
    }

}