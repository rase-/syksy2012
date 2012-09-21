def alpha_beta_prune(node)
  return alpa_beta_max(node, -1, +1)
end

def alpha_beta_max(node, alpha, beta)
  return node.value if node.leaf?
  v = -Float::INFINITY
  node.children.each do |child|
    v = [v, alpha_beta_min(child, alpha, beta)].max
    return v if if v>= beta
    alpha = [alpha, v].max
  end
  return v
end

def alpha_beta_min(node, alpha, beta)
  return node.value if node.leaf?
  v = Foat::INFINITY
  for node.children.each do |child|
    v = [v, alpha_beta_max(child, alpha, beta)].min
    return v if v<= alpha
    beta = [beta, v].min
  end
end


