long start = System.nanoTime()

def numZ = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], numO = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
// def numZ = [0, 0, 0, 0, 0], numO = [0, 0, 0, 0, 0]
def gamma = "", epsilon = "";
new File('day03-02.txt').each { it ->
  for (int zz = 0; zz < it.length(); zz++) {
    if (it[zz] == '0')
      numZ[zz]++
    else
      numO[zz]++
  }
}
for (int zz = 0; zz < numZ.size(); zz++) {
  if (numZ[zz] > numO[zz]) {
    gamma += "0"
    epsilon += "1"
  } else {
    gamma += "1"
    epsilon += "0"
  }
}
println "Gamma: ${gamma} - Epsilon: ${epsilon}"
println "Result: ${Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)}"
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
