lines = new File('day12-04.txt').collect(it -> it.split('-'))

boolean isSmallCave(dest) {
  boolean rslt = true
  dest.getChars().each { rslt &= it.isLowerCase() }
  return rslt
}

void processPath(path, multi) {
  def currCave = path.last()
  def prevCave = path.size() > 1 ? path[path.size() - 2] : null
  if (currCave == 'end')
    solutions.add(path)
  else {
    lines.each { line ->
      if ((line[0] == currCave) || (line[1] == currCave)) {
        def dest = line[0] == currCave ? line[1] : line[0]
        if (dest == 'start')
          return
        def small = isSmallCave(dest)
        if ((small && !path.contains(dest)) || !small) {
          def copy = []
          copy.addAll(path)
          copy.add(dest)
          processPath(copy, multi)
        } else if ((small && (multi == '') && (path.findAll(it -> it == dest).size() == 1))) {
          def copy = []
          copy.addAll(path)
          copy.add(dest)
          processPath(copy, dest)
        }
      }
    }
  }
}

long start = System.nanoTime()
solutions = []
def paths = ["start"]
processPath(paths, '')
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
println "Result ${solutions.size()}"
