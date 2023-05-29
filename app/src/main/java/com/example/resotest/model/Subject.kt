package com.example.resotest.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "subject_table")
@Parcelize
data class Subject(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @SerializedName("SNAME")
    var name: String = "",

    @SerializedName("NTIMEZONE")
    var timeZone: String = "",

    @SerializedName("NORDER")
    var order: Int = 0,

    @SerializedName("ID")
    var nodeId: String = "",

    @SerializedName("ACTION_MY_REGION")
    var actionMyRegion: String = ""

) : Parcelable