import numpy as np # 追加
import pandas as pd # 追加
import os
from ultralytics import YOLO
import base64
from PIL import Image
import tensorflow as tf
import io

# import cv2
# import sys
# import platform

# YOLOモデルの場所
current_dir = os.path.dirname(os.path.abspath(__file__))
# file_path = os.path.join(current_dir, "yolov10s.pt")
file_path = os.path.join(current_dir, "yolov10n_float32.tflite")
model = None

# モデルをロードする関数S--------------------------------------------------------
def model_Rode():
    # current_dir = os.path.dirname(os.path.abspath(__file__))
    # file_path = os.path.join(current_dir, "yolov10s.pt")

    global file_path
    global model

    # YOLOモデルをロードする
    if os.path.exists(file_path):
        # return "yolov10s.pt発見"
        try:
            model = YOLO(file_path)
            return "YOLOモデルをロード"
        except Exception as e:
            return f"モデルのロードに失敗しました: {e}"
    else:
        return "yolov10s.ptが見つかりません"
# モデルをロードする関数E--------------------------------------------------------

# bitmapの型を確認する関数Ｓ--------------------------------------------------------
def bit_rode(c_bitmap):
    return f"{type(c_bitmap)}"
# bitmapの型を確認する関数E--------------------------------------------------------

# Base64文字列をデコードしてNumPy配列に変換する関数S-----------------------------------
def base64_to_image(base64_string):
    image_data = base64.b64decode(base64_string)
    image = Image.open(io.BytesIO(image_data))
    return np.array(image)
# Base64文字列をデコードしてNumPy配列に変換する関数E-----------------------------------

# YOLOv10で画像認識する関数S-------------------------------------------------------
def run_yolo_on_base64(base64_strings):
    results = []

    for base64_string in base64_strings:
        # Base64文字列を画像に変換
        image_np = base64_to_image(base64_string)

        # 入力サイズにリサイズ（YOLOv10用）
        input_shape = input_details[0]['shape'][1:3]
        resized_image = tf.image.resize(image_np, input_shape)
        resized_image = resized_image / 255.0  # 正規化
        input_data = np.expand_dims(resized_image, axis=0).astype(np.float32)

        # モデル推論
        interpreter.set_tensor(input_details[0]['index'], input_data)
        interpreter.invoke()

        # 出力データを取得
        output_data = interpreter.get_tensor(output_details[0]['index'])
        results.append(output_data)

    return results
# YOLOv10で画像認識する関数E-------------------------------------------------------







# def bitmap_trance(c_bitmap):
#     try:
#         results = model(c_bitmap)
#         num_objects = len(results[0].boxes)
#         return num_objects
#     except Exception as e:
#         return f"Error processing image: {e}" # Or handle the error in a more appropriate way

# def file_check():
#     dir = os.path.dirname(os.path.abspath(__file__))
#     return os.listdir(dir)


def hellow_model():
    # current_dir = os.getcwd()
    current_dir = os.path.abspath("hello.py")
    return current_dir

def hello_world():
    # return sys.version
    return "Hello takotako"

def set_text(txt):
    return txt

# ------ 以下の記述を追加 ------
def test_numpy():
    return np.array([1,2,3,4,5])

# ------ 以下の記述を追加 ------
def test_pandas():
    return pd.Series([1,2,3,4,5])

def hellow_yolo():
    return "yolo"

# # -*- coding: utf-8 -*-
#
# from ultralytics import YOLO
#
# # モデルのロード (例: YOLOv10-M)
# model = YOLO("yolov10s.pt")
#
# # 画像の予測
# results = model("387.jpg")
# #results[0].show()
# # 検出された人間の数の表示
# #print("people:", len(results[0].boxes))
#
# # 結果の保存
# # results[0].save()
#
# def people_nom():
#     #return ("people:", len(results[0].boxes))
#     return len(results[0].boxes)
#
#
# print(people_nom())

# # -*- coding: utf-8 -*-
# from ultralytics import YOLO
# import numpy as np
# import cv2
# import base64
# from io import BytesIO
# from PIL import Image

# # YOLOモデルのロード
# model = YOLO("yolov10s.pt")

# def people_count_and_image(image_bytes):
#     # ByteArrayから画像データを読み込む
#     nparr = np.frombuffer(image_bytes, np.uint8)
#     img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    
#     # 画像の予測
#     results = model(img)
    
#     # 検出された人の数を取得
#     people_count = sum(1 for box in results[0].boxes if box.cls == 0)  # クラス0が「人」を意味すると仮定

#     # 検出結果を画像に描画
#     annotated_image = results[0].plot()  # YOLOが提供する描画機能で注釈付き画像を生成

#     # 画像をJPEG形式のバイト配列にエンコード
#     _, buffer = cv2.imencode('.jpg', annotated_image)
#     result_image_bytes = base64.b64encode(buffer).decode('utf-8')

#     # 人数と結果画像を返す
#     return people_count, result_image_bytes
