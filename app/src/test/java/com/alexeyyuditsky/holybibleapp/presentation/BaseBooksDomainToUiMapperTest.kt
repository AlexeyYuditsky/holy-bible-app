package com.alexeyyuditsky.holybibleapp.presentation

import com.alexeyyuditsky.holybibleapp.R
import com.alexeyyuditsky.holybibleapp.domain.BookDomainToUiMapper
import com.alexeyyuditsky.holybibleapp.domain.ErrorType
import org.junit.Assert
import org.junit.Test

/**
 * Test for [BaseBooksDomainToUiMapper]
 */
class BaseBooksDomainToUiMapperTest {

    private val baseBookDomainToUiMapper = object : BookDomainToUiMapper {
        override fun map(id: Int, name: String) = BookUi.Base(id, name)
    }

    private val resourceProvider = object : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection_message -> "no connection"
            R.string.service_unavailable_message -> "service unavailable"
            else -> "something went wrong"
        }
    }

    @Test
    fun `fun map which convert BooksDomain(Fail) to BooksUi(Fail)`() {
        val mapper = BaseBooksDomainToUiMapper(resourceProvider, baseBookDomainToUiMapper)

        val actual1: BooksUi = mapper.map(ErrorType.NO_CONNECTION)
        val actual2: BooksUi = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        val actual3: BooksUi = mapper.map(ErrorType.GENERIC_ERROR)
        val expected1: BooksUi = BooksUi.Base(listOf(BookUi.Fail("no connection")))
        val expected2: BooksUi = BooksUi.Base(listOf(BookUi.Fail("service unavailable")))
        val expected3: BooksUi = BooksUi.Base(listOf(BookUi.Fail("something went wrong")))

        Assert.assertEquals(expected1, actual1)
        Assert.assertEquals(expected2, actual2)
        Assert.assertEquals(expected3, actual3)
    }

}