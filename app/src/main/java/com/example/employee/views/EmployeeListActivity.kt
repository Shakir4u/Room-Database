package com.example.employee.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employee.R
import com.example.employee.viewModel.EmployeeViewModel
import com.example.employee.views.adapter.ClickListner
import com.example.employee.views.adapter.EmployeeAdapter
import kotlinx.android.synthetic.main.activity_employee_list.*
import kotlin.properties.Delegates

class EmployeeListActivity : AppCompatActivity(),ClickListner {
    private lateinit var employeeViewModel: EmployeeViewModel
    private  var sum:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)
        employeeViewModel= ViewModelProvider(this).get(EmployeeViewModel::class.java)

        val adapter:EmployeeAdapter= EmployeeAdapter(this,this,employeeViewModel);
        rvEmployee.adapter=adapter
        rvEmployee.layoutManager= LinearLayoutManager(this)

        employeeViewModel.employeeList.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onClick(postion: Int, isChecked: Boolean) {
        if(isChecked){
            sum+=(rvEmployee.adapter as EmployeeAdapter).getList()[postion].salary.toLong()
        }else{
            sum-=(rvEmployee.adapter as EmployeeAdapter).getList()[postion].salary.toLong()
        }
        Toast.makeText(applicationContext,"Sum of Salary is $sum",Toast.LENGTH_SHORT).show()
    }

}