package com.example.myapplicationtest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun PermissionHand(
    onGranted: (Boolean) -> Unit    // onGrantedがTrueならカメラとストレージの許可
) {
    val context = LocalContext.current

    // ランチャーの作成: 必要に応じて複数パーミッションのリクエスト
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        // 許可状態を確認
        val allGranted = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            permissions[Manifest.permission.CAMERA] == true &&
                    permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
        } else {
            permissions[Manifest.permission.CAMERA] == true
        }
        onGranted(allGranted)
    }

    // カメラの権限確認
    val cameraGranted = ContextCompat.checkSelfPermission(
        context, Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    // ストレージの権限確認（API 33未満のみ）
    val storageGranted = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            context, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }

    // パーミッションの状態を確認
    if (cameraGranted && storageGranted) {
        onGranted(true)  // すでに許可されている
    } else {
        // リクエストするパーミッションを API レベルに応じて変更
        SideEffect {
            launcher.launch(
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                } else {
                    arrayOf(
                        Manifest.permission.CAMERA
                    )
                }
            )
        }
    }
}