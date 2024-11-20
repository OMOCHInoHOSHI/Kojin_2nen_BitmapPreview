package com.example.myapplicationtest

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Photo
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationtest.ui.theme.MyApplicationTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()    //スマホの端を無くす

        // ContentResolverを取得
        val content_Resolver = contentResolver
        setContent {
            MyApplicationTestTheme {
                //Surfaceは content colorを決める役割がある   //ScaffoldにSurfaceが含まれる
                Scaffold(modifier = Modifier.fillMaxWidth()) { innerPadding ->
                    val localPadding = innerPadding
//                                        Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )

//                    var str by remember { mutableStateOf("あんどろ") }
//                    Box {
//                        Greeting(
//                            str, onClick = {str = "ボタンがタップされました"},
//                            modifier = Modifier.padding(innerPadding)   //余白
//                        )
//                        IconButton(
//                            onClick = {  },
//                            modifier = Modifier.align(Alignment.TopEnd)
//                        ) {
//                            Icon(
//                                imageVector = Icons.Rounded.PhotoCamera,
//                                contentDescription = "",
//                                modifier = Modifier.size(48.dp)
//                            )
//                        }
//                    }

                    //カメラ起動S------------------------------------------------------------

                    var camera_flg by remember { mutableIntStateOf(0) }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                    {
                        LaunchedEffect(key1 = true) {

                        }
                        IconButton(
                            onClick = { camera_flg=1 },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.PhotoCamera,
                                contentDescription = "",
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                    if(camera_flg==1){
//                        CameraScreen()
                        camera_flg = CameraScreen_2(camera_flg)
//                        camera_flg=0
                    }
                    //カメラ起動E------------------------------------------------------------


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

                    if(camera_flg == 0){
                        Box(
                            modifier = Modifier
//                            .fillMaxSize()
                                .padding(16.dp)
                        ){
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
                                //Text(text = "画像を選択")
                            }
                        }
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
                    var usebitmpa by remember { mutableStateOf<Bitmap?>(null) }
                    if(uri_get != Uri.EMPTY && flg==1){
                        //取得したUriをBitmapに変換
                        val bitmap: Bitmap? = uri_get.getBitmapOrNull(contentResolver)
                        println(bitmap)

                        if(bitmap != null){


//                            val bitmapnotnull = bitmap
                            usebitmpa = bitmap
//                            bitmapnotnull.recycle()


//                            val context: Context = this
//                            val assetManager = context.assets   //asettのパス
//                            val modelPath = "yolov10n_float32.tflite"
//                            val inputStream = assetManager.open(modelPath)
//                            println(inputStream)
//                            println("もでるぱす")

                            //val recognizer = YOLOv10ImageRecognizer(context, modelPath)


                            // recognizeImageメソッドを呼び出して認識結果を取得
//                            try {
//                                val recognizer = YOLOv10ImageRecognizer(inputStream)
//                                val results = recognizer.recognizeImage(bitmapnotnull)
//                                results.forEach { result ->
//                                    println("認識結果 - X: ${result.x}, Y: ${result.y}, Width: ${result.width}, Height: ${result.height}, Confidence: ${result.confidence}")
//                                }
//                            } catch (e: Exception) {
//                                Log.e("MainActivity", "Error during image recognition", e)
//                            }

                            // 結果を出力
//                            results.forEach { result ->
//                                println("認識結果 - X: ${result.x}, Y: ${result.y}, Width: ${result.width}, Height: ${result.height}, Confidence: ${result.confidence}")
//                            }
                        }
                        //Bitmapを変換する関数呼び出し
                        // transe_Bitmap(bitmap)



                        flg=0


                    }
                    //画像ピッカー起動E------------------------------------------------------------

                    val context: Context = this
                    if (uri_get != Uri.EMPTY && camera_flg == 0){
                        var prebit = BitmapImagePreview(usebitmpa, camera_flg)
                        if(prebit != null){
                            saveBitmap(prebit, context)
                        }
                        println("再描画")
                    }



                }
            }
        }
        //python追加-------------------------------------------------------------
//        val py = getInstance()
//        val module = py.getModule("hello")
//        val txt1 = module.callAttr("hello_world")
//        val txt2 = module.callAttr("set_text", "Good Morning")
//        println(txt1)   // logに出力。Logcatに出力される
//        println(txt2)   // logに出力。Logcatに出力される
//
//        val num1 = module.callAttr("test_numpy")
//        val num2 = module.callAttr("test_pandas")
//        println(num1)
//        println(num2)

//        val tex3 = module.callAttr("hellow_yolo")
//        println(tex3)
        //追加-------------------------------------------------------------
    }
}

@Composable
fun content(
    onNothingSelected: () -> Unit
):Uri?{
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

//@Composable
//fun Greeting(name: String,
//             onClick: () -> Unit = {},
//             modifier: Modifier = Modifier) {
//
//    //ボタンコンポーザー
//    //ボタンを押すとテキストを表示S---------------------
//    Button(onClick = onClick) {
//        //テキストを表示
//        Text(
//            text = "Hello $name!",
//            modifier = modifier
//        )
//    }
//    //ボタンを押すとテキストを表示E---------------------
//}

//@Composable
//fun PhotoPicker(onPickPhoto: () -> Unit) {
//    Button(onClick = { onPickPhoto() }) {
//        Text(text = "写真を選択")
//    }
//}

@Composable
fun BitmapImagePreview(bitmap: Bitmap?,kameraflg: Int):Bitmap? {

        if(bitmap != null){

            var useBitmap by remember { mutableStateOf(bitmap) }

            var width = bitmap.width
            var height = bitmap.height

            var flg by remember { mutableStateOf(0) }

            println("nullじゃないよー")
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ){
                Image(
                    bitmap = useBitmap.asImageBitmap(),
                    contentDescription = "Bitmap Image",
                    modifier = Modifier
//                        .fillMaxSize()
//                        .align(Alignment.BottomCenter) // 下揃えに設定
                        .padding(bottom = 60.dp)           //下の余白
                        .align(Alignment.TopCenter)
                        .padding(top = 260.dp)           //上の余白


                )
                Button(
                    onClick = {
                        flg=1
                    },
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 100.dp)
                ){
                    Text("リサイズ")
                }
            }

//            var width:Int by remember { mutableStateOf(0) }
//            if (flg == 1){
//                useBitmap = resizeTonxn(useBitmap,textnum_width(width),textnum_hight(height))
//                flg = 0
//            }
//            useBitmap = resizeTonxn(useBitmap,textnum_width(width),textnum_hight(height))
            width = textnum_width(width)
            height = textnum_hight(height)

            if(flg == 1){
                useBitmap = resizeTonxn(useBitmap,width,height)
                return useBitmap
                flg=0
            }
        }
    else{
        return null
        }

    return null
}

