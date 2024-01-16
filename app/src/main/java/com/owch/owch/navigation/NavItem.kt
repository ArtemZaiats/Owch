package com.owch.owch.navigation

import com.owch.owch.R

sealed class NavItem(var title: String, var icon: Int, var screenRoute: String) {

    object Home : NavItem("Home", R.drawable.ic_home_nav_bar, "home")
    object Chat : NavItem("Chat", R.drawable.ic_owch_buddy, "chat")
    object Inspect : NavItem("Inspect", R.drawable.ic_inspect_nav_bar, "inspect")
    object Location : NavItem("Location", R.drawable.ic_location, "location")
}