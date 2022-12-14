package com.alexeyyuditsky.holybibleapp.core

import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class AbstractTest {

    @Test
    fun test_success() {
        val dataObject = TestDataObject.Success("a", "b")
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        val uiObject = domainObject.map(DomainToUiMapper.Base())
        assertTrue(uiObject is UiObject.Success)
    }

    @Test
    fun test_fail() {
        val dataObject = TestDataObject.Fail(IOException())
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        val uiObject = domainObject.map(DomainToUiMapper.Base())
        assertTrue(uiObject is UiObject.Fail)
    }

    private sealed class TestDataObject : Abstract.Object<DomainObject, DataToDomainMapper>() {

        class Success(
            private val textOne: String,
            private val textTwo: String
        ) : TestDataObject() {
            override fun map(mapper: DataToDomainMapper): DomainObject {
                return mapper.map(textOne, textTwo)
            }
        }

        class Fail(
            private val exception: Exception
        ) : TestDataObject() {
            override fun map(mapper: DataToDomainMapper): DomainObject {
                return mapper.map(exception)
            }
        }

    }

    private interface DataToDomainMapper : Abstract.Mapper {

        fun map(textOne: String, textTwo: String): DomainObject

        fun map(e: Exception): DomainObject

        class Base : DataToDomainMapper {
            override fun map(textOne: String, textTwo: String): DomainObject {
                return DomainObject.Success("$textOne $textTwo")
            }

            override fun map(e: Exception): DomainObject {
                return DomainObject.Fail(e)
            }
        }

    }

    private sealed class DomainObject : Abstract.Object<UiObject, DomainToUiMapper>() {

        class Success(
            private val textCombined: String
        ) : DomainObject() {
            override fun map(mapper: DomainToUiMapper): UiObject {
                return mapper.map(textCombined)
            }
        }

        class Fail(
            private val e: Exception
        ) : DomainObject() {
            override fun map(mapper: DomainToUiMapper): UiObject {
                return mapper.map(e)
            }
        }

    }

    private interface DomainToUiMapper : Abstract.Mapper {

        fun map(result: String): UiObject

        fun map(e: Exception): UiObject

        class Base : DomainToUiMapper {
            override fun map(result: String): UiObject {
                return UiObject.Success(result)
            }

            override fun map(e: Exception): UiObject {
                return UiObject.Fail(e)
            }
        }
    }

    private sealed class UiObject : Abstract.Object<Unit, Abstract.Mapper.Empty>() {
        class Success(
            private val result: String
        ) : UiObject() {
            override fun map(mapper: Abstract.Mapper.Empty) {}
        }

        class Fail(
            private val e: Exception
        ) : UiObject() {
            override fun map(mapper: Abstract.Mapper.Empty) {}
        }
    }

}