package com.yudha.tokoonline.api.model.localDatabase.dao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yudha.tokoonline.api.model.localDatabase.table.User

@Dao
interface UserDao {

    /*@Query("SELECT * FROM user_table WHERE id = :id LIMIT 1")
    fun getUserById(id: Int): User?*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User) {
        Log.d("User Dao", "Inserting user: $user")
    }

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUserById(id: Int): User?

    @Update
    fun updateUser(user: User)

}