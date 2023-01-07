package com.alexeyyuditsky.holybibleapp.data

import com.alexeyyuditsky.holybibleapp.data.cache.BookDb
import com.alexeyyuditsky.holybibleapp.data.cache.BooksCacheDataSource
import com.alexeyyuditsky.holybibleapp.data.cache.DbWrapper
import com.alexeyyuditsky.holybibleapp.data.network.BookCloud
import com.alexeyyuditsky.holybibleapp.data.network.BooksCloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class BooksRepositorySaveBooksTest : BaseBooksRepositoryTest() {

    @Test
    fun `save books`() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            ToBooksDataMapper.Base(TestToBookMapper())
        )

        val actualCloud: BooksData = repository.fetchBooks()
        val actualCache: BooksData = repository.fetchBooks()
        val expectedCloud: BooksData = BooksData.Success(
            listOf(
                BookData(0, "name0", "ot"),
                BookData(1, "name1", "nt")
            )
        )
        val expectedCache: BooksData = BooksData.Success(
            listOf(
                BookData(0, "name0 db", "ot db"),
                BookData(1, "name1 db", "nt db")
            )
        )

        Assert.assertEquals(expectedCloud, actualCloud)
        Assert.assertEquals(expectedCache, actualCache)
    }

    private class TestBooksCloudDataSource : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0, "name0", "ot"),
                BookCloud(1, "name1", "nt")
            )
        }
    }

    private class TestBooksCacheDataSource : BooksCacheDataSource {
        private val list = ArrayList<BookDb>()
        override fun fetchBooks() = list
        override fun saveBooks(books: List<BookData>) {
            books.map { book ->
                list.add(book.mapToBookDb(object : BookDataToDbMapper {
                    override fun mapToBookDb(id: Int, name: String, testament: String, db: DbWrapper) = BookDb(
                        id = id,
                        name = "$name db",
                        testament = "$testament db"
                    )
                }, object : DbWrapper {
                    override fun createObject(id: Int) = BookDb().apply {
                        this.id = id
                    }
                }))
            }
        }
    }

}