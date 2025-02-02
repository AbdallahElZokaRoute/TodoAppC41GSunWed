package com.route.todoappc41gsunwed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.todoappc41gsunwed.fragments.AddTaskFragment
import com.route.todoappc41gsunwed.databinding.ActivityMainBinding
import com.route.todoappc41gsunwed.fragments.SettingsFragment
import com.route.todoappc41gsunwed.fragments.TasksListFragment
import com.route.todoappc41gsunwed.fragments.callbacks.OnTodoAddedListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tasksListFragment: TasksListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tasksListFragment = TasksListFragment()
        binding.addTaskFab.setOnClickListener {
            val bottomSheetDialogFragment = AddTaskFragment()
            bottomSheetDialogFragment.onTodoAddedListener = OnTodoAddedListener {
                tasksListFragment.getTaskListFromDatabase()
            }
            bottomSheetDialogFragment.show(supportFragmentManager, null)
        }
        binding.todoBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_list -> {
                    showFragment(tasksListFragment)
                }

                R.id.navigation_settings -> {
                    showFragment(SettingsFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.todoBottomNavigationView.selectedItemId = R.id.navigation_list
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.todo_fragment_container, fragment)
            .commit()
    }
}