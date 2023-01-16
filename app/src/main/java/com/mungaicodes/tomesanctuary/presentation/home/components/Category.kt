package com.mungaicodes.tomesanctuary.presentation.home.components

import androidx.annotation.DrawableRes
import com.mungaicodes.tomesanctuary.R

data class Category(
    val label: String,
    val value: String = "...",
    @DrawableRes
    val image: Int
)

val categories = listOf(
    Category(
        label = "Architecture",
        image = R.drawable.architecture,
        value = "ARCHITECTURE"
    ),
    Category(
        label = "Art",
        image = R.drawable.art,
        value = "ART"
    ),
    Category(
        label = "Auto/Bio-graphy",
        image = R.drawable.bio,
        value = "BIOGRAPHY & AUTOBIOGRAPHY"
    ),
    Category(
        label = "Business & Economics",
        image = R.drawable.economics,
        value = "BUSINESS & ECONOMICS"
    ),
    Category(
        label = "Comics & Graphic Novels",
        image = R.drawable.comics,
        value = "COMICS & GRAPHIC NOVELS"
    ),
    Category(
        label = "Computers",
        image = R.drawable.computers,
        value = "COMPUTERS"
    ),
    Category(
        label = "Cooking",
        image = R.drawable.food,
        value = "COOKING"
    ),
    Category(
        label = "Design",
        image = R.drawable.design,
        value = "DESIGN"
    ),
    Category(
        label = "Drama",
        image = R.drawable.drama,
        value = "DRAMA"
    ),
    Category(
        label = "Education",
        image = R.drawable.education,
        value = "EDUCATION"
    ),
    Category(
        label = "Fiction",
        image = R.drawable.fiction,
        value = "FICTION"
    ),
    Category(
        label = "History",
        image = R.drawable.history,
        value = "HISTORY"
    ),
    Category(
        label = "Humor",
        image = R.drawable.humor,
        value = "HUMOR"
    ),
    Category(
        label = "LAW",
        image = R.drawable.law,
        value = "LAW"
    ),
    Category(
        label = "True Crime",
        image = R.drawable.crime,
        value = "TRUE CRIME"
    ),
    Category(
        label = "Travel",
        image = R.drawable.travel,
        value = "TRAVEL"
    ),
    Category(
        label = "Self-Help",
        image = R.drawable.help,
        value = "SELF-HELP"
    ),
    Category(
        label = "Science",
        image = R.drawable.science,
        value = "SCIENCE"
    ),
    Category(
        label = "Religion",
        image = R.drawable.religion,
        value = "RELIGION"
    ),
    Category(
        label = "Psychology",
        image = R.drawable.psychology,
        value = "PSYCHOLOGY"
    ),
    Category(
        label = "Poetry",
        image = R.drawable.poetry,
        value = "POETRY"
    ),
    Category(
        label = "Philosophy",
        image = R.drawable.philosophy,
        value = "PHILOSOPHY"
    ),
    Category(
        label = "Pets",
        image = R.drawable.pets,
        value = "PETS"
    ),
    Category(
        label = "Nature",
        image = R.drawable.nature,
        value = "NATURE"
    ),
    Category(
        label = "Music",
        image = R.drawable.music,
        value = "MUSIC"
    ),
    Category(
        label = "Medical",
        image = R.drawable.health,
        value = "MEDICAL"
    ),
    Category(
        label = "Maths ",
        image = R.drawable.maths,
        value = "MATHEMATICS"
    ),
    Category(
        label = "Literary Criticism",
        image = R.drawable.criticism,
        value = "LITERARY CRITICISM"
    ),
)