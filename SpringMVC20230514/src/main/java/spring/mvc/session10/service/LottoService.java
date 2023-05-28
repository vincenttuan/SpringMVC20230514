package spring.mvc.session10.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

@Service
public class LottoService {
	
	// 用來存放 Lotto 紀錄的集合
	private List<Set<Integer>> lottos = new CopyOnWriteArrayList<>(); // 支援多執行緒的集合(Thread-safe)
	
	// 新增一筆紀錄
	public Set<Integer> add() {
		// 得到 539 的電腦選號
		Set<Integer> lotto = getRandomLotto();
		lottos.add(0, lotto); // 0: 表示每次新的號碼都放到最前面
		return lotto;
	}
	
	// 查詢多筆紀錄
	public List<Set<Integer>> queryAll() {
		return lottos;
	}
	
	// 修改指定紀錄(一整列)
	public void update(int index) {
		lottos.set(index, getRandomLotto());
	}
	
	// 修改指定紀錄(某一個欄位)
	
	// 刪除指定紀錄(一整列)
	public void delete(int index) {
		lottos.remove(index);
	}
	
	// 透過電腦選號產生號碼
	private Set<Integer> getRandomLotto() {
		// 樂透 539: 1~39 取出任意不重複的五個數字
		Set<Integer> lotto = new LinkedHashSet<>();
		Random random = new Random();
		while (lotto.size() < 5) {
			int number = random.nextInt(39) + 1; // 1~39
			lotto.add(number);
		}
		return lotto;
	}
	
	// 分組統計資料與排序
	
}
