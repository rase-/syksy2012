require 'rubygems'
require 'json'
require 'Node.rb'

# Used to decode the path from the goal node to the first
def decode_path(goal_node)
  path = []
  until goal_node.nil?
    path.unshift goal_node
    goal_node = goal_node.previous
  end
  return path
end

def bfs(start_id, goal_id, nodes)
  start_node = nodes[start_id]
  nodelist = [start_node]
  until nodelist.empty?
    current = nodelist.shift
    current.neighbours.each do |node|
      if node.id == goal_id
        node.previous = current
        puts "Goal found"
        return decode_path(node)
      end
      if node.colour == :white
        node.previous = current
        nodelist.push node
      end
    end
    current.colour = :black
  end
  raise "No route found"
end

filename = "verkko.json"
lines = IO.readlines(filename)

# Read file is used to generate hashes from json
hashes = JSON.parse(lines[0])

nodes = {}

# Nodes are initialized from hashes generated from json
# Previous attribute also added for path decoding
hashes.each do |hash|
  node = Node.new.hash_initialize(hash)
  nodes[node.id] = node
  class << node
    attr_accessor :previous
  end
end

# This block of code fills neighbours for ids
nodes.each do |key,node|
  neighbour_ids = node.neighbours.keys
  node.neighbours = []
  neighbour_ids.map { |id| node.neighbours << nodes[id] }
end
puts "Nodes: #{nodes.size}"
puts "Starts: "
start = readline.strip
puts start.class
puts "Ends: "
goal = readline.strip
path = bfs(start, goal, nodes)

path.each { |node| puts node }
