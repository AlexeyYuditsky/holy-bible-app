package com.alexeyyuditsky.holybibleapp.core

interface Matcher<T> {
    fun matches(arg: T): Boolean
}