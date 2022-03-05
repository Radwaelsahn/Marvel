package com.marvel.radwa.data.local

import javax.inject.Inject


/**
 * Created by Radwa Elsahn on 7/7/2020
 */

class LocalRepository @Inject constructor() {

    companion object {
        const val KEY_FCM_TOKEN = "fcmToken"
        const val KEY_USER = "user"
        const val KEY_DEFAULT_CARD_ID = "defaultCardId"
        const val KEY_IS_FIRST_TIME = "KEY_IS_FIRST_TIME"
        const val KEY_SELECTED_COUNTRY = "selectedCountry"
        const val KEY_SELECTED_CATEGORIES = "selectedCategories"
        const val KEY_SELECTED_CATEGORY_SHOW_ALL_LINK = "showAllLink"
        const val KEY_SELECTED_COUNTRY_SEARCH = "selectedCountrySearch"
        const val KEY_SELECTED_CATEGORIES_SEARCH = "selectedCategoriesSearch"
        const val KEY_SELECTED_SUBJECTS = "selectedSubjects"
        const val KEY_PLAY_BACK_SPEED = "playbackSpeed"
        const val KEY_VIDEO_QUALITY = "KEY_VIDEO_QUALITY"
        const val KEY_AUTO_PLAY = "KEY_AUTO_PLAY"
        const val KEY_RECENT_SEARCH_ITEMS = "recentSearchItems"
        const val KEY_PLAY_BACK_TIME = "KEY_PLAY_BACK_TIME"
        const val KEY_RECORD_DEVICE = "KEY_RECORD_DEVICE"
        const val KEY_EDU_CATEGORIES = "KEY_EDU_CATEGORIES"
        const val KEY_INTERCOM_REGISTERED = "KEY_INTERCOM_REGISTERED"
        const val KEY_REFRESH_TOKEN_EXPIRED = "verify_refresh_token_timer"
        const val KEY_PHONE_REQUIRED_COUNTER = "KEY_PhoneRequiredCounter"
    }



}
