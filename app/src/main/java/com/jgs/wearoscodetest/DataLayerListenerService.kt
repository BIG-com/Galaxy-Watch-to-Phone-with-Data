package com.jgs.wearoscodetest

import com.google.android.gms.wearable.WearableListenerService
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem

class DataLayerListenerService : WearableListenerService() {

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        for (event in dataEvents) {
            if (event.type == DataEvent.TYPE_CHANGED) {
                val dataItem = event.dataItem
                if (dataItem.uri.path == "/heart_rate") {  /* 변경사항: 경로 확인 */
                    val dataMapItem = DataMapItem.fromDataItem(dataItem)
                    val heartRate = dataMapItem.dataMap.getFloat("heart_rate") /* 변경사항: 심박수 추출 */
                    HeartRateDataManager.updateHeartRate(heartRate)
                }
            }
        }
    }
}