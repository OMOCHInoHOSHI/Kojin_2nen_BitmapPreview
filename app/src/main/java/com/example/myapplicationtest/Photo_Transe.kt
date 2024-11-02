package com.example.myapplicationtest

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import androidx.compose.runtime.Composable
//import androidx.compose.ui.text.LinkAnnotation
import com.chaquo.python.Python
import java.io.ByteArrayOutputStream


//拡張関数　//クラスを新しい機能で拡張
//Bitmapを返却
@Composable
fun Uri.getBitmapOrNull(contentResolver: ContentResolver): Bitmap? {
    return kotlin.runCatching {
        //Build.VERSION.SDK_INTでAPIレベルを取得   //Build.VERSION_CODES.QはAPIレベル29(Android10)
        //現在のAndroidのバージョンがAndroid 10以上かどうか
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val source = ImageDecoder.createSource(contentResolver, this)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(contentResolver, this)
        }
    }.getOrNull()
}