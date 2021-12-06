def list = [0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L]
void processOneDay(List list) {
  long pos0 = list[0]
  for (int zz = 0; zz < 8; zz++) {
    list[zz] = list[zz + 1]
  }
  list[6] += pos0
  list[8] = pos0
}

new File('day06-02.txt').each { it ->
  it.split(',').each { it2 -> list[Integer.parseInt(it2)]++ }
}

for (int day = 1; day < 257; day++) {
  processOneDay(list);
  println day + " -> " + list.sum()
}