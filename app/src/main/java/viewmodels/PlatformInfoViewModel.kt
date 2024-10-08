package viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import api.API
import entity.Platform

class PlatformInfoViewModel: ViewModel() {

    var platform by mutableStateOf(Platform(0,"","",0,"",""))

    fun onInit(platformId: Int) {
        loadPlatform(platformId)
    }

    private fun loadPlatform(platformId : Int) {

        API.loadGamesPlatform(platformId,{ platform ->
            this.platform = platform
        }, {
            println("Error - PlatformInfoViewModel")
        })

    }

}