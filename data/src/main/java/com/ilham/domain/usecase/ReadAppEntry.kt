package com.ilham.domain.usecase

import com.ilham.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(): Flow<Boolean>{
        return localUserManger.readAppEntry()
    }

}