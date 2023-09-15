package com.tasha.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "people")
data class People(
    val name: String,
    val height: String? = null,
    val mass: String? = null,

    @SerializedName("birth_year")
    @ColumnInfo("birth_year")
    val birthYear: String? = null,
    val gender: String? = null,
    val homeworld:String? = null,       //will be a planet
    val url:String? = null,
    val starships: ArrayList<String>?= null
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

}