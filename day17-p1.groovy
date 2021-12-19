

def inTarget(Tuple pos, Square target) {
  return (pos.x >= target.topLeft.x) && (pos.x <= target.bottomRight.x) && (pos.y <= target.topLeft.y) && (pos.y >= target.bottomRight.y)
}
def pastTarget(Tuple pos, Square target) {
  return (pos.x > target.bottomRight.x) || (pos.y < target.bottomRight.y)
}
def maxY(positions) {
  return positions.collect { it -> it.y }.max()
}
def getShot(Tuple velocity, Square target) {
  def rslt = []
  Tuple pos = new Tuple(0, 0)
  while (!pastTarget(pos, target)) {
    rslt.add(pos)
    pos = new Tuple(pos.x + velocity.x, pos.y + velocity.y) // newPos
    velocity.x = velocity.x > 0 ? velocity.x - 1 : 0
    velocity.y -= 1
  }
  return rslt
}

// def target = new Square(new Tuple(20, -5), new Tuple(30, -10))
def target = new Square(new Tuple(240, -57), new Tuple(292, -90))
def maxHeight = 0
def meetCrit = 0
for (int xx = 20; xx < 300; xx++) {
  println "${xx}"
  for (int yy = -100; yy < 3000; yy++) {
    def rslt = getShot(new Tuple(xx, yy), target)
    if (inTarget(rslt[rslt.size() - 1], target)) {
      meetCrit++
      def height = maxY(rslt)
      maxHeight =  (height > maxHeight ? height : maxHeight)
    }
  }
}
println "MaxY: ${maxHeight}"
println "Meet criteria: ${meetCrit}"

class Tuple {
  int x, y
  public Tuple(x, y) {
    this.x = x;
    this.y = y;
  }
  String toString() {
    return "[${x};${y}]"
  }
}
class Square {
  Tuple topLeft, bottomRight
  public Square(tl, br) {
    this.topLeft = tl
    this.bottomRight = br
  }
  String toString() {
    return "${topLeft} -> ${bottomRight}"
  }
}