package com.mungaicodes.tomesanctuary.domain.model

import com.mungaicodes.tomesanctuary.data.remote.dto.AccessInfo
import com.mungaicodes.tomesanctuary.data.remote.dto.SaleInfo
import com.mungaicodes.tomesanctuary.data.remote.dto.SearchInfo
import com.mungaicodes.tomesanctuary.data.remote.dto.VolumeInfo

data class Book(
    val accessInfo: AccessInfo,
    val etag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfo,
    val searchInfo: SearchInfo?,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)
