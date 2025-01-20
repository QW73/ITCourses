package com.example.itcourses.ui


import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.example.itcourses.R
import com.example.itcourses.databinding.ActivityMainBinding
import com.example.itcourses.databinding.CustomBottomNavViewBinding
import com.example.itcourses.ui.navigation.CustomBottomNavManager
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val customBottomNavManager: CustomBottomNavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        binding = ActivityMainBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val bottomNavBinding = CustomBottomNavViewBinding.bind(binding.navView.customBottomNav)

        customBottomNavManager.setup(
            bottomNavBinding, navController
        )


    }


}