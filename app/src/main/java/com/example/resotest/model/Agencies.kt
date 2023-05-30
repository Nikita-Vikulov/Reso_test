package com.example.resotest.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "agencies_table")
@Parcelize
data class Agencies(

    @SerializedName("IDOKRUG")
    var idDistrict: Int = 0,

    @SerializedName("SSTATUS")
    var status: String = "",

    @SerializedName("NDISTANSE")
    var distance: Double = 0.0,

    @SerializedName("NLONG")
    var longitude: Double = 0.0,

    @SerializedName("SADDRESS")
    var address: String = "",

    @SerializedName("SSHORTNAME")
    var shortName: String = "",

    @SerializedName("SSHORTADDRESS")
    var shortAddress: String = "",

    @SerializedName("SDISTANCE")
    var distanceView: String = "",

    @SerializedName("SPHONE")
    var phone: String = "",

    @SerializedName("SGRAF")
    var workSchedule: String = "",

    @SerializedName("NLAT")
    var latitude: Double = 0.0,

    @SerializedName("REL")
    var rel: String = "",

    @SerializedName("NTIMEZONE")
    var timeZone: String = "",

    @SerializedName("IDTOWN")
    var idTown: Int = 0,

    @SerializedName("ID")
    @PrimaryKey(autoGenerate = false)
    var nodeId: Int = 0,

    @SerializedName("SEMAIL")
    var email: String = "",

    ) : Parcelable