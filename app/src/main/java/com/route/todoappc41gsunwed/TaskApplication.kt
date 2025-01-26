package com.route.todoappc41gsunwed

import android.app.Application
import com.route.todoappc41gsunwed.database.TaskDatabase

class TaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        TaskDatabase.init(this)
    }
}
