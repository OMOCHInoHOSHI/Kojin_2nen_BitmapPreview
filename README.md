[![Android](https://custom-icon-badges.herokuapp.com/badge/Android-3CDA84.svg?logo=android&logoColor=white)]()
[![Gradle](https://custom-icon-badges.herokuapp.com/badge/Gradle-02303a.svg?logo=Gradle&logoColor=white)]()
[![Kotlin](https://custom-icon-badges.herokuapp.com/badge/Kotlin-A97BFF.svg?logo=Kotlin&logoColor=white)]()
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)
<img src="https://img.shields.io/badge/-JetPackCompese-FFCC33.svg?logo=android&style=popout">


# BitmapPreview

## 開発環境
 - **IDE：** Android Studio Koala Feature Drop
 - **言語：** Kotlin
 - **バックエンド：** Python

### 動作環境
 - API34、35

その他バージョンは未確認。画像保存関係のパーミッション関係が不完全?
 
 - 最低API26
  
## 目的
 - 2年次個人製作
 - AndroidStudioの練習


## ブランチ説明
 - **main** 安定板
 - **front** 画面設計　画像加工
 - **front_hyouji** frontの実験用
 - **tensorflowlite** TFLiteを使おうとした(途中)
 - **yolo** Pythonと繋げてYOLOを使おうとした(途中)


### 機能
 - 共通   
画像を選択、カメラでの撮影
 - front 
選択した画像の加工
 - tensorflowlite、yolo  
バグあり、画像認識(動作しない)


## 現在の動作
main、front
### カメラ
 - 右上のボタンでカメラを起動
 - 撮影（jepg保存＆シェア）
### 画像加工
 - 左上のボタンで画像ピッカーを起動
 - 選択した画像を表示
 - 画像のリサイズ
 - 画像の回転
 - 画像の保存（トグルボタンでシェアのオンオフ）

## 動作方法
- mainのコピー
- SDKは各々のSDKを使用する
