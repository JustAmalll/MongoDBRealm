package dev.amal.mongodbrealm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.amal.mongodbrealm.ui.theme.MongoDBRealmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MongoDBRealmTheme {

            }
        }
    }
}
