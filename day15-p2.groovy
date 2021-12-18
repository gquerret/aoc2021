initValues = new File('day15-02.txt').collect { line ->
  line.getChars().collect { it -> Integer.parseInt("${it}") }
}
values = []
(0..4).each { it ->
  initValues.each { line ->
    def newLine = []
    (0..4).each { it2 -> line.each { digit -> newLine.add(digit + it + it2 > 9 ? digit + it + it2 - 9 : digit + it + it2) } }
    values.add(newLine)
  }
}
int[][] paths = new int[values.size()][values.size()]

void handlePos(values, paths, currX, currY) {
  int top = paths[currY - 1][currX]
  int left = paths[currY][currX - 1]
  if (currX == 0) {
    paths[currY][currX] = top + values[currY][currX]
  } else if (currY == 0) {
    paths[currY][currX] = left + values[currY][currX]
  } else {
    paths[currY][currX] = (top < left ? top : left) + values[currY][currX]
  }
}

long start = System.nanoTime()

for (int yy = 1; yy < values.size(); yy++) {
  for (int xx = 0; xx <= yy; xx++) {
    handlePos(values, paths, xx, yy - xx)
  }
}
for (int xx = 1; xx < values.size(); xx++) {
  for (int yy = xx; yy < values.size(); yy++) {
    def currY = values.size() - 1 - (yy - xx), currX = yy
    handlePos(values, paths, yy, values.size() - 1 - (yy - xx))
  }
}

/* new File('output.txt').withWriter { writer -> 
  paths.each { line -> 
   line.each { it -> writer.write("${it} "); }
   writer.newLine(); }
} */
println "Min Path: ${paths[paths.size() - 1][paths.size() - 1]}"
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
