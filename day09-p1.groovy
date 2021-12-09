int lowestPoints(lines) {
  def rslt = 0L
  for (int yy = 1; yy < lines.size() - 1; yy++) {
    for (int xx = 1; xx < lines[yy].size() - 1; xx++) {
      if ((lines[yy][xx] < lines[yy + 1][xx]) && (lines[yy][xx] < lines[yy - 1][xx]) && (lines[yy][xx] < lines[yy][xx + 1]) && (lines[yy][xx] < lines[yy][xx - 1])) {
        rslt += lines[yy][xx] + 1
      }
    }
  }
  return rslt
}

long start = System.nanoTime()
def lines = []
new File('day09-02.txt').each { it ->
  lines.add(it.collect(it2 -> Integer.parseInt(it2)))
}
def emptyLine = []
(1..(lines[0].size() + 2)).each { emptyLine.add(100) }
def borders = []
borders.add(emptyLine)
lines.each { it ->
  def xx = []
  xx.add(100)
  xx.addAll(it)
  xx.add(100)
  borders.add(xx)
}
borders.add(emptyLine)

println lowestPoints(borders)
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
