long start = System.nanoTime()

def list = [];
def input = new File('day01-01.txt').collect { it -> Integer.parseInt(it) }
for (int zz = 2; zz < input.size(); zz++) {
  list.add(input[zz] + input[zz - 1] + input[zz - 2]);
}

def count = 0;
def prev = Integer.MAX_VALUE;
list.each { it ->
  if (prev < it) {
    count++;
  }
  prev = it;
}
println "Count: ${count}"
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
