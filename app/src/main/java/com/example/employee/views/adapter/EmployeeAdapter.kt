package com.example.employee.views.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employee.R
import com.example.employee.roomDB.Employee
import com.example.employee.viewModel.EmployeeViewModel
import com.example.employee.views.dialogs.EditDialogActivity
import kotlinx.android.synthetic.main.row_employee.view.*

class EmployeeAdapter internal constructor(var context: Context,var listner: ClickListner,var employeeViewModel: EmployeeViewModel): RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private  var employees= mutableListOf<Employee>()
    inner  class EmployeeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val tvName:TextView  = itemView.findViewById(R.id.tvName)
        val tvSalary:TextView  = itemView.findViewById(R.id.tvSalary)
        val ivEdit:ImageView  = itemView.findViewById(R.id.ivEdit)
        val ivDelete:ImageView  = itemView.findViewById(R.id.ivDelete)
        val cbSalary:CheckBox  = itemView.findViewById(R.id.cbSalary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = inflater.inflate(R.layout.row_employee, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return employees.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.tvName.text ="Name:- "+ employee.name
        holder.tvSalary.text ="Salary:- "+ employee.salary


        holder.cbSalary.setOnClickListener {
            listner.onClick(position,holder.cbSalary.isChecked)
        }

        holder.ivDelete.setOnClickListener {
            employeeViewModel.deleteEmployee(employee)
            employees.removeAt(position)
            notifyItemRemoved(position)
        }

        holder.ivEdit.setOnClickListener {
            val intent=Intent(context,EditDialogActivity::class.java).apply {
                putExtra("data",employee)
            }
            context.startActivity(intent)
        }
    }

    internal fun setData(employees: MutableList<Employee>){
        this.employees=employees
        notifyDataSetChanged()
    }
    internal fun getList():MutableList<Employee>{
        return this.employees

    }
}