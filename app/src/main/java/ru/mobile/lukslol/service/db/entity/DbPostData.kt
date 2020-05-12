package ru.mobile.lukslol.service.db.entity

open class DbPostData {
    @Transient
    open val type: String = ""

    data class Custom(
        override val type: String = TYPE_NAME,
        val content: String
    ) : DbPostData() {
        companion object { const val TYPE_NAME = "CUSTOM" }
    }

    data class Greeting(
        override val type: String = TYPE_NAME,
        val message: String
    ) : DbPostData() {
        companion object { const val TYPE_NAME = "GREETING" }
    }

    data class Unknown(
        override val type: String = TYPE_NAME
    ) : DbPostData() {
        companion object { const val TYPE_NAME = "UNKNOWN" }
    }
}