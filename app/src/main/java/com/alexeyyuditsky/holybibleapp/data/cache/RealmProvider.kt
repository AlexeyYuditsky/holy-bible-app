package com.alexeyyuditsky.holybibleapp.data.cache

import io.realm.Realm

interface RealmProvider {

    fun provide(): Realm

    class Base : RealmProvider {
        override fun provide(): Realm {
            return Realm.getDefaultInstance()
        }
    }

    class Test : RealmProvider {
        override fun provide(): Realm {
            TODO("Not yet implemented")
        }
    }

}