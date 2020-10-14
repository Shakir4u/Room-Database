package com.example.employee.views.dialogs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import com.example.employee.R
import com.example.employee.roomDB.Employee
import com.example.employee.viewModel.EmployeeViewModel
import com.example.employee.views.EmployeeListActivity
import kotlinx.android.synthetic.main.activity_employee.*

class EditDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)
        var employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        val employee=intent.getParcelableExtra<Employee>("data")
        employee?.let {
            etName.setText(it.name)
            etSalary.setText(it.salary)
            etDesignation.setText(it.designation)
        }
        btAdd.setText("Update")
        btAdd.setOnClickListener {

            if(checkValidation()){
                employee?.apply {
                    name=etName.text.toString()
                    salary=etSalary.text.toString()
                    designation=etDesignation.text.toString()
                    employeeViewModel.updateEmployee(this)
                }
                finish()
            }
        }
    }

    private fun checkValidation(): Boolean {
        if(TextUtils.isEmpty(etName.text.toString())){
            etName.setError("* Please Enter Employee Name")
            etName.requestFocus()
            return false
        }else if(TextUtils.isEmpty(etSalary.text.toString())) {
            etSalary.setError("* Please Enter Employee Salary")
            etSalary.requestFocus()
        }
        else if(TextUtils.isEmpty(etDesignation.text.toString())){
            etDesignation.setError("* Please Enter Employee Designation")
            etDesignation.requestFocus()
            return false
        }else{
            return  true
        }
        return false;
    }
}