package com.example.practicaltest.core.util

object Constant {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "c61caf000df34c8790e911d40f57fa67"
    const val PREF_USER = "user_data"
}

object ApiResponseCodes {
    const val UNAUTHORIZED = 401
    const val SERVER_ERROR = 500
}

object Msg {
    const val ERROR_COMMON = "Oops, something went wrong. Let\'s try it again."
    const val INTERNET_ISSUE = "Sorry, Seems your internet connection is not available. Check & try again."
}

object IntentParcelable {
    const val LATEST_NEWS = "latest_news"
}

object DataBase {
    const val DB_TBL_NAME = "users"
    const val DB_TBL_USER_ID = "user_id"
    const val DB_TBL_FIRST_NAME = "first_name"
    const val DB_TBL_LAST_NAME = "last_name"
    const val DB_TBL_EMAIL = "email"
    const val DB_TBL_PASSWORD = "password"
}