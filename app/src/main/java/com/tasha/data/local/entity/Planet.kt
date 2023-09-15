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
    val url:String? = null,
    val residents: ArrayList<String>? = null
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

}