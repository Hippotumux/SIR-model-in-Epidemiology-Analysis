# -SIR-model-in-Epidemiology-Analysis

{%hackmd @hipp0/Hippotumuxthem %}

# SIR model 

![image](https://hackmd.io/_uploads/Byc1PL2X0.png)


## 工具

- JAVAFX

## 功能

- 給使用者設定參數 (人口，傳染力，初始感染者)
- 設計一些地圖，有地圖的人口密度，跟互動性
- 跑 x 天，會有模擬圖，總人口變化圖，感染人數圖，死亡人數圖

## 架構圖

https://online.visual-paradigm.com/


## 參數
- 城市、小城市、鄉村人口
- 感染率、死亡率、痊癒率

### 生成視窗
- width 
- height


### 使用者輸入視窗
- width 
- height
- 總人口數
- 三個地圖的流通量
- 感染率
- 再感染率
- 康復率
- 死亡率
- 天數
- 三個城市初始感染者人數


### 地圖

初始各地區人口佔比（可隨機x95~105%)

- 城市人口佔比: 60%
- 小城市人口佔比: 30%
- 鄉村人口佔比: 10%


#### 地圖初始化

- 邊界（0）、道路（1）、通道（2）


### 人
#### 參數
- x位置
- y位置
- 當天移動步數
- 狀態：感染(紅圓)、未感染(綠圓)、死亡(紅叉->兩天後消失)
- 感染率、再感染率
- 死亡率
- 是否在通道內
- 目前走路的角度


#### 模擬走路

- 在通道內固定角度
在通道外隨機走路
遇到通道計算流通量，判斷是否通過
return $\theta$


#### 畫圖
- 圓形、半徑、顏色




### 數據圖

#### 參數

紀錄: 死亡人數，健康人數，康復人數，感染人數

#### 畫圖

- x軸: 天數
- y軸: 人口數

死亡人數(黑)，健康人數(綠)，康復人數(藍)，感染人數(紅)
疊在同一張圖


## Reference 

[數據圖 需要安裝](https://blog.csdn.net/starter_____/article/details/78827506)
[數據圖2 需要安裝](https://blog.csdn.net/z1696637434/article/details/134793904)

## 功能實作

```java=
System.out.println("Hello JavaFX, I am Hippotumux")
```

### 視窗
