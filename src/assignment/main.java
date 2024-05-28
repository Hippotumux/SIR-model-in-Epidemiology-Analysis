package assignment;

import windows.Map;
import windows.UI;
import objects.Parameter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class main extends Application {
	private int _total_population;
	private int[] _circulation_volume;
	private int _infection_rate;
	private int _reinfection_rate;
	private int _recovery_rate;
	private int _mortality_rate;
	private int _city1_initial_infections;
	private int _city2_initial_infections;
	private int _city3_initial_infections;
	public void get_parameter(Parameter para, UI ui) {
		para.set_total_population(ui.get_total_population());
		para.set_circulation_volume(ui.get_circulation_volume());
		para.set_infection_rate(ui.get_infection_rate());
		para.set_reinfection_rate(ui.get_reinfection_rate());
		para.set_recovery_rate(ui.get_recovery_rate());
		para.set_mortality_rate(ui.get_mortality_rate());
	}
	
	@Override
    public void start(Stage primaryStage) {
        UI ui = new UI();
        // wait and show (會等結束才跑其他的)
        ui.start();
        
        Parameter para = new Parameter();
        get_parameter(para, ui);
        
        Map k = new Map();
        k.start();
       
    }
    public static void main(String[] args) {
        launch(args);
    }
}