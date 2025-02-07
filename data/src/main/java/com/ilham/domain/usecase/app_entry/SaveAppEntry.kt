package com.ilham.domain.usecase.app_entry

import com.ilham.domain.manger.LocalUserManger

class SaveAppEntry (
    private val localUserManger: LocalUserManger
){

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}