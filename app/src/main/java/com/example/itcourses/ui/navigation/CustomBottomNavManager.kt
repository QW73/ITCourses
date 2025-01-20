package com.example.itcourses.ui.navigation

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.itcourses.R
import com.example.itcourses.databinding.CustomBottomNavViewBinding

enum class Tab {
    HOME, FAVORITES, ACCOUNT
}

class CustomBottomNavManager(
    private val context: Context
) {

    private lateinit var navController: NavController
    private lateinit var binding: CustomBottomNavViewBinding

    private val lineSpacingExtra4Px =
        context.resources.getDimension(R.dimen.bottom_nav_spacing_extra_4sp)
    private val lineSpacingExtra3Px =
        context.resources.getDimension(R.dimen.bottom_nav_spacing_extra_3sp)

    fun setup(binding: CustomBottomNavViewBinding, navController: NavController) {
        this.binding = binding
        this.navController = navController

        highlightSelectedTab(Tab.HOME)

        binding.navHome.setOnClickListener {
            navController.navigate(R.id.navigation_home)
            highlightSelectedTab(Tab.HOME)
        }
        binding.navFavorites.setOnClickListener {
            navController.navigate(R.id.navigation_favorites)
            highlightSelectedTab(Tab.FAVORITES)
        }
        binding.navAccount.setOnClickListener {
            navController.navigate(R.id.navigation_account)
            highlightSelectedTab(Tab.ACCOUNT)
        }
    }

    // Highlighting the selected tab
    private fun highlightSelectedTab(selectedTab: Tab) {

        when (selectedTab) {
            Tab.HOME -> {
                highlightTab(binding.navHomeIcon, binding.navHomeText)
                resetTab(binding.navAccountIcon, binding.navAccountText)
                resetTab(binding.navFavoritesIcon, binding.navFavoritesText)
            }

            Tab.FAVORITES -> {
                highlightTab(binding.navFavoritesIcon, binding.navFavoritesText)
                resetTab(binding.navHomeIcon, binding.navHomeText)
                resetTab(binding.navAccountIcon, binding.navAccountText)
            }

            Tab.ACCOUNT -> {
                highlightTab(binding.navAccountIcon, binding.navAccountText)
                resetTab(binding.navHomeIcon, binding.navHomeText)
                resetTab(binding.navFavoritesIcon, binding.navFavoritesText)
            }
        }
    }

    // Resetting the tab
    private fun resetTab(navIcon: ImageView, navText: TextView) {

        val defaultColor = ContextCompat.getColor(context, R.color.white)

        navIcon.setBackgroundResource(0)
        navIcon.setColorFilter(defaultColor)

        navText.setTextColor(defaultColor)
        navText.letterSpacing = 0.04f
        navText.setLineSpacing(lineSpacingExtra3Px, 1f)
    }

    // Highlighting the tab
    private fun highlightTab(navIcon: ImageView, navText: TextView) {

        val primaryColor = ContextCompat.getColor(context, R.color.green)

        navIcon.setColorFilter(primaryColor)
        navIcon.setBackgroundResource(R.drawable.rounded_nav_icon_container)

        navText.setTextColor(primaryColor)
        navText.letterSpacing = 0.05f
        navText.setLineSpacing(lineSpacingExtra4Px, 1f)
    }


}