//@Composable
//fun NumberInputScreen() {
//    var inputValue by remember { mutableStateOf("") } // 入力値を管理する状態
//    var resultValue by remember { mutableStateOf(0) } // 結果を保持する変数
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // 数字入力フィールド
//        TextField(
//            value = inputValue,
//            onValueChange = { newValue ->
//                // 入力値が数字のみの場合に更新
//                if (newValue.all { it.isDigit() }) {
//                    inputValue = newValue
//                }
//            },
//            label = { Text("数字を入力") },
//            placeholder = { Text("例: 123") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // ボタンを押したときに変数に渡す
//        Button(
//            onClick = {
//                resultValue = inputValue.toIntOrNull() ?: 0 // 入力値を整数に変換
//            }
//        ) {
//            Text("確定")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // 結果を表示
//        Text(text = "結果: $resultValue")
//    }
//}

// 幅を入力
@Composable
fun textnum_width(width:Int):Int{

    var wi by remember { mutableStateOf(width) }
    var numtext by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),

    ){
        OutlinedTextField(
            value = numtext,
            onValueChange = {
                numtext = it
                if (numtext.isNotEmpty() && numtext.all { char -> char.isDigit() }) {
                    // 数値が入力された場合のみコールバックを呼び出す
                    wi = it.toInt()
                }
            },
            label = { Text(text = "幅：640") },
            singleLine = true,  //改行無効
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),  //数値のみ
            modifier = Modifier.padding(20.dp)
                .align(Alignment.TopCenter)
                .offset(x = -90.dp, y = 150.dp)
                .width(150.dp)
//                .size(100.dp)

        )
    }

    return wi
}

//高さを入力
@Composable
fun textnum_hight(height: Int):Int{

    var he by remember { mutableStateOf(height) }
    var numtext by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        ){
        var numtext by remember { mutableStateOf("") }
        OutlinedTextField(
            value = numtext,
            onValueChange = {
                numtext = it
                if (numtext.isNotEmpty() && numtext.all { char -> char.isDigit() }) {
                    // 数値が入力された場合のみコールバックを呼び出す
                    he = it.toInt()
                }
            },
            label = { Text(text = "高さ：640") },
            singleLine = true,  //改行無効
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),  //数値のみ
            modifier = Modifier.padding(20.dp)
                .align(Alignment.TopCenter)
                .offset(x = 80.dp, y = 150.dp)
                .width(150.dp)
//                .size(100.dp)
        )
    }

    return he
}

@Composable
fun saveBitmap(bitmap: Bitmap,context: Context){
    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Button(
            onClick = {
                saveBitmapToJpeg(context, bitmap)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(x = 80.dp)
        ) {
            Text("JPG")
        }

        Button(
            onClick = {
                saveBitmapToPNG(context, bitmap)
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("PNG")
        }

        Button(
            onClick = {
                saveBitmapWebP(context, bitmap)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(x = -90.dp)

        ) {
            Text("WebP ")
        }
    }
}

//@Composable
//fun bit_Size_Button(bitmap: Bitmap)
//
//{
//    var showPreview by remember { mutableStateOf(false) }
//
//    if (bitmap != null){
//
//        val width = bitmap.width
//        val height = bitmap.height
//
//        var wi by remember { mutableStateOf(width) }
//        var he by remember { mutableStateOf(height) }
//
//        Box(modifier = Modifier.fillMaxSize()){
//            resizeTonxn(bitmap,wi,he)
//        }
//        Button(
//            onClick = {
//                showPreview = true
//            }
//        ) {
//            Text("確定")
//        }
//    }
//
//    if (showPreview) { // 状態が true の場合のみ BitmapImagePreview を表示
//        BitmapImagePreview(bitmap, 0)
//    }
//}


//プレビューのためのテストコードS----------------------------------------------------
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTestTheme {
//        var str by remember { mutableStateOf("あんどろ") }
//
////        var moji="ボタンがタップされました-"
//        var moji= stringResource(id=R.string.app_name)
//
//        Box {
//            Greeting(str, onClick = {str = moji})
//        }

//        var flg by remember { mutableIntStateOf(0) } // flg の状態を管理する
//
//        FilledTonalButton(
//            onClick = { flg = 1 },
//            modifier = Modifier
//                .size(80.dp)
//                .padding(8.dp)
//
//        ) {
//            Text(text = "カメラ起動")
//        }
//        if(flg == 1){
//            //カメラ権限呼び出し
//            CameraScreen()
//        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {
            LaunchedEffect(key1 = true) {

            }
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 80.dp)

            ) {
                Icon(
                    imageVector = Icons.Rounded.PhotoCamera,
                    contentDescription = "",
                    modifier = Modifier.size(48.dp)
                )
            }
        }


    }
}