package com.example.myapplicationtest

import android.widget.Button
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Camera
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.w3c.dom.Text

//TakePhotoコンポーザはボタンのUIを表示する
@Composable
fun TakePhoto(takePhoto:() -> Unit){
    //takePhotoは、ラムダの引数を受け取る
    Button(onClick = takePhoto) {
        Text(text = "撮影")
        Icon(
            imageVector = Icons.Rounded.Camera, // カメラのアイコンに変更
            contentDescription = "カメラ起動",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
}