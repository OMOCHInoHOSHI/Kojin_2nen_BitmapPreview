import numpy as np # 追加
import pandas as pd # 追加
import os
from ultralytics import YOLO
# import sys
# import platform

import base64
from PIL import Image
import tensorflow as tf
import io

import subprocess

current_dir = os.path.dirname(os.path.abspath(__file__))
file_path = os.path.join(current_dir, "yolov10n.pt")
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

def base64_to_image(base64_string):
    image_data = base64.b64decode(base64_string)
    image = Image.open(io.BytesIO(image_data))
    # image.save("output.jpg", "JPEG")
    return np.array(image)
    # return  image

# 人数を取得する関数S---------------------------------------------------------------
def run_yolo_on_base64(base64_string):

    global model

    try:
        # Base64文字列を画像に変換
        image_jpg = base64_to_image(base64_string)
        # return f"{type(image_jpg)}"

        try:

            results = model(image_jpg)
            return f"{results}"
            # 結果が空の場合のチェック
            # if len(results) == 0 or not results[0].boxes:
            # if len(results[0].boxes)==0:
            #     return f"検出された物体はありません{type(results[0])}"
            # else:
            #     # return f"検出 {type(results[0])}"
            #     return "era--"
            # 検出された物体の数をカウント
            # object_count = len(results[0].boxes)
            # return f"検出された物体の数: {object_count}"
            # # 推論結果
            # if not hasattr(results[0], 'boxes'):
            #     return "検出結果に 'boxes' 属性がありません"
            # people_count = len(results[0].boxes)
            # return f"検出された人数: {people_count}"
        except Exception as e:
            # return f"resultエラー: {e}"
            return f"えら{results}"

        # return type(results)
        # 人の数をカウント
        # people_count = 0
        # return people_count



        # for *xyxy, conf, cls in results[0].xyxy[0]:
        #     if int(cls) == 0:  # クラスが0（人）の場合
        #         people_count += 1

        # try:
        #     people_count = len(results[0].boxes)
        #
        #     return f"人数{people_count}"  # 検出結果と人数を返す
        # except Exception as e:
        #     return f"エラー{e}"

    except Exception as e:
        return f"検出エラー: {e}"
        # return "えら-"
# 人数を取得する関数E---------------------------------------------------------------


def file_check():
    dir = os.path.dirname(os.path.abspath(__file__))
    return os.listdir(dir)


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