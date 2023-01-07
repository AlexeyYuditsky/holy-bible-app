package com.alexeyyuditsky.holybibleapp.data

abstract class BaseBooksRepositoryTest {
    protected class TestToBookMapper : ToBookDataMapper {
        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)
    }
}