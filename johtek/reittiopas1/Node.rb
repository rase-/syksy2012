class Node
  attr_accessor :id, :name, :address, :x, :y, :neighbours

  def hash_initialize(hash)
    @id = hash["koodi"]
    @name = hash["nimi"]
    @address = hash["osoite"]
    @x = hash["x"]
    @y = hash["y"]
    @neighbours = hash["naapurit"]
    return self
  end

  def initialize(id=nil, name=nil, address=nil, x=nil, y=nil, neighbours=nil)
    @id = id
    @name = name
    @address = address
    @x = x
    @y = y
    @neighbours = neighbours
    @colour = :white
  end

  def to_s
    @name
  end
end
