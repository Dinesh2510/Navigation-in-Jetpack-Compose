package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.includes.ItemModel
import com.example.myapplication.includes.DemoDataProvider.itemList
import com.example.myapplication.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AppNavigation()
                    }
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavController) {
    val primaryGrey = Color(0xFFD3D5D0)
    Column(
        modifier = Modifier
            .background(color = primaryGrey)
            .fillMaxWidth()
    ) {
        TopAppBar(title = {
            Text(
                "Navigation Demo",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(200.dp, 28.dp)
            )
        }, actions = {
            IconButton(onClick = {
                navController.navigate("search")
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    modifier = Modifier.padding(end = 8.dp),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

        }, modifier = Modifier
            .fillMaxWidth()
        )
        LazyColumn {
            items(itemList) { food ->
                MainPageContent(foodItem = food, onVideoClick = {
                    navController.navigate("detailsPage")
                })
            }
        }
    }
}

@Composable
fun MainPageContent(foodItem: ItemModel, onVideoClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onVideoClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = foodItem.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = foodItem.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = foodItem.subtitle,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

/**=========================MainPage END====================*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPage(navController: NavController) {


    Column {
        TopAppBar(
            title = {
                Text(
                    "Details Page",
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .size(200.dp, 24.dp)
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = {
                    navController.navigate("search")
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(16.dp))
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        DetailsContent()
    }
}

@Composable
fun DetailsContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "our title",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Our Description",
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

/**=========================Details Page END====================*/
@Composable
fun SearchPage(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is Search Page ")
        Button(onClick = {
            navController.popBackStack("mainPage", false)
        }) {
            Text("Go to Main Page")
        }
    }
}
/**=========================Search Page END====================*/
