package com.gorani.jetpack_workmanager_3

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class WorkManagerTest(context: Context, workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {

    companion object {
        const val HOW_MUCH = "progress"
    }

    override suspend fun doWork(): Result {

        // percentage 표시. i -> 1, 2, .. , 9, 10 로 표기되는 것을 10을 곱하여 => 10%, 20%, .. , 90%, 100% 로 표기하도록 함.
        for (i in 1..10) {
            val result = workDataOf(HOW_MUCH to i*10)
            setProgress(result)
            delay(1000)
        }

        return Result.success()
    }

}