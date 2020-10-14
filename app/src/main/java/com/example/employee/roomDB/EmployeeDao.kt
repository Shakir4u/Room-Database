package com.example.employee.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDao {
    @Query("SELECT * from Employee ORDER BY name ASC")
    fun getEmployee(): LiveData<MutableList<Employee>
            >

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete()
    suspend fun deleteEmployee(employee: Employee)

    @Query("DELETE FROM Employee")
    suspend fun deleteAll()
}