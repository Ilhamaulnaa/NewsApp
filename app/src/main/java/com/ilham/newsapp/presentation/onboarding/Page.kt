package com.ilham.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.ilham.newsapp.R

data class Page(
    val title: String,
    val desciption: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Lorem Ipsum",
        desciption = "Lorem ipsum is simply dummy of the printing and typesetting industry",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum",
        desciption = "Lorem ipsum is simply dummy of the printing and typesetting industry",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum",
        desciption = "Lorem ipsum is simply dummy of the printing and typesetting industry",
        image = R.drawable.onboarding3
    )

)