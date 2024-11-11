package com.example.myapplicationtest

import android.content.Context
import android.graphics.Bitmap
import com.example.myapplicationtest.ml.Yolov10nFloat32
import org.tensorflow.lite.support.image.TensorImage

//TFLite関数。
fun tFLite_Yolov10n(context: Context, bitmap: Bitmap){

    val model = Yolov10nFloat32.newInstance(context)

    // Creates inputs for reference.
    val image = TensorImage.fromBitmap(bitmap)

    // 確認S-----------------------------------------------------------------------------------------
    val width = bitmap.width
    val height = bitmap.height
    val isCorrectShape = (width == 640 && height == 640)

    val config = bitmap.config
    val isRgb = (config == Bitmap.Config.ARGB_8888) // || config == Bitmap.Config.RGB_565

    val isCorrectShapeAndChannels = isCorrectShape && isRgb
    if (isCorrectShapeAndChannels) {
        println("Bitmapの形状は[1, 640, 640, 3]です。")
    } else {
        println("Bitmapの形状が異なります。")
    }

    // 確認E-----------------------------------------------------------------------------------------


    // Runs model inference and gets result.
//    val outputs = model.process(image)
//    val output = outputs.outputAsCategoryList

    // Releases model resources if no longer used.
    model.close()

}