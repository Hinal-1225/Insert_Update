package com.example.insert_update

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(var context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    companion object {
        private var DB_NAME = "PracticeDB"
        private var TB_EMPLOYEE = "EmployeeDetail"
        private var DB_VERSION = 1
        private var EM_ID = "em_id"
        private var EM_POST = "em_post"
        private var EM_DEPT = "em_dept"
        private var EM_SALARY = "em_salary"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var query =
            "CREATE TABLE $TB_EMPLOYEE($EM_ID INTEGER PRIMARY KEY AUTOINCREMENT, $EM_POST TEXT, $EM_DEPT TEXT, $EM_SALARY INTEGER)";
        p0?.execSQL(query);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }


    fun insert(Employee: Employee): Boolean {
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(EM_POST, Employee.em_post)
        cv.put(EM_DEPT, Employee.em_dept)
        cv.put(EM_SALARY, Employee.em_salary)

        var flag = db.insert(TB_EMPLOYEE, null, cv)
        if (flag > 0) {
            return true
        } else {
            return false
        }
    }

    fun retriveAll(): ArrayList<Employee> {
        var db = readableDatabase
        var cursor = db.query(TB_EMPLOYEE, null, null, null, null, null, null)
        var arr: ArrayList<Employee> = ArrayList()
        while (cursor.moveToNext()) {
            var id = cursor.getInt(0)
            var post = cursor.getString(1)
            var dept = cursor.getString(2)
            var salary = cursor.getInt(3)
            var employee = Employee(id, post, dept, salary)
            arr.add(employee)
        }
        return arr
    }


    fun update(Employee:Employee)
    {
        var db=writableDatabase
        var cv= ContentValues()
        cv.put(EM_POST,Employee.em_post)
        cv.put(EM_DEPT,Employee.em_dept)
        cv.put(EM_SALARY,Employee.em_salary)
        db.update(TB_EMPLOYEE,cv,"$EM_ID=${Employee.em_id}", null)
        db.close()
    }
}