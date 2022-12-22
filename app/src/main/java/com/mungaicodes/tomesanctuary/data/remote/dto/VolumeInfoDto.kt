package com.mungaicodes.tomesanctuary.data.remote.dto

data class VolumeInfoDto(
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val industryIdentifierDtos: List<IndustryIdentifierDto>,
    val readingModes: ReadingModesDto,
    val pageCount: Int,
    val printType: String,
    val categories: List<String>,
    val averageRating: Double,
    val ratingsCount: Int,
    val maturityRating: String,
    val allowAnonLogging: Boolean,
    val contentVersion: String,
    val panelizationSummary: PanelizationSummaryDto,
    val imageLinks: ImageLinksDto,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    val canonicalVolumeLink: String
)