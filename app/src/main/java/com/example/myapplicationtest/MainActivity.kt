package com.example.myapplicationtest

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chaquo.python.Python.*
import com.example.myapplicationtest.ui.theme.MyApplicationTestTheme
import androidx.compose.material.icons.rounded.Photo
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Base64
//import java.util.Base64
import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.Composable
//import androidx.compose.ui.text.LinkAnnotation
import com.chaquo.python.Python
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {

            //python追加-------------------------------------------------------------
            val py = getInstance()
            val module = py.getModule("hello")
            val txt1 = module.callAttr("hello_world")
            val txt2 = module.callAttr("set_text", "Good Morning")
            println(txt1)   // logに出力。Logcatに出力される
            println(txt2)   // logに出力。Logcatに出力される

            val num1 = module.callAttr("test_numpy")
            val num2 = module.callAttr("test_pandas")
            println(num1)
            println(num2)

            // モデルをロードする
            val modelerode = module.callAttr("model_Rode")
            println(modelerode)


            //追加-------------------------------------------------------------
            MyApplicationTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                //画像ピッカー起動S------------------------------------------------------------
                var flg by remember { mutableIntStateOf(0) } // flg の状態を管理する
                //uri_getに取得したuriを格納する
                var uri_get by remember { mutableStateOf(Uri.EMPTY) }
                //確認
                if(uri_get != Uri.EMPTY){
                    println("初期URIがヌルではない")
                }
                else{
                    println("初期URIがヌル")
                }

                FilledTonalButton(
                    onClick = { flg += 1 },
                    modifier = Modifier
                        .size(80.dp)
                        .padding(1.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Photo, // フォルダアイコンに変更
                        contentDescription = "",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    //Text(text = "カメラ起動")
                }
                if(flg == 1){
                    //フォルダから写真を選択するS---------------------------------------------------------
                    uri_get=
                        content(
                            //何も選択しない場合
                            onNothingSelected = {
                                // Handle nothing selected, e.g., show a message or log an event
                                Log.d("MainActivity", "No image selected")
                                flg=0
                            }
                        )
                    //フォルダから写真を選択するE---------------------------------------------------------

                }
                //画像のuriが取得できた場合
                if((uri_get != Uri.EMPTY) && flg==1){

                    //取得したUriをBitmapに変換
                    val bitmap: Bitmap? = uri_get.getBitmapOrNull(contentResolver)
                    println("uriのbitmap$bitmap")

                    if(bitmap != null){

                        val bitmapnotnull = bitmap  //nllでないbitmap
                        println("nullじゃないbitmap$bitmapnotnull")
                        val context: Context = this
                        val assetManager = context.assets   //asettのパス

                        //bitmapのサイズをリサイズする
                        var use_bitmap  = resizeTo640x640(bitmapnotnull)

                        //bitmap RGB_565に変換
                        use_bitmap  = convertToRGB_565(use_bitmap)



                        // BitmapデータからBase64へ変換
                        val base64_array = bitmapToBase64(use_bitmap)

                        // 型確認
                        val C_bit = module.callAttr("bit_rode",base64_array)
                        println(C_bit)


                        // 結果
                        val result = module.callAttr("run_yolo_on_base64",base64_array)
                        println(result)



                        // base64の画像をpythonに渡して変換

                    }


                    flg=0
                }
                //画像ピッカー起動E------------------------------------------------------------
            }
        }

    }
}

