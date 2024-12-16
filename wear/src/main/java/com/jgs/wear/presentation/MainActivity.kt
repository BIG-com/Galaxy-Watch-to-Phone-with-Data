/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.jgs.wear.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jgs.wear.R

class MainActivity : AppCompatActivity() {
    // 단순히 앱이 실행되어 있는지 확인하기 위한 액티비티
    // 실제 심박수 로직은 HeartRateService에서 진행
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}