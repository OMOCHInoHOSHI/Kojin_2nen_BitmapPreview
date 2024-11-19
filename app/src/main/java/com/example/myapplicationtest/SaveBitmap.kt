package com.example.myapplicationtest

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale

//val filePath = "path/to/save/image.jpg"
private val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"


//fun saveBitmapAsJpeg(bitmap: Bitmap): Boolean {
//
//    val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
//    var fileOutputStream: FileOutputStream? = null
//
//    val contentValues = ContentValues().apply {
//        put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg") // ファイル名
//        put(android.provider.MediaStore.MediaColumns.MIME_TYPE, "image/jpeg") // MIMEタイプ
//        put(
//            android.provider.MediaStore.MediaColumns.RELATIVE_PATH,
//            "Pictures/Save_Image"
//        ) // 保存先フォルダ
//    }
//
//}

fun saveBitmapToJpeg(context: Context, bitmap: Bitmap): Boolean {
    val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())

    // MediaStoreに保存するためのContentValuesを作成
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg") // ファイル名
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg") // MIMEタイプ
        put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/Save_Image") // 保存先フォルダ
    }

    // MediaStoreに画像を挿入
    val resolver = context.contentResolver

    try {
        // メディアストレージに画像を挿入
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let { validUri ->
            // OutputStreamを取得してBitmapをJPEG形式で書き込む
            resolver.openOutputStream(validUri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream) // JPEG形式で保存
                outputStream.flush()

                // 保存に成功した場合は、UIスレッドでToastを表示
                Toast.makeText(context, "Image saved successfully", Toast.LENGTH_SHORT).show()
                return true
            }
        } ?: run {
            // URIがnullの場合、保存に失敗
            Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
            return false
        }
    } catch (e: IOException) {
        e.printStackTrace()
        // エラーが発生した場合
        Toast.makeText(context, "Error saving image", Toast.LENGTH_SHORT).show()
        return false
    }
}
