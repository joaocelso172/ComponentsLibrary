package org.example.project.common.presentation.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import entity.Friend


@Composable
fun FriendListComponent(componentState: FriendListComponentState){
//    val state by remember{ mutableStateOf(componentState) }

    Text(text = componentState.headerText, modifier =  Modifier.padding(4.dp))

    LazyRow(
        modifier = Modifier.then(
            Modifier.fillMaxWidth()
        )
    ) {
        componentState.friendList.apply {
            items(size) {
                Column(
                    verticalArrangement = Arrangement.Bottom
                ) {
                    this@apply[it].let {
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


@Preview
@Composable
fun FriendListPreview(){
   // FriendListComponent(state = FriendListComponentState("Friend List Component", mutableListOf(Friend("João Celso", "11 975660479", "JC"), )))
}