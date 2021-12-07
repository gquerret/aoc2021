def list = []
def sums = []

long process(List list, int col) {
  def rslt = 0L
  for (int zz = 0; zz < list.size(); zz++) {
    rslt += list[zz] == null ? 0 : list[zz] * Math.abs(col - zz)
  }
  return rslt
}


long start = System.nanoTime()
new File('day07-02.txt').each { it ->
  it.split(',').collect(it2 -> Integer.parseInt(it2)).each { it2 -> if (list[it2] == null) list[it2] = 1; else list[it2]++; }
}
println list
for (int day = 0; day < list.size(); day++) {
  sums[day] = process(list, day);
}
println sums.min() + "-->" + sums.indexOf(sums.min())
