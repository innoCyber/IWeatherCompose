package com.example.iweathercompose.utils

import android.R.attr.fontFamily
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.iweathercompose.ui.theme.Typography

val appTypography = Typography(

    displayLarge = Typography.bodyLarge.copy(fontFamily = appFontFamily, color = Color.White, fontSize = 100.sp),
    displaySmall = Typography.bodyLarge.copy(fontFamily = appFontFamily, color = Color.White),
    displayMedium = Typography.bodyLarge.copy(fontFamily = appFontFamily, color = Color.White),
)