package com.jgs.wearoscodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var heartRateText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heartRateText = findViewById(R.id.heartRateText)

        // 데이터 업데이트를 위해 LiveData나 BroadcastReceiver를 사용하거나,
        // Application 단에 전역변수를 두고 서비스에서 업데이트할 수도 있음.
        // 여기서는 단순 예제로, Singleton 등을 통해 수신 데이터를 업데이트
        HeartRateDataManager.onHeartRateUpdate = { rate ->
            runOnUiThread {
                heartRateText.text = "Current Heart Rate: $rate bpm"
            }
        }
    }
}

// 심박수 데이터 공유를 위한 단순 매니저 예제
object HeartRateDataManager {
    var onHeartRateUpdate: ((Float) -> Unit)? = null
    fun updateHeartRate(rate: Float) {
        onHeartRateUpdate?.invoke(rate)
    }
}