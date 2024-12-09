package com.ilham.domain.usecase.app_entry

import com.ilham.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {

    operator fun invoke(): Flow<Boolean>{
        return localUserManger.readAppEntry()
    }

}