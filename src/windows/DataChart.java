package windows;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class DataChart {

	private Stage _stage = new Stage();
	private final XYChart.Series<Number, Number> _total_series = new XYChart.Series<>();
	private final XYChart.Series<Number, Number> _infection_series = new XYChart.Series<>();
	private final XYChart.Series<Number, Number> _health_series = new XYChart.Series<>();
	private final NumberAxis _x_axis = new NumberAxis();
	private final NumberAxis _y_axis = new NumberAxis();
	private LineChart<Number, Number> lineChart = new LineChart<>(_x_axis, _y_axis);

	public DataChart() {
		// TODO Auto-generated constructor stub
	}

	public void start() {

		_x_axis.setLabel("Days");
		_y_axis.setLabel("Number of People");

		lineChart.setTitle("Dynamic Population Data");

		_total_series.setName("Total");
		_total_series.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: blue; -fx-stroke-width: 2px;");

		_infection_series.setName("Infection");
		_infection_series.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: red; -fx-stroke-width: 2px;");

		_health_series.setName("Health");
		_health_series.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: green; -fx-stroke-width: 2px;");

		lineChart.getData().addAll(_total_series, _infection_series, _health_series);

		Scene scene = new Scene(lineChart, 400, 300);
		_stage.setScene(scene);
		_stage.setTitle("SIR Simulation");
		_stage.show();
	}

	public void update_data(int day, int total, int infection, int health) {
		/**
		 * 更新
		 */
		_total_series.getData().add(new XYChart.Data<>(day, total));
		_infection_series.getData().add(new XYChart.Data<>(day, infection));
		_health_series.getData().add(new XYChart.Data<>(day, health));

	}

}
