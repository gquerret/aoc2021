def init = "";
new File('day14-02.txt').readLines().take(1).each { line ->
  init = line;
}
def rules = [:]
new File('day14-02.txt').readLines().drop(2).each { line ->
  rules.put(line.split("->")[0].trim(), line.split("->")[1].trim())
}

Foo first = new Foo(init.substring(0, 2))
Foo prev = first
for (int zz = 1; zz < init.size() - 1; zz++) {
  Foo curr = new Foo(init.substring(zz, zz + 2))
  prev.next = curr
  prev = curr
}

for (int zz = 1; zz <= 10; zz++) {
  println "Step ${zz}"
  insert(first, rules)  
}
result(first)

void insert(Foo foo, rules) {
  while (foo != null) {
    String str = foo.pair
    Foo foo2 = new Foo(rules.get(str) + str[1])
    foo2.next = foo.next
    foo.pair = str[0] + rules.get(str)
    foo.next = foo2
    foo = foo2.next
  }
}

void result(Foo foo) {
  def found = [:]
  found.put(foo.pair[0], 1L)
  while (foo != null) {
    def val = found.get(foo.pair[1])
    if (val == null)
      found.put(foo.pair[1], 1L)
    else
      found.put(foo.pair[1], ++val)
    foo = foo.next
  }

  def leastCommon = found.values().sort().first()
  def mostCommon = found.values().sort().last()
  println "${leastCommon} -- ${mostCommon} -- ${mostCommon - leastCommon}"
}

void display(Foo foo) {
  while (foo != null) {
    println "Curr ${foo.pair}"
    foo = foo.next
  }
}

class Foo {
  String pair
  Foo next
  public Foo(pair) { this.pair = pair }
}
