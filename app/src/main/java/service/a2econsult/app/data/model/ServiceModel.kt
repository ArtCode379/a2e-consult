package service.a2econsult.app.data.model

import androidx.annotation.DrawableRes
import java.time.LocalTime

data class ServiceModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val availableTime: List<LocalTime>? = null,
    @field:DrawableRes val imageRes: Int,
)