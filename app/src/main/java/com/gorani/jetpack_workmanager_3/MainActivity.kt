package com.gorani.jetpack_workmanager_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.gorani.jetpack_workmanager_3.WorkManagerTest.Companion.HOW_MUCH

/**
 * WorkManager 진행률 알아보기. (진행상태, 진척도 검토)
 * => WorkManager + Coroutine 조합을 사용한다.
 *
 * ex. WorkManager 작업이 오래 걸리는 작업이다.
 * 이 작업은 60초가 걸리는 작업이다.
 * 이 작업의 진행상태를 알아본다. => 1초.. 10초.. 25초.. 30초..
 * 시간이 경과됨에 따라 작업률의 현재 진행 상태를 percentage 로 표시하도록 한다. => 몇 퍼센트가 진행 되었는지!
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workManagerTest = OneTimeWorkRequestBuilder<WorkManagerTest>().build()
        WorkManager.getInstance(this).enqueue(workManagerTest)

        // WorkManager 에서 변동되는 값(i) 을 WorkManager 가 실행되는 곳(여기 MainActivity) 관찰.
        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(workManagerTest.id)
            .observe(this) { workInfo ->
                val progress = workInfo.progress
                val value = progress.getInt(HOW_MUCH, 0)

                if (value != 0) {
                    Log.d("MainActivity_!!", "$value%")
                }

                if (value == 100) {
                    Log.d("MainActivity_!!", "작업이 모두 완료되었습니다.")
                }

            }

    }
}