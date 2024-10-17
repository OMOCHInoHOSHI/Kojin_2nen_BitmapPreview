plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    //
    id("com.chaquo.python")//追加
}

android {
    namespace = "com.example.myapplicationtest"
    compileSdk = 34
    //
    flavorDimensions += "pyVersyon"//追加

    defaultConfig {
        //
        ndk{
            abiFilters += listOf("arm64-v8a","x86_64")
        }//追加
        applicationId = "com.example.myapplicationtest"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

//pythonで追加S-------------------------------------------------------------------------------
chaquopy{
    defaultConfig {
        //buildPython("C:/Python/python3")
        //buildPython("C:/Users/tamago/AppData/Local/Microsoft/WindowsApps/python3")
        //インストールしたPythonのパス

        pip {
            // numpyをインストールする
            install("numpy")
            // pandasをインストールする
            install("pandas")
            /* requirements.txtを指定することも可能
            install("-r", "requirements.txt")
            */
        }
    }
    productFlavors{}
    sourceSets{}
}
//pythonで追加E-------------------------------------------------------------------------------


dependencies {
    //カメラライブラリS---------------------------------------------------------
    val cameraxVersion = "1.3.4"    //変数
    implementation("androidx.camera:camera-core:${cameraxVersion}")
    implementation("androidx.camera:camera-camera2:${cameraxVersion}")
    implementation("androidx.camera:camera-lifecycle:${cameraxVersion}")
    implementation("androidx.camera:camera-video:${cameraxVersion}")
    implementation("androidx.camera:camera-view:${cameraxVersion}")
    implementation("androidx.camera:camera-extensions:${cameraxVersion}")
    //カメラライブラリE---------------------------------------------------------

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}