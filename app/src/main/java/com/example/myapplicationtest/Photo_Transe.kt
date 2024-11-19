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
import java.nio.ByteBuffer


//拡張関数　//クラスを新しい機能で拡張
//Bitmapを返却する関数S------------------------------------------------------------------------------------
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
//Bitmapを返却する関数E------------------------------------------------------------------------------------

//受け取ったBitmapをARGB_8888に変換する関数S--------------------------------------------------------------
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
//受け取ったBitmapをARGB_8888に変換する関数E--------------------------------------------------------------

//受け取ったBitmapをRGB_565に変換する関数S----------------------------------------------------------------
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
// 受け取ったBitmapをRGB_565に変換する関数E----------------------------------------------------------------


// 受け取ったbitmapを640×640の大きさにリサイズする関数S---------------------------------------------------
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
// 受け取ったbitmapを640×640の大きさにリサイズする関数E---------------------------------------------------

// Bitmapを引数で受け取った大きさに変換する関数S----------------------------------------------------------
fun resizeTonxn(bitmap:Bitmap,wi:Int,he:Int):Bitmap{

    val width = bitmap.width
    val height = bitmap.height
    val isCorrectShape = (width == wi && height == he)

    // 640×640の大きさにリサイズ
    if(isCorrectShape){
        println("幅＝$width、高さ＝$height")
        println("リサイズしません")
        //640×640ならそのまま返却
        return bitmap
    }
    else{
        println("幅=$wi x 高さ=$he にリサイズ")
        return Bitmap.createScaledBitmap(bitmap, wi, he, true)
    }

}
// Bitmapを引数で受け取った大きさに変換する関数S----------------------------------------------------------


// Bitmapクラスを画像フォーマットのbitmapに変換する関数S--------------------------------------------------
fun bitmapToByteArray(bitmap: Bitmap):ByteArray {

    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()

    return byteArray
}
// Bitmapクラスを画像フォーマットのbitmapに変換する関数E--------------------------------------------------

// BitmapデータからBase64へ変換する関数S------------------------------------------------------
fun bitmapToBase64(bitmap: Bitmap):String {
    //bitmapからByteArrayに変換
    val bytearray = bitmapToByteArray(bitmap)

    // ByteArrayをBase64文字列に変換
    val base64String = Base64.encodeToString(bytearray, Base64.DEFAULT)

    return  base64String
}
// BitmapデータからBase64へ変換する関数S------------------------------------------------------

