package com.example.myapplicationtest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.io.File
import java.io.InputStream
import java.io.OutputStream

class PhotoPickerHelper(private val activity: Activity, private val requestCode: Int) {

    fun selectPhoto() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        activity.startActivityForResult(intent, requestCode)
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?, onPhotoSaved: (File?) -> Unit) {
        if (requestCode == this.requestCode && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            imageUri?.let {
                val tempFile = saveUriToTempFile(activity, it)
                onPhotoSaved(tempFile)
            } ?: run {
                Log.e("PhotoPickerHelper", "Photo selection failed or was cancelled.")
                onPhotoSaved(null)
            }
        }
    }

    private fun saveUriToTempFile(context: Context, uri: Uri): File? {
        return try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val tempFile = File.createTempFile("selected_photo", ".jpg", context.cacheDir)
            val outputStream: OutputStream = tempFile.outputStream()

            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()

            tempFile
        } catch (e: Exception) {
            Log.e("PhotoPickerHelper", "Error saving file: ${e.message}")
            null
        }
    }
}

