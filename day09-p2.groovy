int flag(int x, int y, int num, lines) {
  def rslt = 1
  lines[y][x] = num;
  if (lines[y + 1][x] < 9)
    rslt += flag(x, y + 1, num, lines)
  if (lines[y - 1][x] < 9)
    rslt += flag(x, y - 1, num, lines)
  if (lines[y][x + 1] < 9)
    rslt += flag(x + 1, y, num, lines)
  if (lines[y][x - 1] < 9)
    rslt += flag(x - 1, y, num, lines)
  return rslt
}

void parse(lines) {
  def currVal = 10;
  def szs = []
  for (int yy = 1; yy < lines.size() - 1; yy++) {
    for (int xx = 1; xx < lines[yy].size() - 1; xx++) {
      if (lines[yy][xx] < 9) {
        szs.add(flag(xx, yy, currVal++, lines))
      }
    }
  }
  def sorted = szs.sort{ a, b -> a > b ? -1 : 1 }
  def rslt = 1;
  sorted.take(3).each{ rslt *= it }
  println "Result: " + rslt
}

long start = System.nanoTime()
def lines = []
new File('day09-02.txt').each { it ->
  lines.add(it.collect(it2 -> Integer.parseInt(it2)))
}
def emptyLine = []
(1..(lines[0].size() + 2)).each { emptyLine.add(9) }
def borders = []
borders.add(emptyLine)
lines.each { it ->
  def xx = []
  xx.add(9)
  xx.addAll(it)
  xx.add(9)
  borders.add(xx)
}
borders.add(emptyLine)
parse(borders)
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
