package com.example.employee.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import com.example.employee.R
import com.example.employee.roomDB.Employee
import com.example.employee.viewModel.EmployeeViewModel
import kotlinx.android.synthetic.main.activity_employee.*

class EmployeeActivity : AppCompatActivity() {
    private lateinit var employeeViewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)
        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        btAdd.setOnClickListener {
            if(checkValidation()){
                val employee=Employee(0,
                    etName.text.toString(),etSalary.text.toString(),
                    etDesignation.text.toString())
                employeeViewModel.insert(employee)
                val intent=Intent(this,EmployeeListActivity::class.java)
                startActivity(intent)
                clearField()
            }
        }
    }

    private fun clearField() {
        etName.setText("")
        etSalary.setText("")
        etDesignation.setText("")
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