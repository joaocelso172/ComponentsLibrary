package org.example.project

import App
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import entity.Transaction

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val transactionMutableList = mutableStateListOf<Transaction>()
        transactionMutableList.add(Transaction("title", "desc", 1, 1, 0, 231f, "22/01/2024"))
        transactionMutableList.add(Transaction("title", "desc", 1, 3, 0, 231f, "10/02/2025"))

        setContent {
            Column {
                ListItems(transactionMutableList)
                FloatingButton { startActivity(Intent(this@MainActivity, NetworkActivity::class.java)) }
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}


@Composable
fun ListItems(list: List<Transaction>) {
    Column(Modifier.padding(15.dp)) {
        LazyColumn(Modifier) {
            item {
                Text(text = "Transactions")
            }
            items(list.size) { index ->
                Column(
                    Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row {
                        Column {
                            Text(text = list[index].title)
                            Text(text = list[index].description)
                            Text(text = "${list[index].paymentType}x")
                        }
                        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                            Text(text = list[index].date)
                            Text(text = "R$ ${list[index].value}")
                        }
                    }

                    Divider(
                        color = Color.Black,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

}

@Composable
fun FloatingButton(onclick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(15.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(onClick =  onclick ) {
            Icon(Icons.Filled.Add,"")
        }
    }
}