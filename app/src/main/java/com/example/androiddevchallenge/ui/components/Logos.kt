package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@Composable
fun LogoHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    )  {
        Image(
            painter = painterResource(id = R.drawable.ic_strapi),
            contentDescription = "Strapi Logo",
            modifier = modifier.heightIn(max = 124.dp).padding(horizontal = 8.dp),
            contentScale = ContentScale.Fit
        )
        Image(
            painter = painterResource(id = R.drawable.ic_firebase_seeklogo_com),
            contentDescription = "Firebase Logo",
            modifier = modifier.heightIn(max = 124.dp).padding(horizontal = 8.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun LogoFooter() {

}

@Composable
fun StrapiLogo() {
    Image(painter = painterResource(id = R.drawable.ic_strapi), contentDescription = "Strapi Logo")
}