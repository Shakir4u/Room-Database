package com.example.employee.roomDB

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlin.properties.Delegates

@Entity(tableName = "Employee")
@Parcelize
data class Employee(@PrimaryKey(autoGenerate = true) val id:Int , var name: String,
                    var salary:String,
                    var designation:String) : Parcelable