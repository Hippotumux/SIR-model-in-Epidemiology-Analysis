# SIR model 

## 介紹

SIR模型（SIR Model）是一個用於模擬傳染病在群體中傳播的數學模型。它的名稱來自於三個變數：易感者（Susceptible, S）、感染者（Infectious, I）和康復者（Recovered, R）。這個模型首次由Kermack和McKendrick在1927年提出，是流行病學中最基本的模型之一。

### 概念

- 易感者（S）：
這是指在某一特定時刻，尚未被感染但有可能會被感染的個體數量。
- 感染者（I）：
這是指在某一特定時刻，已被感染並能夠傳播疾病的個體數量。
- 康復者（R）：
這是指在某一特定時刻，已經從疾病中康復的數量。


### 模型假設

- 總人口固定(不會移出移入): 即$N = S(t) + I(t) + R(t)$ 是定值。
- 接觸機率固定: 假設每個個體與其他個體間接觸的機率相同
- 免疫性: 康復者不會二次感染

### 數學表示

SIR 模型可以使用 ODE 來描述，方程如下:

$$
\begin{equation}
\begin{cases}
\frac{dS}{dt} = -\beta S I \\
\frac{dI}{dt} = \beta S I - \gamma I \\
\frac{dR}{dt} = \gamma I
\end{cases}
\end{equation}
$$

其中
- $\beta$ 是傳染率: 表示易感者和感染者接觸時的感染率
- $\gamma$ 是康復率: 表示感染者恢復轉變成康復者的速率

### 特徵


- 再生數: $R_0 = \frac{\beta}{\gamma}$ : 如果 $R_0 > 1$ 代表疾病會在群體中擴散，反之如果 $R_0 < 1$ 疾病會逐漸消失
- 疾病階段
    - 初期: 感染者數量急速上升
    - 高峰: 感染者達到高峰後開始下降
    - 穩定階段: 感染者數量下降到 0，大部分人成為康復者


