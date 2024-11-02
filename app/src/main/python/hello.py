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
