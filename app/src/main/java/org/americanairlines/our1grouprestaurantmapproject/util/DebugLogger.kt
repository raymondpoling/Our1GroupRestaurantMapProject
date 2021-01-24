package org.americanairlines.our1grouprestaurantmapproject.util

import android.util.Log

class DebugLogger {
    companion object {
        private const val LOG = "LOG_X"
        private const val ELOG ="ERROR_LOG_X"

        fun logger(message: String) {
            Log.d(LOG, message)
        }

        fun elogger(errMessage: String) {
            Log.e(ELOG, errMessage)
        }

    }
}