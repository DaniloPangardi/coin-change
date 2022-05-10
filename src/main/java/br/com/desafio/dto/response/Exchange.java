package br.com.desafio.dto.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Exchange {
	
	private static final String REGEX_PATTERN = "\\[|]";
	
	private static final String STRING_EMPTY = "";
	
	private Integer exchangeValue;
	private List<Option> options;
	
	public static Exchange buildExchange(Integer exchangeValue) {
		var options = buildOptions(exchangeValue);
		return new Exchange(exchangeValue, options);
	}
	
	private static List<Option> buildOptions(Integer exchangeValue) {
		
		var coins = new ArrayList<>(Arrays.asList(1,2,3,5,8));
		
		var stack = new Stack<Integer>();
		
		var solutions = new HashMap<String, Integer>();
		
		calculateCoins(exchangeValue, coins, stack, solutions);
		
		var orderedSolutions = solutions.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.limit(3)
				.collect(Collectors.toList());
		
		var options = new ArrayList<Option>();
		
		orderedSolutions.forEach(s -> options.add(new Option(s.getValue(), s.getKey())));
		
		return options;
	}
	
	private static void calculateCoins(int exchangeValue, List<Integer> coins, Stack<Integer> stack, Map<String, Integer> solutions) {
		
		if(exchangeValue < 0 || coins.size() == 0) {
			return;
		}
		
		if(exchangeValue == 0) {
			solutions.put(stack.toString().replaceAll(REGEX_PATTERN, STRING_EMPTY), stack.size());
			return;
		}
		
		int coin = coins.get(0);
		stack.push(coin);
		
		calculateCoins(exchangeValue - coin, coins, stack, solutions);
		
		stack.pop();
		
		List<Integer> coinsAux = new ArrayList<Integer>(coins);
		coinsAux.remove(0);
		
		calculateCoins(exchangeValue, coinsAux, stack, solutions);
	}

	
}
