package spring.mvc.session14.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session14.entity.Stock;
import spring.mvc.session14.validator.StockValidator;

@Controller
@RequestMapping("/session14/stock")
public class StockController {

	private List<Stock> stocks = new CopyOnWriteArrayList<>();

	@Autowired
	private StockValidator stockValidator;

	@GetMapping("/")
	public String index(Model model, @ModelAttribute Stock stock) {
		model.addAttribute("stocks", stocks);
		model.addAttribute("avgCosts", getAvgCosts());
		model.addAttribute("avgPrice", getAvgPrice());
		return "session14/stock";
	}

	@PostMapping("/")
	public String add(Model model, @ModelAttribute @Valid Stock stock, BindingResult result) {
		// 自訂驗證
		stockValidator.validate(stock, result);
		// 驗證結果
		if (result.hasErrors()) {
			model.addAttribute("stocks", stocks);
			model.addAttribute("avgCosts", getAvgCosts());
			model.addAttribute("avgPrice", getAvgPrice());
			return "session14/stock";
		}
		stocks.add(stock);
		return "redirect:./";
	}

	// 相同股票代號，平均買進成本 ?
	private Map<String, Double> getAvgCosts() {
		Map<String, Double> averageCosts = stocks.stream().collect(
				Collectors.groupingBy(Stock::getSymbol, Collectors.averagingDouble(s -> s.getPrice() * s.getAmount())));
		return averageCosts;
	}

	// Homework: 如何得到平均買進股價 ?
	// Ex: ((550.0*2000) + (600.0*3000))/5000 = 580
	private Map<String, Double> getAvgPrice() {
		Map<String, Double> totalInvestmentPerStock = stocks.stream().collect(
				Collectors.groupingBy(Stock::getSymbol, Collectors.summingDouble(s -> s.getPrice() * s.getAmount())));

		Map<String, Integer> totalAmountPerStock = stocks.stream()
				.collect(Collectors.groupingBy(Stock::getSymbol, Collectors.summingInt(Stock::getAmount)));

		Map<String, Double> averageCostPerStock = new HashMap<>();

		totalInvestmentPerStock.forEach((symbol, totalInvestment) -> {
			double averageCost = totalInvestment / totalAmountPerStock.get(symbol);
			averageCostPerStock.put(symbol, averageCost);
		});
		return averageCostPerStock;
	}
}
