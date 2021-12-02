long start = System.nanoTime()

def prev = Integer.MAX_VALUE;
def count = 0;
def list = new File('day01-01.txt').collect { it -> Integer.parseInt(it) }
list.each { it ->
  if (prev < it) {
    count++;
  }
  prev = it;
}
println "Count: ${count}"
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
