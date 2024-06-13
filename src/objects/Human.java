package objects;

import java.util.Random;

public class Human {
	private int _posX, _posY, _theta, _movedistance;
	private int _status; // 0 未感染 ,1 感染 ,2 康復者 ,3 死亡者
	private int _infect_rate, _double_infect_rate, _recover_rate, _death_rate;
	private int _is_in_hole; // 0 沒有, ,1 左邊 ,2 右邊 ,3 上面, 4 下面
	private int _in_city; // 0 沒有在城市, 1 大城市, 2 小城市, 3鄉村

	// 初始化位置 x y 步數 感染率 再感染率 康復綠 死亡率 在哪個城市 目前狀況
	public void init(int x, int y, int infect_rate, int double_infect_rate, int recover_rate, int death_rate, int city,
			int status) {
		_posX = x;
		_posY = y;
		_infect_rate = infect_rate;
		_double_infect_rate = double_infect_rate;
		_recover_rate = recover_rate;
		_death_rate = death_rate;
		_theta = 0;
		_movedistance = 10;
		_is_in_hole = 0;
		_in_city = city;
		_status = status;
	}

	// 走路 (需要傳地圖狀態，感染者位置)
	public void walk(int W, int H, int[][] map, int[][] infect_map) {
		// 隨機角度
		Random rand_theta = new Random();
		_theta = rand_theta.nextInt(360);

		// 確認是否在橋裡面，以及動向
		if (_is_in_hole == 1) {
			_theta = 180;
		} else if (_is_in_hole == 2) {
			_theta = 0;
		} else if (_is_in_hole == 3) {
			_theta = 270;
		} else if (_is_in_hole == 4) {
			_theta = 90;
		}

		// 在通道的話就直接繼續走
		if (_is_in_hole != 0) {
			_posX = _posX + (int) (Math.cos(Math.toRadians(_theta)) * _movedistance);
			_posY = _posY + (int) (Math.sin(Math.toRadians(_theta)) * _movedistance);
		}

		// 變換 _posX, _posY 重複直到走到合理位置
		while (_is_in_hole == 0) {
			// 隨機角度
			_theta = rand_theta.nextInt(360);
			int new_posX = _posX + (int) (Math.cos(Math.toRadians(_theta)) * _movedistance);
			int new_posY = _posY + (int) (Math.sin(Math.toRadians(_theta)) * _movedistance);

			// 判斷是否出界
			if (new_posX < 0 || new_posX >= W)
				continue;
			if (new_posY < 0 || new_posY >= H)
				continue;

			// 判斷是否在城市裡
			if (map[new_posX][new_posY] == 1 || map[new_posX][new_posY] == 2 || map[new_posX][new_posY] == 3
					|| map[new_posX][new_posY] == 4 || map[new_posX][new_posY] == 5) {
				_posX = new_posX;
				_posY = new_posY;
				break;
			}
		}

		// 確認從通道出來到城市中
		if (_is_in_hole != 0) {
			// 假設三個城市 1 2 3
			if (map[_posX][_posY] == 1) {
				_in_city = 1;
				_is_in_hole = 0;
			} else if (map[_posX][_posY] == 2) {
				_in_city = 2;
				_is_in_hole = 0;
			} else if (map[_posX][_posY] == 3) {
				_in_city = 3;
				_is_in_hole = 0;
			}
		}

		// 確認是否在橋上
		// 假設三個橋分別為 4(垂直) 5(左右)
		if (_is_in_hole == 0) {
			if (map[_posX][_posY] == 4 && _in_city == 1) {
				_is_in_hole = 4;
			} else if (map[_posX][_posY] == 4 && _in_city == 2) {
				_is_in_hole = 4;
			} else if (map[_posX][_posY] == 4 && _in_city == 3) {
				_is_in_hole = 3;
			} else if (map[_posX][_posY] == 5 && _in_city == 1) {
				_is_in_hole = 2;
			} else if (map[_posX][_posY] == 5 && _in_city == 2) {
				_is_in_hole = 1;
			}
		}
	}

	// 確認碰撞(需要傳感染者地圖位置)
	public boolean check_contact(int[][] infect_map) {
		boolean contact = false;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (infect_map[_posX + x][_posY + y] >= 1) {
					contact = true;
					break;
				}
			}
			if (contact)
				break;
		}
		if (contact) {
			Random random_infect = new Random();
			int infect_prob = random_infect.nextInt(100);
			int infect_rate_target = _infect_rate;
			if (_status == 2) {
				infect_rate_target *= _double_infect_rate;
				infect_rate_target /= 100;
			}

			if (infect_prob < infect_rate_target) {
				_status = 1;
				return true;
			}
		}
		return false;
	}

	// 康復
	public boolean check_recover() {
		Random random_recover = new Random();
		int recover_prob = random_recover.nextInt(100);
		if (recover_prob < _recover_rate) {
			_status = 2;
			return true;
		}
		return false;
	}

	// 死亡
	public boolean check_death() {
		Random random_death = new Random();
		int death_prob = random_death.nextInt(100);
		if (death_prob < _death_rate) {
			_status = 3;
			return true;
		}
		return false;
	}

	public int get_status() {
		return _status;
	}

	public int get_x() {
		return _posX;
	}

	public int get_y() {
		return _posY;
	}
}