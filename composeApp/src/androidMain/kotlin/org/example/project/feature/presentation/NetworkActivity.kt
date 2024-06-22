package org.example.project.feature.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import commons.Constants.EMPTY_STRING
import entity.Contacts
import feature.presentation.INetworkView
import org.example.project.common.presentation.components.ContactsComponentRender
import org.example.project.feature.viewmodel.ContactsViewModel

class NetworkActivity : ComponentActivity(), INetworkView {
    private val componentViewModel: ContactsViewModel by viewModels()

    private var isContactsLoading = mutableStateOf(false)
    private var errorMsg = mutableStateOf(EMPTY_STRING)

    private val friendListState = ContactsComponentRender()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermission()

        setContent {
            UserScreen(networkViewModel = componentViewModel)
        }
    }

    override fun requestPermission() {
        isContactsLoading.value = true
    }

    override fun fillNetworkComponent(contactsList: List<Contacts>) {
        isContactsLoading.value = false
//        friendListState.headerText.value = CONTACTS_HEADER_TEXT
        friendListState.contactsList.addAll(contactsList)
    }

    override fun showErrorScreen(errorMsg: String) {
        isContactsLoading.value = false
        this.errorMsg.value = errorMsg
    }

    override fun showLoadingScreen() {
        errorMsg.value = ""
        isContactsLoading.value = true
       // friendListState.headerText.value = EMPTY_STRING
    }
}

@Composable
fun HorizontalFriendListStructure(
    modifier: Modifier = Modifier,
    headerText: String,
    contactsList: List<Contacts>
) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(headerText, Modifier.padding(4.dp))

        LazyRow(
            modifier = modifier.then(
                Modifier.fillMaxWidth()
            )
        ) {
            items(contactsList.size) {
                Column(
                    verticalArrangement = Arrangement.Bottom
                ) {
                    contactsList[it].let {
                        Column(verticalArrangement = Arrangement.Top) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color.LightGray,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = it.aka,
                                    letterSpacing = TextUnit(2f, TextUnitType.Sp),
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(16.dp)
                                        .defaultMinSize(minWidth = 20.dp)
                                )
                            }

                            Text(
                                text = it.name,
                                softWrap = true,
                                minLines = 2,
                                maxLines = 2,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillParentMaxWidth(0.2f)
                                    .padding(horizontal = 4.dp),
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorStateColumnList(modifier: Modifier = Modifier, errorMsg: String) {
    if (errorMsg.isNotEmpty()) {
        Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            Box(contentAlignment = Alignment.Center) {
                val fillWidthItemsCount = splitMaxScreenWidthByItems(70f)
                LazyRowFilling(
                    textModifier = Modifier.background(
                        color = Color.Red,
                        shape = RoundedCornerShape(12.dp)
                    ),
                    columnModifier = Modifier.background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(12.dp)
                    ),
                    count = fillWidthItemsCount,
                    text = "!"
                )

                Column(
                    Modifier.matchParentSize()
                        .background(color = Color.Black.copy(alpha = 0.70f)),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = errorMsg,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.LightGray,
                        textAlign = TextAlign.Center,
                        fontSize = TextUnit(value = 20f, type = TextUnitType.Sp),
                        fontWeight = FontWeight.Bold,

                        )

                }

            }
        }
    }
}

@Composable
fun LazyRowFilling(
    textModifier: Modifier = Modifier,
    columnModifier: Modifier = Modifier,
    count: Int,
    text: String = ""
) {
    LazyRow(Modifier.padding(12.dp).fillMaxWidth()) {
        items(count) {
            Column(
                verticalArrangement = Arrangement.Bottom
            ) {
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally).then(columnModifier)
                ) {
                    Text(
                        text = text,
                        letterSpacing = TextUnit(2f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                            .defaultMinSize(minWidth = 20.dp).then(textModifier)
                    )
                }
                Text(
                    text = "",
                    softWrap = true,
                    minLines = 2,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillParentMaxWidth(0.2f)
                        .padding(horizontal = 4.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun LoadingColumnList(modifier: Modifier = Modifier, showLoadingSkeleton: Boolean = true) {
    if (showLoadingSkeleton) {
        Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            Box(contentAlignment = Alignment.Center) {
                LazyRowFilling(
                    columnModifier = Modifier.shimmerBackground(),
                    count = splitMaxScreenWidthByItems(70f)
                )
            }
        }
    }
}

@Composable
fun splitMaxScreenWidthByItems(itemWidth: Float): Int {
    return (getScreenWidth() / itemWidth).toInt()
}

@Composable
fun getScreenWidth(): Float {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.dp.value
}

@Composable
fun Modifier.shimmerBackground(
    shape: Shape = RoundedCornerShape(12.dp)
): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "")

    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
            RepeatMode.Restart
        ),
        label = "Loading translate",
    )
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.9f),
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation, translateAnimation),
        end = Offset(translateAnimation + 100f, translateAnimation + 100f),
        tileMode = TileMode.Mirror,
    )
    return@composed this.then(background(brush, shape))
}