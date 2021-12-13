SZ=2500
int[][] map = new int[SZ][SZ]
for (int yy = 0; yy < SZ; yy++) {
  for (int xx = 0; xx < SZ; xx++) {
    map[xx][yy] = 0
  }
}
long count(lines) {
  def rslt = 0L
  lines.each { rslt += it.sum() }
  return rslt
}

def folds = []
new File('day13-02.txt').each { line ->
  if (line.contains(',')) {
    map[Integer.parseInt(line.split(',')[0])][Integer.parseInt(line.split(',')[1])] = 1
  } else if (line.startsWith("fold along")) {
    def str = line.substring(11)
    folds.add([ axis: str.split('=')[0], value: Integer.parseInt(str.split('=')[1]) ])
  }
}

folds.each { it ->
  println "Fold ${it.axis} -- ${it.value}"
  if (it.axis == 'y') {
    for (int yy = it.value + 1; yy < SZ; yy++) {
        def newVal = it.value - (yy - it.value)
        if (newVal >= 0) {
            for (int xx = 0; xx < SZ; xx++) {
                if (map[xx][yy] == 1) {
                    map[xx][yy] = 0
                    map[xx][newVal] = 1
                }
            }
        }
    }
  } else {
    for (int xx = it.value + 1; xx < SZ; xx++) {
        def newVal = it.value - (xx - it.value)
        if (newVal >= 0) {
            for (int yy = 0;  yy < SZ; yy++) {
                if (map[xx][yy] == 1) {
                    map[xx][yy] = 0
                    map[newVal][yy] = 1
                }
            }
        }
    }
  }
  // println "Sum: ${count(map)}"
}

new File("output.txt").withWriter { writer ->
    for (int yy = 0; yy < SZ; yy++) {
        for (int xx = 0; xx < SZ; xx++) {
            if (map[xx][yy] == 0)
                writer.write('.')
            else
                writer.write('#')
        }
        writer.newLine()
    }
}