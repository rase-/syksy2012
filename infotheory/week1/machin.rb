# Taylor series expasion for arccot function
def arccot(x, unity)
  sum = 0
  power_of_x = unity / x
  n = 1
  sign = 1

  # Dividing x^2 by running counter value n to obtain terms in the series
  # Stopped at real value < 10^-n
  while(true)
    term = power_of_x / n
    if (term == 0)
      return sum
    end
    sum = sum + sign*term
    power_of_x /= x*x
    n += 2
    sign = -sign
  end
end

# Using Machin's formula to compute pi
def computePi(digits)
  # Unity defines 10^n, so we can compute n digits for pi as regular integer
  unity = 10 ** (digits +10)
  # Machin's formula is actually for pi/4, so we mutiply it by 4
  val = 4 * (4 * arccot(5, unity) - arccot(239, unity))
  return val / (10 ** 10)
end

puts "How many digits do you want to compute?"
digits = readline.strip.to_i
puts computePi(digits)
