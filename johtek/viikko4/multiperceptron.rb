def train(data, y, iterations)
  w = (1..10).collect {(1..data[0].length).collect {|i| 0}}
  
  (1..iterations).each do |iter|
    r = rand(data.length)
    x = data[r]
    y_i = y[r]
    
    p = classify(x, w)
    
    if p != y_i.to_i # Update step
      w[y_i.to_i] = vector_addition(w[y_i.to_i], x)
      w[p] = vector_subtraction(w[p], x)
    end
    
  end
  
  return w
end

def classify(x, w)
  z = 0
  p = 0
    
  (0..w.length-1).each do |y| # Choosing argmax
    temp = 0
    (0..(x.length-1)).each do |i|
      temp += w[y][i] * x[i]
    end
    if temp > z
      z = temp 
      p = y
    end
  end
  return p
end

def test(test_set, correct_y, w)
  correct = 0
  (0..(test_set.length-1)).each do |i|
    p = classify(test_set[i], w)
    if p == correct_y[i].to_i
      correct += 1
    end
    puts p.to_s + " " +  correct_y[i].to_s
  end
  return 1.0*correct/test_set.length
end

def vector_addition(a, b)
  ret = []
  (0..(a.length-1)).each do |i|
    ret << (a[i] + b[i])
  end
  return ret
end

def vector_subtraction(a, b)
  ret = []
  (0..(a.length-1)).each do |i|
    ret << (a[i] - b[i])
  end
  return ret
end

pics = IO.readlines("mnist-x.data")
classes = IO.readlines("mnist-y.data")

=begin comment
counter = 0
until (counter == 99)
  puts pics[counter] + " " + classes[counter]
  counter += 1
end
=end

training_set = pics.first(5000)
verification_set = pics.last(1000)

training_y = classes.first(5000)
verification_y = classes.last(1000)

weights = train(training_set, training_y, 10000)

outputFile = File.new("weights.bmp", "w")
outputFile.syswrite(weights)
outputFile.close

puts test(verification_set, verification_y, weights)