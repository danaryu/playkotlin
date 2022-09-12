package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @Test
    @AfterEach
    fun afterEach() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록이 정상 동작한다")
    fun saveBookTest() {
        //given
        val bookRequest = BookRequest("mipong-adventure")

        //when
        bookService.saveBook(bookRequest)

        //then
        assertEquals(bookRepository.findAll()[0].name, "mipong-adventure")
    }

    @Test
    @DisplayName("책 대출이 정상 동작한다")
    fun loadBookTest() {
        //given
        bookService.saveBook(BookRequest("mipong-adventure"))
        val savedUser = userRepository.save(User("danpong", 29))

        //when
        bookService.loanBook(BookLoanRequest("danpong", "mipong-adventure"))

        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo("mipong-adventure")
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat(results[0].isReturn).isEqualTo(false)
    }

    @Test
    @DisplayName("이미 대출되어 있는 책을 빌리면 IllegalArgumentException이 발생한다")
    fun loanBookExceptionTest() {
        //given
        bookService.saveBook(BookRequest("mipong-adventure"))
        val savedUser = userRepository.save(User("danpong", 29))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "mipong-adventure", false))
//        bookService.loanBook(BookLoanRequest("danpong", "mipong-adventure"))

        //when
        val message = assertThrows<java.lang.IllegalArgumentException>() {
            bookService.loanBook(BookLoanRequest("heepong", "mipong-adventure"))
        }.message

        //then
        assertEquals(message, "진작 대출되어 있는 책입니다")
    }

    @Test
    @DisplayName("책 반납이 정상적으로 동작한다")
    fun returnBook() {
        //given
        val savedUser = userRepository.save(User("danpong", 29))
        val userLoanHistory = UserLoanHistory(savedUser, "mipong-adventure", false)
        userLoanHistoryRepository.save(userLoanHistory)

        //when
        bookService.returnBook(BookReturnRequest(userLoanHistory.user.name, userLoanHistory.bookName))

        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].isReturn).isTrue
    }

}