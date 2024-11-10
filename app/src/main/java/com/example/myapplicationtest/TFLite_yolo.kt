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

    // Runs model inference and gets result.
    val outputs = model.process(image)
    val output = outputs.outputAsCategoryList

    // Releases model resources if no longer used.
    model.close()

}