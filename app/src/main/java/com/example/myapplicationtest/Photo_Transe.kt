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

//受け取ったBitmapをARGB_8888に変換する関数
fun convertToARGB8888(bitmap: Bitmap): Bitmap {
    //ARGB_8888ではないなら変換
    if (bitmap.config != Bitmap.Config.ARGB_8888) {
        println("ARGB_8888に変換")

        //ARGB_8888で初期化して生成
        val newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false)

        //元のBitmapは解放
        bitmap.recycle()

        return newBitmap
    } else {
        println("ARGB_8888でした")
        return bitmap
    }
}


//受け取ったBitmapをRGB_565に変換する関数
fun convertToRGB_565(bitmap: Bitmap): Bitmap {
    //ARGB_8888ではないなら変換
    if (bitmap.config != Bitmap.Config.RGB_565) {
        println("RGB_565に変換")

        //ARGB_8888で初期化して生成
        val newBitmap = bitmap.copy(Bitmap.Config.RGB_565, false)

        //元のBitmapは解放
        bitmap.recycle()

        return newBitmap
    } else {
        println("RGB_565でした")
        return bitmap
    }
}


// 受け取ったbitmapを640×640の大きさにリサイズする関数
fun resizeTo640x640(bitmap: Bitmap): Bitmap{

    val width = bitmap.width
    val height = bitmap.height
    val isCorrectShape = (width == 640 && height == 640)

    // 640×640の大きさにリサイズ
    if(isCorrectShape){
        println("幅＝$width、高さ＝$height")
        println("リサイズしません")
        //640×640ならそのまま返却
        return bitmap
    }
    else{
        println("幅＝$width、高さ＝$height")
        println("リサイズします")
        return Bitmap.createScaledBitmap(bitmap, 640, 640, true)
    }


}