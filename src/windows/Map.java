package windows;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Map{
    public static int[][] map = new int[900][600];
    private static Pane root = new Pane();
    private static WritableImage writableImage = new WritableImage(900, 600);
    private static PixelWriter pixelWriter = writableImage.getPixelWriter();
    private Stage primaryStage = new Stage();
    public void start() {
        primaryStage.setTitle("900x600 Window");

        int bl = 3;

        // 初始化地圖
        for (int x = 0; x < 900; x++) {
            for (int y = 0; y < 600; y++) {
                map[x][y] = -1;
            }
        }

        // 繪製不同的區域 A
        for (int x = 0; x < 500; x++) {
            for (int y = 0; y < 300; y++) {
                if (x < bl || y < bl || x >= 500 - bl || y >= 300 - bl) map[x][y] = 0;
                else map[x][y] = 1;
                if (y >= 300 - bl && x >= bl && x < 300 - bl) map[x][y] = 1;
            }
        }

        for (int x = 0; x < 300; x++) {
            for (int y = 300; y < 450; y++) {
                if (x < bl || y < 300 + bl || x >= 300 - bl || y >= 450 - bl) map[x][y] = 0;
                else map[x][y] = 1;
                if (y < 300 + bl && x >= bl && x < 300 - bl) map[x][y] = 1;
            }
        }
        
        // B

        for (int x = 700; x < 900; x++) {
            for (int y = 50; y < 300; y++) {
                if (x < 700 + bl || y < 50 + bl || x >= 900 - bl || y >= 300 - bl) map[x][y] = 0;
                else map[x][y] = 2;
            }
        }

        for (int x = 400; x < 750; x++) {
            for (int y = 500; y < 600; y++) {
                if (x < 400 + bl || y < 500 + bl || x >= 750 - bl || y >= 600 - bl) map[x][y] = 0;
                else map[x][y] = 3;
            }
        }

        for (int x = 435 - bl; x < 465 + bl; x++) {
            for (int y = 300 - bl; y < 500 + bl; y++) {
                if (x < 435 || x >= 465) map[x][y] = 0;
                else map[x][y] = 4;
            }
        }

        for (int x = 710 - bl; x < 740 + bl; x++) {
            for (int y = 300 - bl; y < 500 + bl; y++) {
                if (x < 710 || x >= 740) map[x][y] = 0;
                else map[x][y] = 4;
            }
        }

        for (int x = 500 - bl; x < 700 + bl; x++) {
            for (int y = 135 - bl; y < 165 + bl; y++) {
                if (y < 135 || y >= 165) map[x][y] = 0;
                else map[x][y] = 5;
            }
        }

        plotmap();
        
        // 創建ImageView並設置WritableImage
        ImageView imageView = new ImageView(writableImage);

        // 將ImageView添加到Pane中
        root.getChildren().add(imageView);

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void plotmap() {
        // 根據地圖數據繪製顏色
        for (int x = 0; x < 900; x++) {
            for (int y = 0; y < 600; y++) {
                if (map[x][y] == 0) pixelWriter.setColor(x, y, Color.BLACK);
                else if (map[x][y] == -1) pixelWriter.setColor(x,  y, Color.LIGHTBLUE); 
                else pixelWriter.setColor(x, y, Color.WHITE);
            }
        }
    }
    
    public void plotpeople(int[][] human_map) {
        // 根據地圖數據繪製顏色
        for (int x = 0; x < 900; x++) {
            for (int y = 0; y < 600; y++) {
                if (human_map[x][y] == 1) pixelWriter.setColor(x, y, Color.GREEN);
                else if (human_map[x][y] == 2) pixelWriter.setColor(x,  y, Color.RED);
            }
        }
    }
    
    public int[][] getmap() {
    	return map;
    }
}