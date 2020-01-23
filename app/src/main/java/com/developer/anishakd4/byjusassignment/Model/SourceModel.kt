package com.developer.anishakd4.byjusassignment.Model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceModel(
    @ColumnInfo(name = "name")
    val name: String): Parcelable