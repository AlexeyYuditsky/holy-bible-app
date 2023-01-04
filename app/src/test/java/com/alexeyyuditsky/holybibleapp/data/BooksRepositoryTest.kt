package com.alexeyyuditsky.holybibleapp.data

/*import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheDataSource
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheMapper
import com.alexeyyuditsky.holybibleapp.data.network.BookCloud
import com.alexeyyuditsky.holybibleapp.data.network.BooksCloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException*/

class BooksRepositoryTest : BaseBooksRepositoryTest() {
/*
    private val unknownHostException = UnknownHostException()

    @Test
    fun `no connection and no cache`() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual: BooksData = repository.fetchBooks()
        val expected: BooksData = BooksData.Fail(unknownHostException)

        assertEquals(actual, expected)
    }

    @Test
    fun `have a connection and no cache`() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual: BooksData = repository.fetchBooks()
        val expected: BooksData = BooksData.Success(
            List(3) { i -> Book(i, "name $i") }
        )

        assertEquals(actual, expected)
    }

    @Test
    fun `no connection and have a cache`() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual: BooksData = repository.fetchBooks()
        val expected: BooksData = BooksData.Success(
            List(3) { i -> Book(id = i + 10, "name${i + 10}") }
        )

        assertEquals(actual, expected)
    }

    @Test
    fun `have a connection and have a cache`() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual: BooksData = repository.fetchBooks()
        val expected: BooksData = BooksData.Success(
            List(3) { i -> Book(id = i + 10, "name${i + 10}") }
        )

        assertEquals(actual, expected)
    }

    private inner class TestBooksCloudDataSource(
        private val returnSuccess: Boolean
    ) : BooksCloudDataSource {

        override suspend fun fetchBooks(): List<BookCloud> {
            if (returnSuccess) {
                return List(3) { i -> BookCloud(i, "name $i") }
            } else {
                throw unknownHostException
            }
        }

    }

    private class TestBooksCacheDataSource(
        private val returnSuccess: Boolean,
    ) : BooksCacheDataSource {

        override fun fetchBookDb(): List<BookDb> {
            return if (returnSuccess) {
                List(3) { i -> BookDb().apply { id = i + 10; name = "name${i + 10}" } }
            } else {
                emptyList()
            }
        }

        override fun saveBooks(books: List<Book>) {
            // not used here
        }

    }*/

}