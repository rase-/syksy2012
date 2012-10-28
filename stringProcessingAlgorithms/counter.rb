def hashValue(key, hash)
  value = hash[key]
  if value.nil?
    hash[key] = 1
  else
    hash[key] = value + 1
  end
  return hash
end

puts "Reading and processing"

character_hash = {}
word_hash = {}
file = File.open("english.50MB", "r").each_line do |line|
  line.downcase.split("").each do |char|c
    character_hash = hashValue(char, character_hash)
  end
  
  line.downcase.split(" ").each do |word|
    word_hash = hashValue(word, word_hash)
  end
end

file.close

puts "Processed and read, now sorting"

sorted = character_hash.sort_by {|char, amount| amount}
puts "Done"
puts "Showing results:"

# Printing top 10 characters
counter = 10
((sorted.size - 10)..(sorted.size - 1)).each do |i|
  puts "#{counter}. character: #{sorted[i][0]}, amount: #{sorted[i][1]}"
  counter -= 1
end

sorted = word_hash.sort_by {|word, amount| amount}

# Printing top 10 words
counter = 10
((sorted.size - 10)..(sorted.size - 1)).each do |i|
  puts "#{counter}. word: #{sorted[i][0]}, amount: #{sorted[i][1]}"
  counter -= 1
end