package com.tasha.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "planet")
data class Planet (
    val name: String,
    val gravity: String? = null,
    val terrain: String? = null,
    val climate: String? = null,
    val url:String,
    val residents: ArrayList<String>? = null
) : ParentEntity(),Parcelable {
    @PrimaryKey
    var id: Long = System.currentTimeMillis()
        set(value) {
            field = url.split("/")[5].toLong()

        }
        get() {
            return url.split("/")[5].toLong()
        }
}