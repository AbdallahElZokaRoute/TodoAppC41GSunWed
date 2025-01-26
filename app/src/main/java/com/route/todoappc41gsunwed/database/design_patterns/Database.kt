package com.route.todoappc41gsunwed.database.design_patterns

class Database {

}

val database1 = Database() // 123456
val database2 = Database() // 123456
val data0 = AppConstants.getInstance() // 123456
val data1 = AppConstants.getInstance() // 123456
// Kotlin + SQLite




class AppConstants private constructor() {

    companion object {
        private var INSTANCE: AppConstants? = null
        fun getInstance(): AppConstants {
            if (INSTANCE == null) {
                INSTANCE = AppConstants()
            }
            return INSTANCE!!
        }
    }
}
