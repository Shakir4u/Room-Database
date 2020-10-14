package com.example.employee.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.employee.repo.EmployeeRepo
import com.example.employee.roomDB.Employee
import com.example.employee.roomDB.EmployeeDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application):AndroidViewModel(application) {
    private val employeeRepo:EmployeeRepo
     val employeeList:LiveData<MutableList<Employee>>
    init {
        val employeeDao = EmployeeDB.getDatabase(application).employeeDao()
        employeeRepo = EmployeeRepo(employeeDao)
        employeeList = employeeRepo.employeeList
    }

    fun insert(employee: Employee) = viewModelScope.launch(Dispatchers.IO) {
        employeeRepo.insert(employee)
    }

    fun updateEmployee(employee: Employee) = viewModelScope.launch(Dispatchers.IO) {
        employeeRepo.updateEmployee(employee)
    }

    fun deleteEmployee(employee: Employee)=viewModelScope.launch( Dispatchers.IO ) {
        employeeRepo.deleteEmployee(employee)
    }
}