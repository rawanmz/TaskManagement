package com.example.taskmanagement.presentaion.component

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagement.R
import com.example.taskmanagement.ui.theme.cardColor
import com.example.taskmanagement.ui.theme.titleColor
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val CURRENT_LANGUAGE = "pref_current_language"

/// bubble
@Composable
fun MessageBubbleViewRTL(isSender: Boolean) {
    val background =
        if (!isSender) Color(220, 248, 198) else MaterialTheme.colors.cardColor
    Row(
        Modifier
            .padding(20.dp)
            .wrapContentSize()
            .mirror(isSender),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            Modifier
                .align(Alignment.Top)
                .padding(top = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_chat_tail),
                contentDescription = "",
                modifier = Modifier.size(height = 22.dp, width = 10.dp),
                colorFilter = ColorFilter.tint(background),
            )
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(background, RoundedCornerShape(6.dp))
        ) {
            //content
            MessageContent(false, false)
        }
    }
}

@Composable
fun MessageBubbleViewLTR(isSender: Boolean) {
    val background =
        if (!isSender) Color(220, 248, 198) else MaterialTheme.colors.cardColor
    Row(
        Modifier
            .padding(20.dp)
            .wrapContentSize()
            .mirror(isSender)
        ,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(background, RoundedCornerShape(6.dp))
        ) {
            //content
            MessageContent(isSender, true)
        }
        Box(
            Modifier
                .align(Alignment.Top)
                .padding(top = 12.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_chat_tail),
                contentDescription = "",
                modifier = Modifier.size(height = 22.dp, width = 10.dp),
                colorFilter = ColorFilter.tint(background),
            )
        }
    }
}

@Composable
fun MessageContent(isSender: Boolean, isLTR: Boolean) {
    Column(
        Modifier
            .wrapContentSize()
            .widthIn(max=320.dp)
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .mirror(isSender),
        horizontalAlignment = if (isLTR) Alignment.End else Alignment.Start,
    ) {

//        if((item.message?.lines()?.size ?: 1) > 16){
//            ExpandableText(
//                item.message.orEmpty(),
//                Modifier.wrapContentHeight(),
//            )}
//        else{
            Text(
               modifier= Modifier.wrapContentHeight(),
                text="  cl",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.titleColor,
                fontWeight = FontWeight.Bold
            )
       // }
//        Text(
//            modifier=Modifier.wrapContentHeight(),
//            text="12:00 pm",
//            textSize = 9.sp,
//            textAlign = if (isLTR) TextAlign.End else TextAlign.Start,
//            color = MaterialTheme.colors.titleColor,
//            fontWeight = FontWeight.Bold
//        )
    }
}
//@Composable
//fun getLocale(context: Context): Locale {
//    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//
//    val currentLang = sharedPreferences.getString(CURRENT_LANGUAGE, "ar") ?: "ar"
//    return if (currentLang.isNullOrBlank()) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//            Resources.getSystem().configuration.locales[0]
//        else
//            Resources.getSystem().configuration.locale
//    } else {
//        Locale(currentLang)
//    }
//}
//
//@Composable
//fun isRTLLanguage(context: Context): Boolean {
//    if (getLocale(context).language == "ar" || getLocale(context).language == "ur") return true
//    else return false
//}

fun Modifier.mirror(isFlippable: Boolean?): Modifier = composed {
    if (isFlippable == true) {
        this.scale(scaleX = -1f, scaleY = 1f)
    } else {
        this
    }
}

//fun getTime(
//    pattern: String = "yyyy-MM-dd'T'HH:mm:ss",
//    dateString: String?,
//    locale: Locale = Locale.ENGLISH,
//    toFormat: String = "hh:mm a"
//): String {
//    return if (dateString.isNullOrEmpty()) ""
//    else {
//        val format: DateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
//        val time = format.parse(dateString)
//        return SimpleDateFormat(toFormat, locale).format(time)
//    }
//}