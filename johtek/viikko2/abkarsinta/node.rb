class Node
  attr_accessor :value, :children, :leaf, state

  def initialize()
    @value = nil
    @children = []
    @leaf = false
    @state = [[nil,nil,nil],[nil,nil,nil],[nil,nil,nil]]
  end

  def leaf?
    return @leaf
  end
end

