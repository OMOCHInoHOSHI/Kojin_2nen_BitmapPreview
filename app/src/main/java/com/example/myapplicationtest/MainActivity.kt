package com.example.myapplicationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chaquo.python.Python.getInstance
import com.example.myapplicationtest.ui.theme.MyApplicationTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()    //スマホの端を無くす
        setContent {
            MyApplicationTestTheme {
                //Surfaceは content colorを決める役割がある   //ScaffoldにSurfaceが含まれる
                Scaffold(modifier = Modifier.fillMaxWidth()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )

                    var str by remember { mutableStateOf("あんどろ") }
                    Box {
                        Greeting(
                            str, onClick = {str = "ボタンがタップされました"},
                            modifier = Modifier.padding(innerPadding)   //余白
                        )
                    }

                    var flg by remember { mutableIntStateOf(0) } // flg の状態を管理する
                    FilledTonalButton(
                        onClick = { flg = 1 },
                        modifier = Modifier.size(80.dp).padding(1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.PhotoCamera, // カメラのアイコンに変更
                            contentDescription = "カメラ起動",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        //Text(text = "カメラ起動")
                    }
                    if(flg == 1){
                        //カメラ権限呼び出し
                        CameraScreen()
                    }

                }
            }
        }
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
        //追加-------------------------------------------------------------
    }
}

@Composable
fun Greeting(name: String,
             onClick: () -> Unit = {},
             modifier: Modifier = Modifier) {

    //ボタンコンポーザー
    //ボタンを押すとテキストを表示S---------------------
    Button(onClick = onClick) {
        //テキストを表示
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
    //ボタンを押すとテキストを表示E---------------------
}


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

        var flg by remember { mutableIntStateOf(0) } // flg の状態を管理する

        FilledTonalButton(
            onClick = { flg = 1 },
            modifier = Modifier.size(80.dp).padding(8.dp)

        ) {
            Text(text = "カメラ起動")
        }
        if(flg == 1){
            //カメラ権限呼び出し
            CameraScreen()
        }

    }
}