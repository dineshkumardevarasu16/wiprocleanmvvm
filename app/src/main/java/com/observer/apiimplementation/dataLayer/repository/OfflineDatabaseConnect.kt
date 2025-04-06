package com.observer.apiimplementation.dataLayer.repository

import android.content.Context

object OfflineDatabaseConnect {

    //Given a string s, reverse only all the vowels in the string and return it.

    //The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

    //Input: s = "IceCreAm"

    //Output: "AceCreIm"

    //Explanation:

    //The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

    fun initializeDatabase(context: Context) : OfflineDatabaseManager
    {
        val offlineDatabaseRepositoryImpl = OfflineDatabaseRepositoryImpl.initializeDatabaseRepo()
        offlineDatabaseRepositoryImpl.initializeDatabase(context)
        return offlineDatabaseRepositoryImpl
    }
}