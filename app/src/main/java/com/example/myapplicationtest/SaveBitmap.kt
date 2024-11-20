package com.example.myapplicationtest

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Toast
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Locale

//val filePath = "path/to/save/image.jpg"
private val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

// BitmapをJBGに変換して保存S-----------------------------------------------------------------------------------
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
                Toast.makeText(context, "JPEGで保存", Toast.LENGTH_SHORT).show()
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
// BitmapをJBGに変換して保存E-----------------------------------------------------------------------------------

// BitmapをPNGに変換して保存S-----------------------------------------------------------------------------------
fun saveBitmapToPNG(context: Context, bitmap: Bitmap): Boolean {
    val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())

    // MediaStoreに保存するためのContentValuesを作成
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.png") // ファイル名
        put(MediaStore.MediaColumns.MIME_TYPE, "image/png") // MIMEタイプ
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
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream) // JPEG形式で保存
                outputStream.flush()

                // 保存に成功した場合は、UIスレッドでToastを表示
                Toast.makeText(context, "PNGで保存", Toast.LENGTH_SHORT).show()
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
// BitmapをPNGに変換して保存E-----------------------------------------------------------------------------------

// BitmapをWebPに変換して保存S-----------------------------------------------------------------------------------
fun saveBitmapWebP (context: Context, bitmap: Bitmap): Boolean {
    val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())

    // MediaStoreに保存するためのContentValuesを作成
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.webp") // ファイル名
        put(MediaStore.MediaColumns.MIME_TYPE, "image/webp") // MIMEタイプ
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
                bitmap.compress(Bitmap.CompressFormat.WEBP, 100, outputStream) // JPEG形式で保存
                outputStream.flush()

                // 保存に成功した場合は、UIスレッドでToastを表示
                Toast.makeText(context, "WebPで保存", Toast.LENGTH_SHORT).show()
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
// BitmapをWebPに変換して保存E-----------------------------------------------------------------------------------
