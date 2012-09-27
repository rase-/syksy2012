require 'pp'


def random_number(distribution)
  r = rand # Random number in [0,1]
  return 1 if r > distribution[0]
  return 0
end

def generate_tuples(n, model)
  tuples = []
  (0..(n-1)).each do |i|
    tuple = {}
    model.nodes.each do |node|
      enabled = true
      node.parents.each do |parent|
        enabled = false if tuple[parent.id] == 0
      end
      if enabled
        value = random_number(node.distribution)
      else
        value = 0
      end
      tuple[node.id] = value
    end
    tuples << tuple
  end
  return tuples
end

def pretty_print_tuples(tuples)
  tuples.each do |tuple|
    #(A,R,S,B,K,L)
    puts "(" + tuple[:A].to_s + "," + tuple[:R].to_s + "," + tuple[:S].to_s + "," + tuple[:B].to_s + "," + tuple[:K].to_s + "," + tuple[:L].to_s + ")" 
  end
end

class BayesModel
  attr_accessor :nodes, :roots
  def initialize
    @nodes = []
    @nodes_by_id = {}
    @roots = []
  end
  
  def add_node(node)
    @nodes << node
    @nodes_by_id[node.id] = node
    @roots << node if node.parents.empty?
  end
  
  def find_node(id)
    return @nodes_by_id[id]
  end
  
  class Node
    attr_accessor :parents, :children, :probability, :id
    
    # Probability holds given that the value of all parents is 1
    def initialize(id, parents=[], children=[], probability=0)
      @id = id
      @parents = parents
      @children = children
      @probability = probability
    end
    
    def distribution
      return [1-@probability, @probability]
    end
  end
end

model = BayesModel.new
model.add_node(BayesModel::Node.new(:A, [], [], 0.9))
model.add_node(BayesModel::Node.new(:R, [model.find_node(:A)], [], 0.9))
model.add_node(BayesModel::Node.new(:S, [model.find_node(:A)], [], 0.95))
model.add_node(BayesModel::Node.new(:B, [], [], 0.95))
model.add_node(BayesModel::Node.new(:K, [model.find_node(:S), model.find_node(:B)], [], 0.99))
model.add_node(BayesModel::Node.new(:L, [model.find_node(:K)], [], 0.99))

tuples = generate_tuples(100000, model)
#pretty_print_tuples(tuples)
not_l = 0
not_l_and_not_a = 0
not_l_and_not_b = 0

not_k = 0
not_k_and_a = 0
not_k_and_b = 0

tuples.each do |tuple|
  if tuple[:L] == 0
    not_l += 1
    not_l_and_not_a += 1 if tuple[:A] == 0
    not_l_and_not_b += 1 if tuple[:B] == 0
  end
  
  if tuple[:K] == 0
    not_k += 1
    not_k_and_a += 1 if tuple[:A] == 1
    not_k_and_b += 1 if tuple[:B] == 1
  end
end
puts "NOT L: " + not_l.to_s + " | NOT L AND NOT A: " + not_l_and_not_a.to_s + " | PERCENTAGE: " + (1.0 * not_l_and_not_a/not_l).to_s
puts "NOT L: " + not_l.to_s + " | NOT L AND NOT B: " + not_l_and_not_b.to_s + " | PERCENTAGE: " + (1.0 * not_l_and_not_b/not_l).to_s
puts "NOT K: " + not_k.to_s + " | NOT K AND A: " + not_k_and_a.to_s + " | PERCENTAGE: " + (1.0 * not_k_and_a/not_k).to_s
puts "NOT K: " + not_k.to_s + " | NOT K AND B: " + not_k_and_b.to_s + " | PERCENTAGE: " + (1.0 * not_k_and_b/not_l).to_s
