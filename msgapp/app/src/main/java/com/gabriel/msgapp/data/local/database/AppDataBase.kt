package com.gabriel.msgapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabriel.msgapp.data.local.dao.MessageDao
import com.gabriel.msgapp.model.Message

@Database(entities = [Message::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun messageDao() : MessageDao
}