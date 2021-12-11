boolean reset(lines) {
  def simult = true
  for (int yy = 0; yy < lines.size(); yy++) {
    for (int xx = 0; xx < lines[yy].size(); xx++) {
      if (lines[yy][xx] < 0) {
        lines[yy][xx] = 0
      } else
        simult = false
    }
  }
  return simult
}

void increase(lines) {
  for (int yy = 0; yy < lines.size(); yy++) {
    for (int xx = 0; xx < lines[yy].size(); xx++) {
      lines[yy][xx]++
    }
  }
}
boolean flash(lines) {
  boolean retVal = false
  for (int yy = 0; yy < lines.size(); yy++) {
    for (int xx = 0; xx < lines[yy].size(); xx++) {
      // println "${yy} -- ${xx} -- ${lines.size()} -- ${lines[yy].size()}"
      if (lines[yy][xx] >= 10) {
        lines[yy][xx] = -10
        retVal = true
        if ((yy > 0) && (xx > 0) && (lines[yy - 1][xx - 1] >= 0)) // 7
          lines[yy - 1][xx - 1]++
        if ((yy > 0) && (lines[yy - 1][xx] >= 0)) // 8
          lines[yy - 1][xx]++
        if ((yy > 0) && (xx < lines[yy].size() - 1) && (lines[yy - 1][xx + 1] >= 0)) // 9
          lines[yy - 1][xx + 1]++
        if ((xx > 0) && (lines[yy][xx - 1] >= 0)) // 4
          lines[yy][xx - 1]++
        if ((xx < lines[yy].size() - 1) && (lines[yy][xx + 1] >= 0)) // 6
          lines[yy][xx + 1]++
        if ((yy < lines.size() - 1) && (xx > 0) && (lines[yy + 1][xx - 1] >= 0)) // 1
          lines[yy + 1][xx - 1]++
        if ((yy < lines.size() - 1) && (lines[yy + 1][xx] >= 0)) // 2
          lines[yy + 1][xx]++
        if ((yy < lines.size() - 1) && (xx < lines[yy].size() - 1) && (lines[yy + 1][xx + 1] >= 0)) // 3
          lines[yy + 1][xx + 1]++
      }
    }
  }
  return retVal
}

boolean step(lines) {
  increase(lines)
  while (flash(lines)) { }
  return reset(lines)
}

long start = System.nanoTime()
def lines = []
new File('day11-02.txt').each { it ->
  lines.add(it.getChars().collect(it2 -> Integer.parseInt("${it2}")))
}
def rslt = false
def zz = 0L
while (!rslt) {
  rslt = step(lines)
  zz++
}
println "Result ${zz}"
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
