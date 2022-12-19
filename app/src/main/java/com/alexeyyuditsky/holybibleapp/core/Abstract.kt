package com.alexeyyuditsky.holybibleapp.core


abstract class Abstract {

    abstract class Object<T, M : Mapper> {
        abstract fun map(mapper: M): T
    }

    // FIXME: rename
    interface Mapable<T, M : Mapper> {
        abstract fun map(mapper: M): T
    }

    interface Mapper {
        class Empty : Mapper
    }

}

// 1) Чтобы избавиться от множественного наследования в BookDb, почему не переделать abstract class Object в interface Object?
// 2)
