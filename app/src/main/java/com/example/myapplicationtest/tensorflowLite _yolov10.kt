package com.example.myapplicationtest

import android.R.attr.height
import android.R.attr.width
import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class YOLOv10ImageRecognizer(inputStream: InputStream) {

    // TensorFlow Liteモデルのインタープリターを読み込む
    private val interpreter: Interpreter by lazy {
        val model = loadModelFile(inputStream)
        Interpreter(model)
    }

    // モデルファイルを ByteBuffer として読み込む
    private fun loadModelFile(inputStream: InputStream): ByteBuffer {
        val modelBytes = inputStream.readBytes()
        return ByteBuffer.allocateDirect(modelBytes.size).apply {
            order(ByteOrder.nativeOrder())
            put(modelBytes)
            rewind()  // 読み込みポインタを初期位置に戻す
        }
    }

    // 画像認識関数
    fun recognizeImage(bitmap: Bitmap): List<RecognitionResult> {
        // 入力サイズにリサイズ (640x640)
        val inputWidth = 640
        val inputHeight = 640
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, inputWidth, inputHeight, true)

        // ビットマップをByteBufferに変換
        val inputBuffer = convertBitmapToByteBuffer(resizedBitmap, inputWidth, inputHeight)

        // モデルの出力バッファを用意
        val outputBuffer = Array(1) { Array(80) { FloatArray(4) } }  // 出力形式はモデルによって異なるため確認

        // 推論を実行
        interpreter.run(inputBuffer, outputBuffer)

        // 結果を整形して返す
        return processOutput(outputBuffer)
    }

    // ビットマップをByteBufferに変換
    private fun convertBitmapToByteBuffer(bitmap: Bitmap, width: Int, height: Int): ByteBuffer {
        val inputBuffer = ByteBuffer.allocateDirect(4 * width * height * 3)  // RGBの3チャンネル * 4バイト
        inputBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(width * height)
        bitmap.getPixels(intValues, 0, width, 0, 0, width, height)

        intValues.forEach { pixel ->
            inputBuffer.putFloat(((pixel shr 16) and 0xFF) / 255f)  // Red
            inputBuffer.putFloat(((pixel shr 8) and 0xFF) / 255f)   // Green
            inputBuffer.putFloat((pixel and 0xFF) / 255f)           // Blue

        }
        return inputBuffer
    }

    // 出力を解析する関数
    private fun processOutput(outputBuffer: Array<Array<FloatArray>>): List<RecognitionResult> {
        val results = mutableListOf<RecognitionResult>()
        for (i in outputBuffer[0].indices) {
            val confidence = outputBuffer[0][i][2]
            if (confidence > 0.5) { // 閾値を設定
                val x = outputBuffer[0][i][0]
                val y = outputBuffer[0][i][1]
                val width = outputBuffer[0][i][2]
                val height = outputBuffer[0][i][3]
                results.add(RecognitionResult(x, y, width, height, confidence))
            }
        }
        return results
    }

    // 認識結果を保持するデータクラス
    data class RecognitionResult(
        val x: Float,
        val y: Float,
        val width: Float,
        val height: Float,
        val confidence: Float
    )
}

