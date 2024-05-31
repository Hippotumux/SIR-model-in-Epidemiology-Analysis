package assignment;

import windows.Map;
import windows.UI;
import windows.DataChart;
import objects.Human;
import objects.Parameter;

import java.util.ArrayList;
import java.util.Random;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {
    public static int[][] human_map = new int[900][600];
    public static int[][] infect_map = new int[900][600];
    public static ArrayList<Human> total_people = new ArrayList<>();
	public static int _total, _health_amount, _infection_amount;
	private Timeline timeline;
	
	public void get_parameter(Parameter para, UI ui) {
		para.set_total_population(ui.get_total_population());
		para.set_circulation_volume(ui.get_circulation_volume());
		para.set_infection_rate(ui.get_infection_rate());
		para.set_reinfection_rate(ui.get_reinfection_rate());
		para.set_recovery_rate(ui.get_recovery_rate());
		para.set_mortality_rate(ui.get_mortality_rate());
		para.set_city1_initial_infections(ui.get_city1_initial_infections());
		para.set_city2_initial_infections(ui.get_city2_initial_infections());
		para.set_city3_initial_infections(ui.get_city3_initial_infections());
	}
	
	
	public void create_city_and_people(Parameter para, int[][] map) {
		_total = para.get_total_population();
		int A_amount = (int)((float)_total * 0.6);
		int B_amount = (int)((float)_total * 0.3);
		int C_amount = _total - A_amount - B_amount;
		Random rand = new Random();
		// 生成 A 城市人 (0,0)~(500,450)
		for (int i = 0 ; i < A_amount ; i++) {
			// 生成合理位置 A 城市
			int rand_x, rand_y;
			do {
				rand_x = (int)(rand.nextInt(500));
				rand_y = (int)(rand.nextInt(450));
			} while (map[rand_x][rand_y] != 1);
			
			if (i < para.get_city1_initial_infections()) {
				// 有病
			
				// 初始化位置 x y 步數 感染率 再感染率 康復綠 死亡率 城市 目前狀況
				Human new_human = new Human();
				new_human.init(rand_x, rand_y, para.get_moving_times(), para.get_reinfection_rate(), para.get_recovery_rate(), para.get_mortality_rate(),1, 1);
				total_people.add(new_human);
			} else {
				// 沒病
				
				// 初始化位置 x y 步數 感染率 再感染率 康復綠 死亡率 城市 目前狀況
				Human new_human = new Human();
				new_human.init(rand_x, rand_y, para.get_moving_times(), para.get_reinfection_rate(), para.get_recovery_rate(), para.get_mortality_rate(),1, 0);
				total_people.add(new_human);
			}
		}
		
		// 生成 B 城市人 (700,50)~(900,300)
		for (int i = 0 ; i < B_amount ; i++) {
			// 生成合理位置 A 城市
			int rand_x, rand_y;
			do {
				rand_x = (int)(rand.nextInt(200)) + 700;
				rand_y = (int)(rand.nextInt(250)) + 50;
			} while (map[rand_x][rand_y] != 2);
		
			if (i < para.get_city2_initial_infections()) {
				// 有病
			
				// 初始化位置 x y 步數 感染率 再感染率 康復綠 死亡率 城市 目前狀況
				Human new_human = new Human();
				new_human.init(rand_x, rand_y, para.get_moving_times(), para.get_reinfection_rate(), para.get_recovery_rate(), para.get_mortality_rate(),2, 1);
				total_people.add(new_human);
			} else {
				// 沒病
				
				// 初始化位置 x y 步數 感染率 再感染率 康復綠 死亡率 城市 目前狀況
				Human new_human = new Human();
				new_human.init(rand_x, rand_y, para.get_moving_times(), para.get_reinfection_rate(), para.get_recovery_rate(), para.get_mortality_rate(),2, 0);
				total_people.add(new_human);
			}
		}
		
		// 生成 C 城市人 (400,500)~(750,600)
		for (int i = 0 ; i < C_amount ; i++) {
			// 生成合理位置 A 城市
			int rand_x, rand_y;
			do {
				rand_x = (int)(rand.nextInt(350))+400;
				rand_y = (int)(rand.nextInt(100))+500;
			} while (map[rand_x][rand_y] != 3);
		
			if (i < para.get_city3_initial_infections()) {
				// 有病
			
				// 初始化位置 x y 步數 感染率 再感染率 康復綠 死亡率 城市 目前狀況
				Human new_human = new Human();
				new_human.init(rand_x, rand_y, para.get_moving_times(), para.get_reinfection_rate(), para.get_recovery_rate(), para.get_mortality_rate(),3, 1);
				total_people.add(new_human);
			} else {
				// 沒病
				
				// 初始化位置 x y 步數 感染率 再感染率 康復綠 死亡率 城市 目前狀況
				Human new_human = new Human();
				new_human.init(rand_x, rand_y, para.get_moving_times(), para.get_reinfection_rate(), para.get_recovery_rate(), para.get_mortality_rate(),3, 0);
				total_people.add(new_human);
			}
		}
	}
	
	public void init_map() {
		for (int x = 0 ; x < 900 ; x++) {
			for (int y = 0 ; y < 600 ; y++) {
				human_map[x][y] = 0;
				infect_map[x][y] = 0;
			}
		}
	}
	
	public void update_map() {
		_total = 0;
		_health_amount = 0;
		_infection_amount = 0;
		
		for (Human h : total_people) {
			int x = h.get_x();
			int y = h.get_y();
			if (human_map[x][y] != 2 && (h.get_status() == 0 || h.get_status() == 2)) {
				human_map[x][y] = 1;
			} else if (h.get_status() == 1) {
				human_map[x][y] = 2;
				infect_map[x][y] += 1;
			}
			
			if (h.get_status() == 1) {
				_infection_amount++;
			} else if (h.get_status() == 0) {
				_health_amount ++;
			} else {
				_total ++;
			}
		}
		//_total = _health_amount + _infection_amount;
	}
	
	@Override
    public void start(Stage primaryStage) {
		int total_day = 1000;
		int day_gap = 10;
        UI ui = new UI();
        // wait and show (會等結束才跑其他的)
        ui.start();
        
        Parameter para = new Parameter();
        get_parameter(para, ui);
        
        Map big_map = new Map();
        big_map.start();
        
        PauseTransition delay = new PauseTransition(Duration.seconds(2)); // 延遲2秒
        

		// 等待 big_map 生成完地圖跑下面這些
		delay.setOnFinished(subevent -> {
	        DataChart data_chart = new DataChart();
	        data_chart.start();
			create_city_and_people(para, big_map.getmap());
			init_map();
			final int[] day = {0};
            timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            	if ((day[0] != 0) && day[0] % day_gap == 0) {
                	System.out.println("第" + ((int)day[0]/day_gap + 1) + "天");
                	System.out.println("健康人數" + _health_amount);
                	System.out.println("病毒人數" + _infection_amount);
            	}
                if (day[0] < total_day) {
                    for (int i = 0; i < total_people.size(); i++) {
                    	total_people.get(i).walk(900, 600, big_map.getmap(), infect_map);
                    }
        			init_map();
                    update_map();
                    for (int i = 0; i < total_people.size(); i++) {
                       	if (total_people.get(i).get_status() == 0 || total_people.get(i).get_status() == 2) {
                    		total_people.get(i).check_contact(infect_map);
                    	}
                    }
                    big_map.plotpeople(human_map);
                    if (day[0] != 0 && day[0] % day_gap == 0) {
                        for (int i = 0; i < total_people.size(); i++) {
                        	if (total_people.get(i).get_status() == 1) {
                        		total_people.get(i).check_recover();
                        	}
                        	if (total_people.get(i).get_status() == 1) {
                        		total_people.get(i).check_death();
                        	}
                        }
                        data_chart.update_data((int)(day[0]/day_gap)+1, _total , _infection_amount, _health_amount);
                    }
                    day[0]++;
                } else {
                	timeline.stop();
                    return ;
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
		});
		delay.play();
    }
    public static void main(String[] args) {
        launch(args);
    }
}