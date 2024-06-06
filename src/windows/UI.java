package windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UI {
	// Stage
	private Stage input_stage = new Stage();

	// 初始化各個輸入框
	private TextField _total_population_field;
	private TextField _infection_rate_field;
	private TextField _reinfection_rate_field;
	private TextField _recovery_rate_field;
	private TextField _mortality_rate_field;
	private TextField _days_field;
	private TextField _city1_initial_infections_field;
	private TextField _city2_initial_infections_field;
	private TextField _city3_initial_infections_field;

	// 初始化各個輸入框所對應的數值
	private int _total_population;
	private int _infection_rate;
	private int _reinfection_rate;
	private int _recovery_rate;
	private int _mortality_rate;
	private int _city1_initial_infections;
	private int _city2_initial_infections;
	private int _city3_initial_infections;
	private int _days;

	private Label _error_label; // 錯誤提醒
	private Button _enter_button; // 確認按扭

	public UI() {
	}

	public void start() {
		input_stage.initModality(Modality.APPLICATION_MODAL);
		// 使用者輸入視窗
		VBox user_input_window = paint_window();

		// 確認按鈕
		_enter_button = new Button("確認");
		_enter_button.setOnAction(event -> {
			handle_enter_button_click();
		});
		user_input_window.getChildren().add(_enter_button);
		Scene input_scene = new Scene(user_input_window, 450, 450);
		input_stage.setTitle("User Input");
		input_stage.setScene(input_scene);
		input_stage.showAndWait();
	}

	private VBox paint_window() {
		/**
		 * 初始化使用者輸入的視窗
		 */
		VBox vbox = new VBox();
		vbox.getChildren().clear();
		vbox.setPadding(new Insets(20));
		vbox.setAlignment(Pos.TOP_CENTER);

		Label total_population_label = new Label("總人口數（<100000）：");
		_total_population_field = new TextField("10000");

		Label infection_rate_label = new Label("感染率（%）：");
		_infection_rate_field = new TextField("46");

		Label reinfection_rate_label = new Label("再感染率（%）：");
		_reinfection_rate_field = new TextField("15");

		Label recovery_rate_label = new Label("康復率（%）：");
		_recovery_rate_field = new TextField("24");

		Label mortality_rate_label = new Label("死亡率（%）：");
		_mortality_rate_field = new TextField("6");

		Label days_label = new Label("天數（<1000）：");
		_days_field = new TextField("100");

		Label city1_initial_infections_label = new Label("城市的初始感染人數：");
		_city1_initial_infections_field = new TextField("100");

		Label city2_initial_infections_label = new Label("郊區的初始感染人數：");
		_city2_initial_infections_field = new TextField("1");

		Label city3_initial_infections_label = new Label("鄉村的初始感染人數：");
		_city3_initial_infections_field = new TextField("1");

		_error_label = new Label();
		_error_label.setPadding(new Insets(10));
		_error_label.setTextFill(Color.RED);

		// Arrange labels and text fields in a grid
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(20));

		grid.add(total_population_label, 0, 0);
		grid.add(_total_population_field, 1, 0);
		grid.add(infection_rate_label, 0, 1);
		grid.add(_infection_rate_field, 1, 1);
		grid.add(reinfection_rate_label, 0, 2);
		grid.add(_reinfection_rate_field, 1, 2);
		grid.add(recovery_rate_label, 0, 3);
		grid.add(_recovery_rate_field, 1, 3);
		grid.add(mortality_rate_label, 0, 4);
		grid.add(_mortality_rate_field, 1, 4);
		grid.add(days_label, 0, 5);
		grid.add(_days_field, 1, 5);
		grid.add(city1_initial_infections_label, 0, 6);
		grid.add(_city1_initial_infections_field, 1, 6);
		grid.add(city2_initial_infections_label, 0, 7);
		grid.add(_city2_initial_infections_field, 1, 7);
		grid.add(city3_initial_infections_label, 0, 8);
		grid.add(_city3_initial_infections_field, 1, 8);

		// Add the grid and error label to this Pane
		vbox.getChildren().addAll(grid, _error_label);

		return vbox;
	}

	private void handle_enter_button_click() {
		/**
		 * 按下確認扭後取得各輸入框的數值並存入對應的變數， 如果輸入不符合的值則顯示錯誤提醒
		 */
		reset_field_borders();
		_error_label.setText("");
		try {
			_total_population = Integer.parseInt(_total_population_field.getText());
			_infection_rate = Integer.parseInt(_infection_rate_field.getText());
			_reinfection_rate = Integer.parseInt(_reinfection_rate_field.getText());
			_recovery_rate = Integer.parseInt(_recovery_rate_field.getText());
			_mortality_rate = Integer.parseInt(_mortality_rate_field.getText());
			_city1_initial_infections = Integer.parseInt(_city1_initial_infections_field.getText());
			_city2_initial_infections = Integer.parseInt(_city2_initial_infections_field.getText());
			_city3_initial_infections = Integer.parseInt(_city3_initial_infections_field.getText());
			_days = Integer.parseInt(_days_field.getText());
			_error_label.setTextFill(Color.GREEN);
			_error_label.setText("輸入成功");
			input_stage.close();
		} catch (NumberFormatException e) {
			highlight_invalid_fields();
			_error_label.setText("請輸入有效的數字格式");
			System.err.println("請輸入有效的數字格式");
		}
	}

	private void reset_field_borders() {
		/**
		 * 還原輸入框顏色
		 */
		Border default_border = new TextField().getBorder();
		_total_population_field.setBorder(default_border);
//		_map1_traffic_field.setBorder(default_border);
//		_map2_traffic_field.setBorder(default_border);
//		_map3_traffic_field.setBorder(default_border);
		_infection_rate_field.setBorder(default_border);
		_reinfection_rate_field.setBorder(default_border);
		_recovery_rate_field.setBorder(default_border);
		_mortality_rate_field.setBorder(default_border);
		_days_field.setBorder(default_border);
		_city1_initial_infections_field.setBorder(default_border);
		_city2_initial_infections_field.setBorder(default_border);
		_city3_initial_infections_field.setBorder(default_border);
	}

	private void highlight_invalid_fields() {
		/**
		 * 將輸入不符合型態的輸入格標記為紅色框
		 */
		Border red_border = new Border(
				new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

		try {
			Integer.parseInt(_total_population_field.getText());
		} catch (NumberFormatException e) {
			_total_population_field.setBorder(red_border);
		}
		try {
			Integer.parseInt(_infection_rate_field.getText());
		} catch (NumberFormatException e) {
			_infection_rate_field.setBorder(red_border);
		}
		try {
			Integer.parseInt(_days_field.getText());
		} catch (NumberFormatException e) {
			_days_field.setBorder(red_border);
		}
		try {
			Integer.parseInt(_reinfection_rate_field.getText());
		} catch (NumberFormatException e) {
			_reinfection_rate_field.setBorder(red_border);
		}
		try {
			Integer.parseInt(_recovery_rate_field.getText());
		} catch (NumberFormatException e) {
			_recovery_rate_field.setBorder(red_border);
		}
		try {
			Integer.parseInt(_mortality_rate_field.getText());
		} catch (NumberFormatException e) {
			_mortality_rate_field.setBorder(red_border);
		}
		try {
			Integer.parseInt(_city1_initial_infections_field.getText());
		} catch (NumberFormatException e) {
			_city1_initial_infections_field.setBorder(red_border);
		}
		try {
			Integer.parseInt(_city2_initial_infections_field.getText());
		} catch (NumberFormatException e) {
			_city2_initial_infections_field.setBorder(red_border);
		}
		try {
			Integer.parseInt(_city3_initial_infections_field.getText());
		} catch (NumberFormatException e) {
			_city3_initial_infections_field.setBorder(red_border);
		}
	}

	// Getter and Setter methods
	public void set_days(int days) {
		this._days = days;
	}

	public int get_days() {
		return this._days;
	}

	public int get_total_population() {
		return _total_population;
	}

	public void set_total_population(int total_population) {
		this._total_population = total_population;
	}

	public int get_infection_rate() {
		return _infection_rate;
	}

	public void set_infection_rate(int infection_rate) {
		this._infection_rate = infection_rate;
	}

	public int get_reinfection_rate() {
		return _reinfection_rate;
	}

	public void set_reinfection_rate(int reinfection_rate) {
		this._reinfection_rate = reinfection_rate;
	}

	public int get_recovery_rate() {
		return _recovery_rate;
	}

	public void set_recovery_rate(int recovery_rate) {
		this._recovery_rate = recovery_rate;
	}

	public int get_mortality_rate() {
		return _mortality_rate;
	}

	public void set_mortality_rate(int mortality_rate) {
		this._mortality_rate = mortality_rate;
	}

	public int get_city1_initial_infections() {
		return _city1_initial_infections;
	}

	public void set_city1_initial_infections(int city1_initial_infections) {
		this._city1_initial_infections = city1_initial_infections;
	}

	public int get_city2_initial_infections() {
		return _city2_initial_infections;
	}

	public void set_city2_initial_infections(int city2_initial_infections) {
		this._city2_initial_infections = city2_initial_infections;
	}

	public int get_city3_initial_infections() {
		return _city3_initial_infections;
	}

	public void set_city3_initial_infections(int city3_initial_infections) {
		this._city3_initial_infections = city3_initial_infections;
	}
}