@Composable
fun content(
    onNothingSelected: () -> Unit
): Uri?{
    //初期値に空のURI
    var pickedImageUri by remember { mutableStateOf(Uri.EMPTY) }
    val launcher = rememberLauncherForActivityResult(
        //像選択のインテントを起動するための ActivityResultLauncher を作成
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->    //Uri?はヌル許容型
        uri?.let {
            //pickedImageUriに選択された画像のuriを代入
            pickedImageUri = it
            Log.d("MainActivity", "image selected")
            println("URIゲット")
        } ?: onNothingSelected()    //uriがnull
    }
    //ライフサイクル   //Composeが最初に組み込まれたときに実行
    LaunchedEffect(true) {
        //1つの画像を選択
        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
    return pickedImageUri
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

////拡張関数　//クラスを新しい機能で拡張
////Bitmapを返却する関数S------------------------------------------------------------------------------------
//fun Uri.getBitmapOrNull(contentResolver: ContentResolver): Bitmap? {
//    return kotlin.runCatching {
//        //Build.VERSION.SDK_INTでAPIレベルを取得   //Build.VERSION_CODES.QはAPIレベル29(Android10)
//        //現在のAndroidのバージョンがAndroid 10以上かどうか
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            val source = ImageDecoder.createSource(contentResolver, this)
//            ImageDecoder.decodeBitmap(source)
//        } else {
//            MediaStore.Images.Media.getBitmap(contentResolver, this)
//        }
//    }.getOrNull()
//}
////Bitmapを返却する関数E------------------------------------------------------------------------------------
//
////受け取ったBitmapをARGB_8888に変換する関数S--------------------------------------------------------------
//fun convertToARGB8888(bitmap: Bitmap): Bitmap {
//    //ARGB_8888ではないなら変換
//    if (bitmap.config != Bitmap.Config.ARGB_8888) {
//        println("ARGB_8888に変換")
//
//        //ARGB_8888で初期化して生成
//        val newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false)
//
//        //元のBitmapは解放
//        bitmap.recycle()
//
//        return newBitmap
//    } else {
//        println("ARGB_8888でした")
//        return bitmap
//    }
//}
////受け取ったBitmapをARGB_8888に変換する関数E--------------------------------------------------------------
//
////受け取ったBitmapをRGB_565に変換する関数S----------------------------------------------------------------
//fun convertToRGB_565(bitmap: Bitmap): Bitmap {
//    //ARGB_8888ではないなら変換
//    if (bitmap.config != Bitmap.Config.RGB_565) {
//        println("RGB_565に変換")
//
//        //ARGB_8888で初期化して生成
//        val newBitmap = bitmap.copy(Bitmap.Config.RGB_565, false)
//
//        //元のBitmapは解放
//        bitmap.recycle()
//
//        return newBitmap
//    } else {
//        println("RGB_565でした")
//        return bitmap
//    }
//}
//// 受け取ったBitmapをRGB_565に変換する関数E----------------------------------------------------------------
//
//// 受け取ったbitmapを640×640の大きさにリサイズする関数S---------------------------------------------------
//fun resizeTo640x640(bitmap: Bitmap): Bitmap{
//
//    val width = bitmap.width
//    val height = bitmap.height
//    val isCorrectShape = (width == 640 && height == 640)
//
//    // 640×640の大きさにリサイズ
//    if(isCorrectShape){
//        println("幅＝$width、高さ＝$height")
//        println("リサイズしません")
//        //640×640ならそのまま返却
//        return bitmap
//    }
//    else{
//        println("幅＝$width、高さ＝$height")
//        println("リサイズします")
//        return Bitmap.createScaledBitmap(bitmap, 640, 640, true)
//    }
//}
//// 受け取ったbitmapを640×640の大きさにリサイズする関数E---------------------------------------------------
//
//// Bitmapクラスを画像フォーマットのbitmapに変換する関数S--------------------------------------------------
//fun bitmapToByteArray(bitmap: Bitmap):ByteArray {
//
//    val byteArrayOutputStream = ByteArrayOutputStream()
//    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
//    val byteArray = byteArrayOutputStream.toByteArray()
//
//    return byteArray
//}
//// Bitmapクラスを画像フォーマットのbitmapに変換する関数E--------------------------------------------------
//
//// BitmapデータからBase64へ変換する関数S------------------------------------------------------
//fun bitmapToBase64(bitmap: Bitmap):String {
//    //bitmapからByteArrayに変換
//    val bytearray = bitmapToByteArray(bitmap)
//
//    // ByteArrayをBase64文字列に変換
//    val base64String = Base64.encodeToString(bytearray, Base64.DEFAULT)
//    Base64.encodeToString(bytearray, Base64.DEFAULT)
//
//    return  base64String
//}
//// BitmapデータからBase64へ変換する関数S------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTestTheme {
        Greeting("Android")
    }
}