long start = System.nanoTime()

int commonLetters( str1,  str2) {
  def rslt = 0
  str1.each {
    if (str2.contains(it))
      rslt++
  }
  return rslt
}
String six (str1, str2, str3) {
  def rslt = str1
  str2.toCharArray().each { it -> rslt =  rslt.replace("${it}", "") }
  str3.toCharArray().each { it -> if (rslt.indexOf("${it}") == -1) rslt = rslt += it }
  def tmp = rslt.toCharArray();
  Arrays.sort(tmp);
  return new String(tmp) 
}

int getResult(line) {
  def mapping = [:], revMapping = []

  mapping[line.input.findAll{ it.size() == 2 }.first()] = 1
  revMapping[1] = line.input.findAll{ it.size() == 2 }.first()
  
  mapping[line.input.findAll{ it.size() == 3 }.first()] = 7
  revMapping[7] = line.input.findAll{ it.size() == 3 }.first()

  mapping[line.input.findAll{ it.size() == 4 }.first()] = 4
  revMapping[4] = line.input.findAll{ it.size() == 4 }.first()

  mapping[line.input.findAll{ it.size() == 7 }.first()] = 8
  revMapping[8] = line.input.findAll{ it.size() == 7 }.first()

  line.input.findAll{ it.size() == 5 }.each { it ->
    def lst = line.input.findAll{ it2 -> (it2.size() == 6) && (commonLetters(it, it2) == 5) }
    if (lst.size() == 1) {
      it2 = lst.first()
      mapping[it] = 3;
      revMapping[3] = it
      mapping[it2] = 9;
      revMapping[9] = it2
    } else if (lst.size() == 2) {
      mapping[it] = 5;
      revMapping[5] = it
    } else {
      mapping[it] = 2;
      revMapping[2] = it
    }
  }

  mapping[six(revMapping[8], revMapping[1], revMapping[5])] = 6;
  revMapping[6] = six(revMapping[8], revMapping[1], revMapping[5]);

  println mapping

  line.input.each { it ->
    if (mapping.get(it) == null) {
      mapping[it] = 0
      revMapping[0] = it
    }
  }

  return (1000 * mapping[line.output[0]]) + (100 * mapping[line.output[1]]) + (10 * mapping[line.output[2]]) + (1 * mapping[line.output[3]])
}

def lines = []
new File('day08-02.txt').each { it ->
  def input = it.tokenize('|')[0].tokenize(' ').collect { it2 -> def tmp = it2.toCharArray(); Arrays.sort(tmp); return new String(tmp) }
  def output = it.tokenize('|')[1].tokenize(' ').collect { it2 -> def tmp = it2.toCharArray(); Arrays.sort(tmp); return new String(tmp) }
  lines.add([input: input, output: output])
}
def rslt = 0
lines.each { rslt += getResult(it) }
println rslt

