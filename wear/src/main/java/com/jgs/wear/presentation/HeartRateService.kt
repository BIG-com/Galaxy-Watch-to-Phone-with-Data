package com.jgs.wear.presentation

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LifecycleService
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import kotlin.random.Random

class HeartRateService : LifecycleService() {

    private val handler = Handler(Looper.getMainLooper())
    private val random = Random

    override fun onCreate() {
        super.onCreate()
        startMockHeartRateTransmission()
    }

    private fun startMockHeartRateTransmission() {
        // 주기적으로 임의의 심박수 전송
        handler.post(object : Runnable {
            override fun run() {
                val mockHeartRate = generateMockHeartRate()
                sendHeartRateToPhone(mockHeartRate)

                // 일정 시간 후 다시 실행 (예: 1000ms = 1초)
                handler.postDelayed(this, 1000L)
            }
        })
    }

    private fun generateMockHeartRate(): Float {
        // 60 ~ 100 사이의 임의의 심박수 값 생성
        return random.nextInt(60, 101).toFloat()
    }

    private fun sendHeartRateToPhone(heartRate: Float) {
        val dataClient = Wearable.getDataClient(this)
        val putDataReq = PutDataMapRequest.create("/heart_rate").run {
            dataMap.putFloat("heart_rate", heartRate)
            asPutDataRequest()
        }
        dataClient.putDataItem(putDataReq)
            .addOnSuccessListener {
                Log.d("HeartRateService", "Mock heart rate sent: $heartRate")
            }
            .addOnFailureListener {
                Log.e("HeartRateService", "Failed to send mock heart rate", it)
            }
    }

    private fun stopMockHeartRateTransmission() {
        handler.removeCallbacksAndMessages(null)
    }
}
