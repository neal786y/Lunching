package com.neal786y.mvparchitecture.base

interface Constants {
    companion object {
        const val ENTITY_ID = "entityId"
        const val ENTITY_TYPE = "entityType"

        const val LOCATION_REQUEST_CODE = 333
        const val PERMISSION_REQUEST_CODE = 888
        const val MIN_SEARCH_CHARACTER = 3
        const val LOCATION_SEARCH_DELAY: Long = 500 // milliseconds
    }
}