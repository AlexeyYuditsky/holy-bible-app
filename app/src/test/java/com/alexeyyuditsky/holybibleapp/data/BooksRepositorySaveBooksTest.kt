package com.alexeyyuditsky.holybibleapp.data

/*import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheDataSource
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheMapper
import com.alexeyyuditsky.holybibleapp.data.network.BookCloud
import com.alexeyyuditsky.holybibleapp.data.network.BooksCloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test*/

class BooksRepositorySaveBooksTest : BaseBooksRepositoryTest() {
/*
    @Test
    fun `save books`() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actualCloud: BooksData = repository.fetchBooks()
        val expectedCloud: BooksData = BooksData.Success(
            listOf(
                Book(0, "name 0"),
                Book(1, "name 1"),
                Book(2, "name 2")
            )
        )
        val actualCache: BooksData = repository.fetchBooks()
        val expectedCache: BooksData = BooksData.Success(
            listOf(
                Book(0, "name 0 db"),
                Book(1, "name 1 db"),
                Book(2, "name 2 db")
            )
        )

        Assert.assertEquals(expectedCloud, actualCloud)
        Assert.assertEquals(expectedCache, actualCache)
    }

    private class TestBooksCloudDataSource : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return List(3) { i -> BookCloud(i, "name $i") }
        }
    }

    private class TestBooksCacheDataSource : BooksCacheDataSource {

        private val list = ArrayList<BookDb>()

        override fun fetchBookDb(): List<BookDb> {
            return list
        }

        override fun saveBooks(books: List<Book>) {
            books.map { book ->
                list.add(
                    BookDb().apply {
                        this.id = book.id
                        this.name = "${book.name} db"
                    }
                )
            }
        }

    }*/

}