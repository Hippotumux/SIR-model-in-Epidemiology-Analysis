package objects;

public class Parameter {
	private int _total_population;
	private int[] _circulation_volume;
	private int _infection_rate;
	private int _reinfection_rate;
	private int _recovery_rate;
	private int _mortality_rate;
	private int _moving_times;

	public Parameter() {
		// 建構子
		this._total_population = 0;
		this._circulation_volume = new int[] { 0, 0, 0 };
		this._infection_rate = 0;
		this._reinfection_rate = 0;
		this._recovery_rate = 0;
		this._mortality_rate = 0;
		this._moving_times = 20;
	}

	public Parameter(int total_population, int[] circulation_volume, int infection_rate, int reinfection_rate,
			int recovery_rate, int mortality_rate, int moving_times) {
		this._total_population = total_population;
		this._circulation_volume = circulation_volume;
		this._infection_rate = infection_rate;
		this._reinfection_rate = reinfection_rate;
		this._recovery_rate = recovery_rate;
		this._mortality_rate = mortality_rate;
		this._moving_times = moving_times;
	}

	// Getter 和 Setter 方法

	public int get_total_population() {
		return _total_population;
	}

	public void set_total_population(int total_population) {
		this._total_population = total_population;
	}

	public int[] get_circulation_volume() {
		return _circulation_volume;
	}

	public void set_circulation_volume(int[] circulation_volume) {
		this._circulation_volume = circulation_volume;
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

	public int get_moving_times() {
		return _moving_times;
	}

	public void set_moving_times(int moving_times) {
		this._moving_times = moving_times;
	}
}