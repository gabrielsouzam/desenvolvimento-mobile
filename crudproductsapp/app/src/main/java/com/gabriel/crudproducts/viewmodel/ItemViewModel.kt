package com.gabriel.crudproducts.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gabriel.crudproducts.model.Item
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration


class ItemViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private var listenerregistration: ListenerRegistration? = null

    var items = mutableStateOf<List<Item>>(listOf())
        private set

    init {

    }

    private fun listenToItems() {
        listenerregistration = db.collection("items")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val fetchedItems = snapshot.documents.map { document ->
                        document.toObject(Item::class.java)?.copy(id = document.id)
                    }.filterNotNull()

                    items.value = fetchedItems
                }
            }
    }

    fun addItem(item: Item) {
        db.collection("items").add(item)
    }

    fun deleteItem(itemId: String) {
        db.collection("items").document(itemId).delete()
    }

    override fun onCleared() {
        super.onCleared()
        listenerregistration?.remove()
    }

    fun updateItem(item: Item) {
        db.collection("items").document(item.id).set(item)
            .addOnSuccessListener {
                Log.d("ItemViewModel", "Item criado com sucesso")
            }
            .addOnFailureListener { exception ->
                Log.e("ItemViewModel", "Erro ao utilizar o item", exception)
            }
    }



}