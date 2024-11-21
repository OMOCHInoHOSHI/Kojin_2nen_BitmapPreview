package com.example.myapplicationtest

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Locale

//val filePath = "path/to/save/image.jpg"
private val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

// BitmapをJPEGに変換して保存S-----------------------------------------------------------------------------------
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

// 共有関数S----------------------------------
private fun shareImage(context: Context, uri: Uri) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "image/*"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(shareIntent, "画像を共有"))
}
// 共有関数E---------------------------------

// シェア機能搭載JPEG保存関数S------------------------------------------------------------------------
fun saveBitmapToJpeg(
    context: Context,
    bitmap: Bitmap,
    shouldShare: Boolean = false
): Boolean {
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
            resolver.openOutputStream(validUri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()

                // 保存成功メッセージ
                Toast.makeText(context, "JPEGで保存しました", Toast.LENGTH_SHORT).show()

                // 保存フラグがtrueの場合にシェア
                if (shouldShare) {
                    shareImage(context, validUri)
                }
                return true
            }
        } ?: run {
            // URIがnullの場合
            Toast.makeText(context, "画像の保存に失敗しました", Toast.LENGTH_SHORT).show()
            return false
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "保存中にエラーが発生しました", Toast.LENGTH_SHORT).show()
        return false
    }
}
// シェア機能搭載JPEG保存関数E------------------------------------------------------------------------

// シェア機能搭載PNG保存関数S------------------------------------------------------------------------
fun saveBitmapToPNG(
    context: Context,
    bitmap: Bitmap,
    shouldShare: Boolean = false
): Boolean {
    val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())

    // MediaStoreに保存するためのContentValuesを作成
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.png") // ファイル名
        put(MediaStore.MediaColumns.MIME_TYPE, "image/PNG") // MIMEタイプ
        put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/Save_Image") // 保存先フォルダ
    }

    // MediaStoreに画像を挿入
    val resolver = context.contentResolver

    try {
        // メディアストレージに画像を挿入
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let { validUri ->
            resolver.openOutputStream(validUri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()

                // 保存成功メッセージ
                Toast.makeText(context, "PNGで保存しました", Toast.LENGTH_SHORT).show()

                // 保存フラグがtrueの場合にシェア
                if (shouldShare) {
                    shareImage(context, validUri)
                }
                return true
            }
        } ?: run {
            // URIがnullの場合
            Toast.makeText(context, "画像の保存に失敗しました", Toast.LENGTH_SHORT).show()
            return false
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "保存中にエラーが発生しました", Toast.LENGTH_SHORT).show()
        return false
    }
}
// シェア機能搭載PNG保存関数E------------------------------------------------------------------------

// シェア機能搭載WebP保存関数S------------------------------------------------------------------------
fun saveBitmapWebP(
    context: Context,
    bitmap: Bitmap,
    shouldShare: Boolean = false
): Boolean {
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
            resolver.openOutputStream(validUri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.WEBP, 100, outputStream)
                outputStream.flush()

                // 保存成功メッセージ
                Toast.makeText(context, "WebPで保存しました", Toast.LENGTH_SHORT).show()

                // 保存フラグがtrueの場合にシェア
                if (shouldShare) {
                    shareImage(context, validUri)
                }
                return true
            }
        } ?: run {
            // URIがnullの場合
            Toast.makeText(context, "画像の保存に失敗しました", Toast.LENGTH_SHORT).show()
            return false
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "保存中にエラーが発生しました", Toast.LENGTH_SHORT).show()
        return false
    }
}
// シェア機能搭載WebP保存関数E------------------------------------------------------------------------
