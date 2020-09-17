package com.development.test.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "groups", indices = [(Index(value = ["id"], unique = true))])
data class Group(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "contacts_count") var contactsCount: Int = 0) : Serializable {

    fun addContact() = contactsCount++

    fun getBubbleText() = title

}
