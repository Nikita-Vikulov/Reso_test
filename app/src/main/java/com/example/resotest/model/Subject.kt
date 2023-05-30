package com.example.resotest.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "subject_table")
@Parcelize
data class Subject(

    @SerializedName("SNAME")
    val name: String = "",

    @SerializedName("NTIMEZONE")
    val timeZone: String = "",

    @SerializedName("NORDER")
    val order: Int = 0,

    @SerializedName("ID")
    @PrimaryKey(autoGenerate = false)
    val nodeId: String = "",

    @SerializedName("ACTION_MY_REGION")
    val actionMyRegion: String = ""

) : Parcelable