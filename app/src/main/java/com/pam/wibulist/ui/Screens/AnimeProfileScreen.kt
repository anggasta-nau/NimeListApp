package com.pam.wibulist.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pam.wibulist.R
import com.pam.wibulist.data.AnimeDetail

@Composable
fun AnimeProfileScreen(anime: AnimeDetail)
{
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxWidth()) {
        BoxWithConstraints() {
            Surface() {
                Column( modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState))
                {
                    //TODO:
                    ProfileHeader(anime = anime, containerHeight = this@BoxWithConstraints.maxHeight)
                    AnimeContent(anime = anime, containerHeight = this@BoxWithConstraints.maxHeight)

                }

            }

        }
    }
}

@Composable
private fun ProfileHeader(
    anime: AnimeDetail,
    containerHeight: Dp
)
{
    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth(),
        painter = painterResource(id = anime.AnimeImageId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )

}

@Composable
private fun AnimeContent(anime: AnimeDetail, containerHeight: Dp)
{
    Column() {
        AnimeTitle(anime = anime)
        AnimeProfileProperty(label = stringResource(R.string.season) , value = anime.Season)
        AnimeProfileProperty(label = stringResource(R.string.total_episode), value = anime.totalEpisode)
        AnimeProfileProperty(label = stringResource(R.string.status), value = anime.Status)
        AnimeProfileProperty(label = stringResource(R.string.synopsis), value = anime.AnimeDesc)
        AnimeSynopsis(anime = anime)
    }
}

@Composable
private fun AnimeTitle(anime: AnimeDetail)
{
    Column(modifier = Modifier.padding(16.dp)) {

        Text(text = anime.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h5
        )

    }
}

@Composable
private fun AnimeProfileProperty(label: String, value: String)
{
    Column(modifier = Modifier.fillMaxSize()) {
        Column( modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Divider()
            Text(text = label,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.caption)

            Text(text = value,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.body1)


        }
    }

}

@Composable
private fun AnimeSynopsis(anime: AnimeDetail) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = anime.AnimeDesc,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.body1
        )

    }
}