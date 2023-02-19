package dev.amal.mongodbrealm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.amal.mongodbrealm.screen.HomeScreen
import dev.amal.mongodbrealm.screen.HomeViewModel
import dev.amal.mongodbrealm.ui.theme.MongoDBRealmTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MongoDBRealmTheme {
                val viewModel: HomeViewModel = hiltViewModel()
                val data = viewModel.data
                HomeScreen(
                    data = data,
                    filtered = viewModel.filtered,
                    name = viewModel.name,
                    objectId = viewModel.objectId,
                    onNameChanged = { viewModel.updateName(name = it) },
                    onObjectIdChanged = { viewModel.updateObjectId(id = it) },
                    onInsertClicked = { viewModel.insertPerson() },
                    onUpdateClicked = { viewModel.updatePerson() },
                    onDeleteClicked = { viewModel.deletePerson() },
                    onFilterClicked = { viewModel.filterData() }
                )
            }
        }
    }
}
