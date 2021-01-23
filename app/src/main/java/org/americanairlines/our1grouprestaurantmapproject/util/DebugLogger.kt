package org.americanairlines.our1grouprestaurantmapproject.util

import android.util.Log

class DebugLogger {
    companion object {
        private const val LOG = "LOG_X"

        fun log(message: String) {
            Log.d(LOG, message)
        }

        fun elog(errMessage: String) {
            Log.d(LOG, errMessage)
        }

    }
}