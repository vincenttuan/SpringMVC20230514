package spring.mvc.session14.validator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.mvc.session14.entity.Stock;

@Component
public class StockValidator implements Validator {
	// 所有股票商品資訊
	private static Map<String, Double> quotes = new LinkedHashMap<>();
	
	static {
		quotes.put("2330", 555.0); // 股票代號, 作收
		quotes.put("2303", 50.9);  // 股票代號, 作收
		quotes.put("2317", 107.5); // 股票代號, 作收
		// ...
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		// 判斷 clazz 是否是 Stock 的實體
		return Stock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stock stock = (Stock)target; // 要被驗證的物件
		// 基礎驗證
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "stock.amount.empty");
		
		// 進階驗證
		if(quotes.get(stock.getSymbol()) == null) {
			// 查無股票
			errors.rejectValue("symbol", "stock.symbol.notfound");
		} else {
			// 取得昨日收盤價(昨收)
			Double previousClose = quotes.get(stock.getSymbol());
			// 1.買進價格必須是昨日收盤價的±10%之間
			if(stock.getPrice() < previousClose * 0.9 || stock.getPrice() > previousClose * 1.1 ) {
				// API: void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage);
				errors.rejectValue("price", "stock.price.range", new Object[] {previousClose * 0.9, previousClose * 1.1}, "stock.price.range");
			}
			// 2.買進股數必須大於或等於1000
			if(stock.getAmount() < 1000) {
				errors.rejectValue("amount", "stock.amount.notenough");
			}
			// 3.買進股數必須是1000的倍數(1000股 = 1張)
			if(stock.getAmount() % 1000 != 0) {
				errors.rejectValue("amount", "stock.amount.range");
			}
		}
	}

}
