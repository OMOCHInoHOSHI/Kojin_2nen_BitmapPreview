package com.example.myapplicationtest

import android.widget.Button
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.w3c.dom.Text

//TakePhotoコンポーザはボタンのUIを表示する
@Composable
fun TakePhoto(takePhoto:() -> Unit){
    //takePhotoは、ラムダの引数を受け取る
    Button(onClick = takePhoto) {
        Text(text = "撮影")
    }
}