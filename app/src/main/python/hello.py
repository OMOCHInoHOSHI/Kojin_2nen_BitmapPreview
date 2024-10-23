import numpy as np # 追加
import pandas as pd # 追加

def hello_world():
    return "Hello takotako"

def set_text(txt):
    return txt

# ------ 以下の記述を追加 ------
def test_numpy():
    return np.array([1,2,3,4,5])

# ------ 以下の記述を追加 ------
def test_pandas():
    return pd.Series([1,2,3,4,5])

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

def hellow_yolo():
    return "yolo"