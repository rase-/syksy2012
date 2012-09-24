#!/usr/bin/env ruby
require 'pp'

def shannon_fano_code(symbols)
  sorted_symbols = []
  sorted_probabilities = []
  sorted = symbols.sort_by {|symbol, prob| prob}
  codes = {}
  symbols.each {|symbol, prob| codes[symbol] = ""}

end

def split_to_sets(sorted_symbols, codes)
  return codes if sorted_symbols.length == 1 
  
  sum = 0
  sums = sorted_symbols.collect {|pair| sum += pair[1]}
  
  start_second = 1
  
  sum_difference = (2*sums[start_second - 1] - sums[sums.length - 1]).abs
  
  if sorted_symbols.length <= 2
    # Here we manipulate codes
    (0..(start_second-1)).each {|i| }
    # Here we do recursion
    split_to_sets(sorted_symbols.first(start_second), codes)
    split_to_sets(sorted_symbols.last(sorted_symbols.length - start_second), codes)
    return codes
  end
  
  while (sum_difference >= (2*sums[start_second] - sums[sums.length - 2]).abs) do
    sum_difference = (2*sums[start_second] - sums[sums.length - 2]).abs
    start_second += 1
  end
  
  # Here we manipulate codes
  
  # Here we do recursion
  split_to_sets(sorted_symbols.first(start_second))
  split_to_sets(sorted_symbols.last(sorted_symbosl.length - start_second))
  return codes
  
end

shannon_fano_code({"a" => 0.2, "b" => 0.1})
split_to_sets([["b", 0.1], ["a", 0.2]], {"a" => 0.2, "b" => 0.1})