package dev.leonardom.firebasecrud.presentation.book_list

import com.google.android.material.circularreveal.CircularRevealHelper
import dev.leonardom.firebasecrud.model.Book
import dev.leonardom.firebasecrud.repositories.Result

data class BookListState (
    val isLoading: Boolean= false,
    val books: List<Book> =emptyList(),
    val error:String=""
        )