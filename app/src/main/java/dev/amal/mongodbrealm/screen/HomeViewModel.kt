package dev.amal.mongodbrealm.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.mongodbrealm.data.MongoRepository
import dev.amal.mongodbrealm.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MongoRepository
) : ViewModel() {

    var name by mutableStateOf("")
    var objectId by mutableStateOf("")
    var filtered by mutableStateOf(false)
    var data by mutableStateOf(emptyList<Person>())

    init {
        viewModelScope.launch {
            repository.getData().collect {
                data = it
            }
        }
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun updateObjectId(id: String) {
        this.objectId = id
    }

    fun insertPerson() {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.isNotEmpty()) {
                repository.insertPerson(person = Person().apply {
                    name = this@HomeViewModel.name
                })
            }
        }
    }

    fun updatePerson() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.isNotEmpty()) {
                repository.updatePerson(person = Person().apply {
                    _id = ObjectId(hexString = this@HomeViewModel.objectId)
                    name = this@HomeViewModel.name
                })
            }
        }
    }

    fun deletePerson() {
        viewModelScope.launch {
            if (objectId.isNotEmpty()) {
                repository.deletePerson(id = ObjectId(hexString = objectId))
            }
        }
    }

    fun filterData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (filtered) {
                repository.getData().collect {
                    filtered = false
                    name = ""
                    data = it
                }
            } else {
                repository.filterData(name = name).collect {
                    filtered = true
                    data = it
                }
            }
        }
    }
}