package spring.mvc.session10.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	public void update(int rowIndex, int columnIndex) {
		Set<Integer> rowSet = lottos.get(rowIndex);
		List<Integer> rowList = new ArrayList<>(rowSet);
		while (true) {
			int newNum = new Random().nextInt(39) + 1;
			// 檢查 newNum 是否有含在 rowList 中, 若有則重取
			if(rowList.stream().filter(n -> n.intValue() == newNum).findAny().isEmpty()) {
				rowList.set(columnIndex, newNum);
				break;
			}
		}
		lottos.set(rowIndex, new LinkedHashSet<>(rowList));
	}
	
	
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
	public Map<Integer, Long> getLottoStatistics() {
		// 1. 將資料彙集
		// {[10, 20, 30, 5, 6], [11, 21, 31, 15, 16], [12, 22, 32, 5, 6] ...}
		// {10, 20, 30, 5, 6, 11, 21, 31, 15, 16, 12, 22, 32, 5, 6 ...}
		List<Integer> nums = lottos.stream()
									.flatMap(lotto -> lotto.stream())
									.collect(Collectors.toList());
		// 2. 資料分組
		Map<Integer, Long> stat = nums.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		// 3. 加上排序
		Map<Integer, Long> statAndSort = new LinkedHashMap<>();
		stat.entrySet().stream()
			.sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
			.forEachOrdered(e -> statAndSort.put(e.getKey(), e.getValue()));
		
		return statAndSort;
	}
	
}
