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
    bodyMedium = Typography.bodyMedium.copy(fontFamily = appFontFamily, color = Color.White),
    bodySmall = Typography.bodySmall.copy(fontFamily = appFontFamily, color = Color.White),
    bodyLarge = Typography.bodyLarge.copy(fontFamily = appFontFamily, color = Color.White),
    titleLarge = Typography.titleLarge.copy(fontFamily = appFontFamily, color = Color.White),
    titleSmall = Typography.titleSmall.copy(fontFamily = appFontFamily, color = Color.White),
    titleMedium = Typography.titleMedium.copy(fontFamily = appFontFamily, color = Color.White),
    headlineLarge = Typography.headlineLarge.copy(fontFamily = appFontFamily, color = Color.White),
    headlineSmall = Typography.headlineSmall.copy(fontFamily = appFontFamily, color = Color.White),
    headlineMedium = Typography.headlineMedium.copy(fontFamily = appFontFamily, color = Color.White),
)