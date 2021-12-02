long start = System.nanoTime()

def x = 0, y = 0, aim = 0;
def list = new File('day02-02.txt').each { it ->
  def line = it.split(' ');
  if ("forward".equals(line[0])) {
    x += Integer.parseInt(line[1]);
    y += aim * Integer.parseInt(line[1]);
  } else if ("down".equals(line[0]))
    aim += Integer.parseInt(line[1]);
  else if ("up".equals(line[0]))
    aim -= Integer.parseInt(line[1]);
}
println "Result: ${x} * ${y} = ${x * y}"
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