![image](https://hackmd.io/_uploads/rJ_1JOF40.png)


### 應用

SIR 模型可以用來研究各種傳染病的傳播，例如 COVID-19，有助於理解疾病的流行特徵，評估疫苗、防疫政策的效果，並且預測疾病爆發趨勢和規模。


## 目標

我們想要模擬並拓展 SIR 模型來探討傳染病在不同條件下的傳播動態，也就是說傳統的 SIR 模型只有三種人: 易感者、感染者和康復者，很明顯現實世界中並不僅只有這三種，為了更真實地反映傳染病在現實世界中的傳播動態，我們將在基本的 SIR 模型基礎上引入更多變數和參數。具體來說，我們將考慮以下擴展：

- 死亡率 (Mortality rate): 現實中，一些感染者可能會因為疾病嚴重而死亡，這群人不再轉為康復者，而是變成了死亡者。
- 再次確診率 (Reinfection Rate): 某些疾病可能不會產生持久的免疫效果，意味著康復者在一段時間後可能再次成為易感者。

### 數學表示

$$
\begin{cases}
\frac{dS}{dt} = -\beta S I + \delta R \\
\frac{dI}{dt} = \beta S I - \gamma I - \mu I \\
\frac{dR}{dt} = \gamma I - \delta R \\
\frac{dD}{dt} = \mu I
\end{cases}
$$

其中：
 $\mu$ 表示感染者的死亡率，$\delta$ 表示康復者再次變為易感者的速率。

這是個非常難解決的微分方程，於是我們想透過模擬的方式或者說數值方式解決。

## 架構圖

![截圖 2024-06-05 下午4.44.25](https://hackmd.io/_uploads/H1uOHj6N0.png)

## 模擬方式

### 工具

- JAVA (JDE 19)
- JAVAFX (22)

### 設計

- 給使用者設定參數 (總人口、傳染率、再感染率、康復率、模擬天數、初始三區域感染人數)
- 三種地區 (城市、小城市、鄉村)
- 模擬每天的人口分布 (健康者、感染者)
- 跑數據圖顯示 (總人口數、未感染者數、康復者數、感染者數)

### 使用者介面

![image](https://hackmd.io/_uploads/HyQq4oa4A.png)

### 模擬人口地圖介面

![image](https://hackmd.io/_uploads/H1Perj6VA.png)


### 數據圖介面

![image](https://hackmd.io/_uploads/BJ44jGPVR.png)

## Class_Parameter 

定義共用參數

1. 總人口數
2. 感染率（%）
3. 再感染率（%）
4. 康復率（%）
5. 死亡率（%）
6. 模擬天數
7. 城市的初始感染人數
8. 郊區的初始感染人數
9. 鄉村的初始感染人數
10. 移動步數

### 參數
`private int`
1. 總人口數
2. 感染率（%）
3. 再感染率（%）
4. 康復率（%）
5. 死亡率（%）
6. 模擬天數
7. 城市的初始感染人數
8. 郊區的初始感染人數
9. 鄉村的初始感染人數
10. 移動步數

### 方法

1. `public Parameter(int total_population, int infection_rate, int reinfection_rate,int recovery_rate, int mortality_rate, int moving_times)`：建構子，初始化物件的各個變數
2. 各變數的 Getter 和 Setter 
            

## Class_UI

定義輸入介面，給使用者設定可控參數。

**需要輸入的參數**

1. 總人口數
2. 感染率（%）
3. 再感染率（%）
4. 康復率（%）
5. 死亡率（%）
6. 模擬天數
7. 城市的初始感染人數
8. 郊區的初始感染人數
9. 鄉村的初始感染人數


### 變數

1. 各參數的標籤 （Label）
2. 各參數的輸入框（private TextField）
3. 各參數的數值（private int）
4. 錯誤提醒（private Label）
5. 確認按扭（private Button）

### 方法

1. `public void start()`：初始化使用者輸入介面，顯示視窗
2. `private VBox paint_window()`：用 `Grid` 配置參數的標籤和輸入框
3. `private void handle_enter_button_click()`：按下確認扭後取得各輸入框的數值並存入對應的變數， 如果輸入不符合的值則顯示錯誤提醒
4. `private void reset_field_borders()`：如果輸入正確，還原輸入框顏色
5. `private void highlight_invalid_fields()`：將輸入不符合型態的輸入格標記為紅色框
6. 各個參數的 getter 和 setter

![image](https://hackmd.io/_uploads/HyQq4oa4A.png)

當使用者輸入不符合格式的資料，顯示錯誤提醒

![截圖 2024-06-05 下午5.00.03](https://hackmd.io/_uploads/HkRNYjTE0.png)


## Class_Human

用來模擬每個人口的狀況、移動模擬、碰撞偵測、(康復、死亡、感染)模擬。

### 設定

- 位置 (x, y)
- 狀態 (未感染者、康復者、感染者、死亡者)
- 感染率
- 是否在通道，還是在城市裡

### 模擬走路函式

總共分成了兩種情況: 在城市裡面走路、在通道裡面走路。

#### 若在城市裡面走路: 
下一位置為 $(x', y') = (x + Rcos(\theta), y + Rsin(\theta))$
- R 為每個人走路的幅度
- $\theta$ 是走下一步的角度 (隨機 0~360度)

#### 若在通道裡面走路: 
則維持和通道平行的角度，一路走到另外一個城市不換角度。


### 碰撞偵測

因為人不會是單一個點，所以設定在範圍九宮格內，只要有接觸到感染者就會去判斷是否被感染

### 康復/死亡

每一天過完，會把所有感染者拿出來判斷他們是否會康復、死亡或繼續保持感染。


## Class_Map

用來畫出目前的模擬地圖，以及人口的分布地圖

### 地區模擬

初始各地區人口佔比

- 城市人口佔比: 60%
- 小城市人口佔比: 30%
- 鄉村人口佔比: 10%

設計理念: 大城市很多人地區又很大，小城市區域小但是人卻很多，鄉村跟小城市大小差不多，但是人很少。

### 地區初始化

- 邊界: 0
- 大城市: 1
- 小城市: 2
- 鄉村: 3
- 垂直通道: 4
- 平行通道: 5
- 其他(視為障礙不可走的地區): -1

![圖片 1](https://hackmd.io/_uploads/rJlVTsTEA.png)


### 人口地圖初始化

- 未感染者+康復者: 1
- 感染者: 2


### 畫圖

利用格點化圖的方式

```java=
pixelWriter.setColor(x,y,color)
```

地圖的話把邊界設成黑色，可行走地區是白色，不可行走地區是藍色，方便使用者觀看。

人口的畫把未感染者或康復者設成綠色，感染者則設成紅色，死亡者不會出現在地圖上。



![image](https://hackmd.io/_uploads/H1Perj6VA.png)

## Class_DataChart

顯示死亡人數(黑)，健康人數(綠)，康復人數(藍)，感染人數(紅)的數據圖

### 參數

1. 死亡人數，健康人數，康復人數，感染人數（`private final XYChart.Series<Number, Number>`）
2. xy軸（`private NumberAxis`）
3. 數據圖（`private LineChart<Number, Number>`）

### 方法

1. `public DataChart(int total_days, int total_population)`：建構子，根據`total_days`、`total_population`初始化xy軸的尺度
2. `public void start()`：初始化數據圖，顯示視窗
3. `public void update_data(int day, int susceptible, int infection, int health, int total)`：更新`day`的人數資料

### 數據圖樣式設定（chart_style.css）

設定各數據線的顏色和粗細、圖例符號和顏色

```css=
/* 設定各數據線的顏色和粗細 */
.chart-series-line.series0 {
    -fx-stroke: blue;
    -fx-stroke-width: 2px;
}

.chart-series-line.series1 {
    -fx-stroke: red;
    -fx-stroke-width: 2px;
}

.chart-series-line.series2 {
    -fx-stroke: green;
    -fx-stroke-width: 2px;
}

.chart-series-line.series3 {
    -fx-stroke: black;
    -fx-stroke-width: 2px;
}

/* 設定各個數據線的圖例符號和顏色 */
.chart-legend {
    -fx-background-color: transparent;
    -fx-padding: 10px;
    -fx-border-color: transparent;
}

.chart-legend-item {
    -fx-font-size: 14px;
    -fx-text-fill: black;
    -fx-padding: 1px;
}

.chart-legend-item-symbol.series0 {
    -fx-background-color: blue; 
}

.chart-legend-item-symbol.series1 {
    -fx-background-color: red; 
}

.chart-legend-item-symbol.series2 {
    -fx-background-color: green; 
}

.chart-legend-item-symbol.series3 {
    -fx-background-color: black; 
}
```

![截圖 2024-06-05 下午6.00.17](https://hackmd.io/_uploads/ryfvwnTVR.png)

## Reference 

- (Chen, Yi-Cheng) A Time-dependent SIR model for COVID-19 with Undetectable Infected Persons
- (LIU, YIN-TZU) Study on the Change of the Number of Cases of Notifiable Infectious Diseases in the COVID-19 Epidemic Prevention Strategy – A Case of Taiwan
