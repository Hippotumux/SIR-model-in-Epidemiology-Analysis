package windows;

import java.net.URL;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class DataChart {

	private Stage _stage = new Stage();
	private final XYChart.Series<Number, Number> _susceptition = new XYChart.Series<>();
	private final XYChart.Series<Number, Number> _infection_series = new XYChart.Series<>();
	private final XYChart.Series<Number, Number> _health_series = new XYChart.Series<>();
	private final XYChart.Series<Number, Number> _total_series = new XYChart.Series<>();
	private NumberAxis _x_axis;
	private NumberAxis _y_axis;
	private LineChart<Number, Number> lineChart;

	public DataChart(int total_days, int total_population) {
		// TODO Auto-generated constructor stub
		_x_axis = new NumberAxis();
		_y_axis = new NumberAxis(0, total_population + 500, 500);
		lineChart = new LineChart<>(_x_axis, _y_axis);
	}

	public void start() {
		/*
		 * 初始化數據圖，顯示視窗
		 */

		_x_axis.setLabel("Days");
		_y_axis.setLabel("Number of People");

		lineChart.setTitle("SIR Model in Epidemiology Analysis");

		_susceptition.setName("Recovered, R(t)");
		_infection_series.setName("Infected, I(t)");
		_health_series.setName("Susceptible, S(t)");
		_total_series.setName("Total population");

		lineChart.getData().addAll(_susceptition, _infection_series, _health_series, _total_series);
		lineChart.setLegendVisible(true);
		lineChart.setCreateSymbols(false);

		Scene scene = new Scene(lineChart, 450, 450);

		URL chart_style = getClass().getResource("/style/chart_style.css");
		scene.getStylesheets().add(chart_style.toExternalForm());

		_stage.setScene(scene);
		_stage.setTitle("SIR Simulation");
		_stage.show();
	}

	public void update_data(int day, int susceptible, int infection, int health, int total) {
		/**
		 * 更新資料
		 */
		_susceptition.getData().add(new XYChart.Data<>(day, susceptible));
		_infection_series.getData().add(new XYChart.Data<>(day, infection));
		_health_series.getData().add(new XYChart.Data<>(day, health));
		_total_series.getData().add(new XYChart.Data<>(day, total));

	}

}