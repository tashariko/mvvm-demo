package com.tasha.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ppv")
data class PPVTable(
    val people_id: Long? = null,
    val vehicle_id: Long? = null,
    val planet_id: Long? = null,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
