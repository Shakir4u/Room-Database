package com.example.employee.repo

import androidx.lifecycle.LiveData
import com.example.employee.roomDB.Employee
import com.example.employee.roomDB.EmployeeDao

class EmployeeRepo(val employeeDao: EmployeeDao) {
    val employeeList:LiveData<MutableList<Employee>> =  employeeDao.getEmployee()

    suspend fun insert(employee: Employee) {
        employeeDao.insert(employee)
    }

    suspend fun updateEmployee(employee: Employee) {
        employeeDao.updateEmployee(employee)
    }
    suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee)
    }
}