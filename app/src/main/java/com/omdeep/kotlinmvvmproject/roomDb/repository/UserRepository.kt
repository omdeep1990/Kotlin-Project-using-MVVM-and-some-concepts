package com.omdeep.kotlinmvvmproject.roomDb.repository

import com.omdeep.kotlinmvvmproject.roomDb.db.User
import com.omdeep.kotlinmvvmproject.roomDb.db.UserDao

//TODO: Create constructor of Interface UserDao here and call all the methods of UserDao here
class UserRepository(val dao : UserDao) {

    //TODO: Insert function called
    suspend fun insert(user : User) {
        dao.insertUser(user)
    }

    //TODO: Update function called
    suspend fun update(user: User) {
        dao.updateUser(user)
    }

    //TODO: Delete function called
    suspend fun delete(user: User) {
        dao.deleteUser(user)
    }

    //TODO: Delete all function called
    suspend fun deleteAll() {
        dao.deleteAllUSers()
    }

    //TODO: Fetching function called
    val users = dao.getUsers()
}