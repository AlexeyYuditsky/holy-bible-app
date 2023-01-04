package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.data.BookData
import com.alexeyyuditsky.holybibleapp.data.BookDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.data.BooksDataToDomainMapper
import com.alexeyyuditsky.holybibleapp.data.TestamentTemp
import org.junit.Assert
import org.junit.Test

/**
 * Test for [BooksDataToDomainMapper]
 */
class BaseBooksDataToDomainMapperTest {

    @Test
    fun map() {
        val baseBookDataToDomainMapper = object : BookDataToDomainMapper {
            override fun map(id: Int, name: String): BookDomain {
                return BookDomain.Base(id, name)
            }
        }
        val baseBooksDataToDomainMapper = object : BooksDataToDomainMapper {
            override fun map(books: List<BookData>): BooksDomain {
                val data = ArrayList<BookDomain>()
                val temp = object : TestamentTemp {
                    private var temp = ""
                    override fun save(testament: String) {
                        temp = testament
                    }

                    override fun matches(testament: String): Boolean = temp == testament
                    override fun isEmpty(): Boolean = temp.isEmpty()
                }
                books.forEach { bookData ->
                    if (!bookData.compare(temp)) {
                        if (temp.isEmpty()) {
                            val bookDomainTestament = BookDomain.Testament(TestamentType.OLD)
                            data.add(bookDomainTestament)
                        } else {
                            val bookDomainTestament = BookDomain.Testament(TestamentType.NEW)
                            data.add(bookDomainTestament)
                        }
                        bookData.saveTestament(temp)
                    }
                    val bookDomain = bookData.map(baseBookDataToDomainMapper)
                    data.add(bookDomain)
                }
                return BooksDomain.Success(data)
            }

            override fun map(exception: Exception): BooksDomain {
                throw IllegalStateException()
            }
        }
        val booksData = listOf(
            BookData(1, "Genesis", "OT"),
            BookData(66, "Revelation", "NT")
        )

        val actual: BooksDomain = baseBooksDataToDomainMapper.map(booksData)
        val expected: BooksDomain = BooksDomain.Success(
            listOf(
                BookDomain.Testament(TestamentType.OLD),
                BookDomain.Base(1, "Genesis"),
                BookDomain.Testament(TestamentType.NEW),
                BookDomain.Base(66, "Revelation"),
            )
        )

        Assert.assertEquals(expected, actual)
    }

}