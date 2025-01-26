package com.gabriel.msgapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabriel.msgapp.repository.MessageRepository

@Suppress("UNCHECKED_CAST")
class MessageViewModelFactory(private val repository: MessageRepository) :
    ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MessageViewModel::class.java)) {
                return MessageViewModel(repository) as T
            }

            throw IllegalArgumentException("Erro ao acessar o viewmodel")
        }
}