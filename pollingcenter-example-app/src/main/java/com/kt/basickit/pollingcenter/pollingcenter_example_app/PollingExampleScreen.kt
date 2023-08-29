package com.kt.basickit.pollingcenter.pollingcenter_example_app

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kt.basickit.pollingcenter.PollingCenter
import com.kt.basickit.pollingcenter.WorkableItem
import kotlinx.coroutines.runBlocking

@Composable
fun PollingExampleScreen() {

    val TAG = "PollingCenter"

    val delayedWithIntervalThreeSec = "delayedWithIntervalThreeSec"
    val instantWithIntervalTwoSec = "instantWithIntervalTwoSec"
    val instantWithIntervalDefaultSec = "instantWithIntervalDefaultSec"

    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            Button(onClick = {
                runBlocking {
                    PollingCenter.addTask(
                        WorkableItem(instantWithIntervalTwoSec, PollingCenter.PollingType.Instant, 2000)
                        { Log.d(TAG, "task (instant, interval 2sec) running") }
                    )
                }
            }) {
                Text("추가 직후 실행 후 2초 인터벌 폴링 작업 추가")
            }
        }
        Row {
            Button(onClick = {
                runBlocking {
                    PollingCenter.addTask(
                        WorkableItem(
                            delayedWithIntervalThreeSec,
                            PollingCenter.PollingType.Delayed,
                            3000
                        ) { Log.d(TAG, "task (delayed, interval 3sec) running") },
                    )
                }
            }) {
                Text("3초 인터벌 폴링 작업 추가")
            }
            Button(onClick = {
                runBlocking {
                    PollingCenter.removeTask(delayedWithIntervalThreeSec)
                }
            }) {
                Text("3초 인터벌 폴링 작업 삭제")
            }
        }
        Button(onClick = {
            runBlocking {
                PollingCenter.addTask(
                    WorkableItem.usingDefaultPollingInterval(
                        instantWithIntervalDefaultSec,
                        PollingCenter.PollingType.Instant
                    ) { Log.d(TAG, "task (instant, interval default(5sec)) running") },
                )
            }
        }) {
            Text("추가 직후 실행 후 5초 인터벌 폴링 작업 추가")
        }
        Row {
            Button(onClick = {
                PollingCenter.cancel()
            }) {
                Text("폴링 센터 중지")
            }
            Button(onClick = {
                PollingCenter.resume()
            }) {
                Text("폴링 센터 재개")
            }
        }
    }
}
