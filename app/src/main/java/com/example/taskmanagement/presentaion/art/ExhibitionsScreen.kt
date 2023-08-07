package com.example.taskmanagement.presentaion.art

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.model.UIState
import com.example.model.exhibition.ExhibitionData
import com.example.taskmanagement.R
import com.example.taskmanagement.navGraph.Screen
import com.example.taskmanagement.presentaion.component.SnackbarComponent
import com.example.taskmanagement.ui.theme.BrownPink
import com.example.taskmanagement.ui.theme.welcomeScreenBackgroundColor
import com.example.taskmanagement.viewmodel.exhibition.ExhibitionsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ExhibitionsScreen(navController: NavHostController) {
    val viewModel: ExhibitionsViewModel = hiltViewModel()
    // val exhibitions = viewModel.exhibitionsState.collectAsState().value

    val exhibitions = viewModel.getExhibitions.collectAsLazyPagingItems()

    val snackState = remember { SnackbarHostState() }
    val context = rememberCoroutineScope()
    SnackbarComponent(snackState)
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
                .fillMaxWidth()
                .weight(4f),
            columns = GridCells.Fixed(3)
        ) {
            items(
                items = exhibitions,
                key = { it.id.toString() }
            ) { exhibition ->
                if (exhibition != null) {
                    ExhibitionImageItem(exhibition, navController)
                }

            }

            when (val state = exhibitions.loadState.refresh) { //FIRST LOAD
                is LoadState.Error -> {
                    item {
                        Text(text = "error ")
                    }
                }
                is LoadState.Loading -> { // Loading UI
                    item {
                        Column(
                            modifier = Modifier,
                            //.fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Refresh Loading"
                            )

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }
                else -> {
                }
            }

            when (val state = exhibitions.loadState.append) { // Pagination
                is LoadState.Error -> {
                    item {
                        Text("erorr")
                    }
                }
                is LoadState.Loading -> { // Pagination Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Pagination Loading")

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }
                else -> {}
            }

        }
        //  }
//        when (exhibitions) {
//            is UIState.Success -> {
//                LazyVerticalGrid(
//                    modifier = Modifier
//                        .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
//                        .fillMaxWidth()
//                        .weight(4f),
//                    columns = GridCells.Fixed(2)
//                ) {
//                    itemsIndexed(viewModel.exhibitionsList.value) { index, item ->
//                        if (index == (viewModel.exhibitionsList.value.lastIndex) && !isLoadingMore) {
//                            if ((viewModel.getNextPageNumber(exhibitions.responseData?.pagination?.nextUrl.orEmpty())
//                                    .toIntOrNull() ?: 1) >= viewModel.page.value
//                            ) {
//                                viewModel.getExhibitions()
//                            }
//                        }
//                        //ui
//                        ExhibitionImageItem(item, navController)
//                    }
//                }
//                    if (isLoadingMore) {
//                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//                            CircularProgressIndicator(
//                                Modifier
//                                    .padding(12.dp)
//                                    .align(Alignment.Center),
//                                color = BrownPink,
//                                strokeWidth = 4.dp
//                            )
//                        }
//                    }
//            }
//
//            is UIState.Loading -> {
//                Column(
//                    Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Card(
//                        modifier = Modifier.size(100.dp),
//                        shape = RoundedCornerShape(12.dp),
//                        elevation = 6.dp,
//                        backgroundColor = MaterialTheme.colors.welcomeScreenBackgroundColor
//                    ) {
//                        CircularProgressIndicator(Modifier.padding(8.dp), color = BrownPink)
//                    }
//                }
//            }
//            is UIState.Error -> {
//                LaunchedEffect(Unit) {
//                    context.launch {
//                        snackState.showSnackbar(exhibitions.message.toString())
//                    }
//                }
//            }
//        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalCoilApi
@Composable
fun ExhibitionImageItem(item: ExhibitionData, navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = item.imageUrl.toString())
            .apply(block = fun ImageRequest.Builder.() {
                placeholder(R.drawable.empty)
                error(R.drawable.empty)
            }).build()
    )

    Box(
        modifier = Modifier
            .height(200.dp)
            .combinedClickable(
                onClick = {
                    navController.navigate(
                        Screen.ExhibitionDetailScreen.setArgument(
                            exhibitionId = item.id ?: 0
                        )
                    )
                },
                onLongClick = {
                    showDialog = true
                }
            ),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .size(400.dp)
                    .combinedClickable(
                        onClick = {
                            navController.navigate(
                                Screen.ExhibitionDetailScreen.setArgument(
                                    exhibitionId = item.id ?: 0
                                )
                            )
                        },
                        onLongClick = {
                            showDialog = true
                        }
                    ),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painter,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    key: ((item: T) -> Any)? = null,
    itemContent: @Composable LazyGridItemScope.(item: T?) -> Unit
) {
    items(
        count = items.itemCount,
        key = if (key == null) null else { index ->
            val item = items.peek(index)
            if (item == null) {
                PagingPlaceholderKey(index)
            } else {
                key(item)
            }
        }
    ) { index ->
        itemContent(items[index])
    }
}

//@SuppressLint("BanParcelableUsage")
private data class PagingPlaceholderKey(private val index: Int) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(index)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<PagingPlaceholderKey> =
            object : Parcelable.Creator<PagingPlaceholderKey> {
                override fun createFromParcel(parcel: Parcel) =
                    PagingPlaceholderKey(parcel.readInt())

                override fun newArray(size: Int) = arrayOfNulls<PagingPlaceholderKey?>(size)
            }
    }
}