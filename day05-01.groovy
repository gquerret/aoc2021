EDGE = 1000
OVERLAP = 2
def grid = []

void displayGrid(List grid) {
  for (int yy = 0; yy < EDGE; yy++) {
    def line = ""
    for (int xx = 0; xx < EDGE; xx++) {
       line += grid[(EDGE * yy) + xx]
    }
    println line
  }
}
int gridOverlap(List grid) {
  def rslt = 0
  for (int yy = 0; yy < EDGE; yy++) {
    for (int xx = 0; xx < EDGE; xx++) {
       if (grid[(EDGE * yy) + xx] >= OVERLAP)
         rslt++
    }
  }
  return rslt
}

for (int xx = 0; xx < EDGE * EDGE; xx++) {
  grid.add(0)
}
new File('day05-02.txt').each { it ->
  str = it.split(" -> ");
  x1 = Integer.parseInt(str[0].split(",")[0])
  y1 = Integer.parseInt(str[0].split(",")[1])
  x2 = Integer.parseInt(str[1].split(",")[0])
  y2 = Integer.parseInt(str[1].split(",")[1])
  println x1 + ":" + y1 + " / " + x2 + ":" + y2
  if (x1 == x2) {
    for (int yy = (y1 < y2 ? y1 : y2); yy <= (y1 > y2 ? y1 : y2); yy++) {
      grid[(yy * EDGE) + x1]++
    }
  } else if (y1 == y2) {
    for (int xx = (x1 < x2 ? x1 : x2); xx <= (x1 > x2 ? x1 : x2); xx++) {
      //println xx
      grid[(y1 * EDGE) + xx]++
    }
  } else {
    // Part 2
    pos1 = y1 < y2 ? (y1 * EDGE) + x1 : (y2 * EDGE) + x2
    pos2 = y1 > y2 ? (y1 * EDGE) + x1 : (y2 * EDGE) + x2
    diff = pos2 - pos1
    if ((diff % (EDGE + 1)) == 0) {
      // Down - Right
      for (int zz = pos1; zz <= pos2; zz += (EDGE + 1)) {
        grid[zz]++ 
      }
    } else if ((diff % (EDGE - 1)) == 0) {
      // Down - Left
      for (int zz = pos1; zz <= pos2; zz += (EDGE - 1)) {
        grid[zz]++ 
      }
    }
  }
  // displayGrid(grid)
  // println ""
}
println gridOverlap(grid)
