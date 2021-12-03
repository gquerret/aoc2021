long start = System.nanoTime()

def list = new File('day03-02.txt').collect()

def tmp1 = [], tmp2 = []
tmp1.addAll(list)
tmp2.addAll(list)
for (int zz = 0; zz < 12; zz++) {
  def numZero = 0, numOne = 0;
  tmp1.each { it ->
    if (it[zz] == '0')
      numZero++
    else
      numOne++
  }
  if (tmp1.size() > 1)
    tmp1 = tmp1.findAll{ it -> it[zz] == (numOne >= numZero ? '1' : '0')}
  // println "${zz} -- ${numZero} / ${numOne} -- ${tmp1}"

  numZero = 0
  numOne = 0
  tmp2.each { it ->
    if (it[zz] == '0')
      numZero++
    else
      numOne++
  }
  if (tmp2.size() > 1)
    tmp2 = tmp2.findAll{ it -> it[zz] == (numZero <= numOne ? '0' : '1')}
  // println "${zz} -- ${numZero} / ${numOne} -- ${tmp2}"
}

println "Tmp1: ${tmp1.size()} -- ${tmp1.get(0)}"
println "Tmp2: ${tmp2.size()} -- ${tmp2.get(0)}"
println "Result: ${Integer.parseInt(tmp1.get(0), 2) * Integer.parseInt(tmp2.get(0), 2)}"
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
