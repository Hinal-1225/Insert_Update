package com.example.insert_update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnInsert.setOnClickListener{
            var post = edtEmPost.text.toString()
            var dept = edtEmDept.text.toString()
            var salary = edtEmSalary.text.toString().toInt()
            var employee = Employee(post,dept,salary)
            var db = DBHelper(this)
            var flag = db.insert(employee)
            if(flag)
            {
                Toast.makeText(this, "Record Inserted Successfully", Toast.LENGTH_SHORT).show()
                edtEmPost.setText("")
                edtEmDept.setText("")
                edtEmSalary.setText("")
            }

        }

        btnViewAll.setOnClickListener{
            var intent = Intent(this,ViewAllActivity::class.java)
            startActivity(intent)
        }

    }
}