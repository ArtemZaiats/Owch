package com.owch.owch.navigation

sealed class Screen(val route: String) {

    object FormListScreen: Screen("FormListScreen/{vehicleId}") {
        fun setVehicleId(vehicleId: Long) = "FormListScreen/${vehicleId}"
    }

    object FormDetailsScreen: Screen("FormDetailsScreen/{formId}") {
        fun setFormId(formId: Long) = "FormDetailsScreen/${formId}"
    }

    object NotificationScreen: Screen("NotificationScreen")

    object DiaryScreen: Screen("DiaryScreen")

    object LoginScreen: Screen("LoginScreen")

    object SplashScreen: Screen("SplashScreen")

}