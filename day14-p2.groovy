def init = "";
new File('day14-02.txt').readLines().take(1).each { line ->
  init = line;
}
def rules = [:]
new File('day14-02.txt').readLines().drop(2).each { line ->
  rules.put(line.split("->")[0].trim(), line.split("->")[1].trim())
}

def pairs = [:]
for (int zz = 0; zz < init.size() - 1; zz++) {
  String pair = init.substring(zz, zz + 2)
  def xx = pairs.get(pair)
  if (xx == null)
    pairs.put(pair, 1L)
  else
    pairs.put(pair, ++xx)
}

for (int zz = 1; zz <= 40; zz++) {
  println "Step $zz"
  def pairsTmp = [:]
  pairs.each { key, value ->
    def middle = rules.get(key)
    def xx1 = pairsTmp.get(key[0] + middle)
    if (xx1 == null) 
      pairsTmp.put(key[0] + middle, value)
    else
      pairsTmp.put(key[0] + middle, xx1 + value)

    def xx2 = pairsTmp.get(middle + key[1])
    if (xx2 == null) 
      pairsTmp.put(middle + key[1], value)
    else
      pairsTmp.put(middle + key[1], xx2 + value)
  }
  pairs = pairsTmp
}
result(init, pairs)

void result(init, pairs) {
  def found = [:]
  found.put(init[0], 1L)
  pairs.each { key, value ->
    def val = found.get(key[1])
    if (val == null)
      found.put(key[1], value)
    else
      found.put(key[1], val + value)
  }
  println found
  def leastCommon = found.values().sort().first()
  def mostCommon = found.values().sort().last()
  println "${leastCommon} -- ${mostCommon} -- ${mostCommon - leastCommon}"
}