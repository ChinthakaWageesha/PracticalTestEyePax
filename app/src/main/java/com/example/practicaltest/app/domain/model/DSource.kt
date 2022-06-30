package com.example.practicaltest.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DSource (
    var sourceId: String? = null,
    var sourceName: String? = null
): Parcelable