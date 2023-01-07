package com.alexeyyuditsky.holybibleapp.domain

import com.alexeyyuditsky.holybibleapp.data.BookData
import com.alexeyyuditsky.holybibleapp.data.BookDataToDomainMapper
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

/**
 * Test for [BaseBooksDataToDomainMapper]
 */
class BaseBooksDataToDomainMapperTest {

    private val baseBookDataToDomainMapper = object : BookDataToDomainMapper {
        override fun map(id: Int, name: String) = BookDomain.Base(id, name)
    }

    @Test
    fun `fun map which convert BooksData(Success) to BooksDomain(Success)`() {
        val mapper = BaseBooksDataToDomainMapper(baseBookDataToDomainMapper)
        val booksData = listOf(
            BookData(1, "Genesis", "OT"),
            BookData(66, "Revelation", "NT")
        )

        val actual: BooksDomain = mapper.map(booksData)
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

    @Test
    fun `fun map which convert BooksData(Fail) to BooksDomain(Fail)`() {
        val mapper = BaseBooksDataToDomainMapper(baseBookDataToDomainMapper)

        val actual1: BooksDomain = mapper.map(UnknownHostException())
        val actual2: BooksDomain = mapper.map(HttpException(Response.success(null)))
        val actual3: BooksDomain = mapper.map(IllegalStateException())
        val expected1: BooksDomain = BooksDomain.Fail(ErrorType.NO_CONNECTION)
        val expected2: BooksDomain = BooksDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        val expected3: BooksDomain = BooksDomain.Fail(ErrorType.GENERIC_ERROR)

        Assert.assertEquals(expected1, actual1)
        Assert.assertEquals(expected2, actual2)
        Assert.assertEquals(expected3, actual3)
    }

}