package com.ikumb.edugate.core

object Constants {

    object IntentName {
        const val LOGIN_ACTIVITY_TYPE = "login_activity_type"
        const val PLAYER_ACTIVITY_ALL_IDS = "allPodIds"
        const val PLAYER_ACTIVITY_POSITION = "position"
        const val PLAYER_ACTIVITY_ITEM = "pod"
        const val PODCAST_ITEM = "podcast"
    }


        object Coords {
            const val LAT = "lat"
            const val LON = "lon"
            const val METRIC = "metric"

        }

        object LoginActivityType {
            const val LOGIN_TYPE = 0
            const val REGISTER_TYPE = 1
            const val EMAIL_VERIFY = 2
            const val FORGOT_PASS = 3
        }
    }
