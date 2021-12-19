versionSum = 0
def convert = [:]
convert.put('0'.charAt(0), [0, 0, 0, 0])
convert.put('1'.charAt(0), [0, 0, 0, 1])
convert.put('2'.charAt(0), [0, 0, 1, 0])
convert.put('3'.charAt(0), [0, 0, 1, 1])
convert.put('4'.charAt(0), [0, 1, 0, 0])
convert.put('5'.charAt(0), [0, 1, 0, 1])
convert.put('6'.charAt(0), [0, 1, 1, 0])
convert.put('7'.charAt(0), [0, 1, 1, 1])
convert.put('8'.charAt(0), [1, 0, 0, 0])
convert.put('9'.charAt(0), [1, 0, 0, 1])
convert.put('A'.charAt(0), [1, 0, 1, 0])
convert.put('B'.charAt(0), [1, 0, 1, 1])
convert.put('C'.charAt(0), [1, 1, 0, 0])
convert.put('D'.charAt(0), [1, 1, 0, 1])
convert.put('E'.charAt(0), [1, 1, 1, 0])
convert.put('F'.charAt(0), [1, 1, 1, 1])

def digits = []
new File('day16-02.txt').each { line ->
  line.getChars().each { it ->
    digits.addAll(convert.get(it))
  }
}
println digits.join('')

int readVersion(digits, pos) {
  return digits[pos + 2] + (2 * digits[pos + 1]) + (4 * digits[pos])
}
int readTypeId(digits, pos) {
  return digits[pos + 2] + (2 * digits[pos + 1]) + (4 * digits[pos])
}
int readPacket(digits, level) {
  // println "ReadPacket Level ${level}: ${digits.join('')}"
  if (digits.size() < 11)
    return 0
  int version = readVersion(digits, 0)
  // println "Version ${version}"
  versionSum += version
  int typeId = readTypeId(digits, 3)
  int currPos = 6
  if (typeId == 4) {
    def done = false
    def tmp = []
    while (!done) {
      done = digits[currPos] == 0
      (1..4).each { tmp.add(digits[currPos + it])}
      currPos += 5
    }
    def rslt = Long.parseLong(tmp.join(''), 2)
    println "Literal Value: ${rslt}"
  } else {
    int lengthTypeId = digits[currPos++]
    if (lengthTypeId == 0) {
      // Read next 15 bytes
      def totalLength = Integer.parseInt(digits.subList(currPos, currPos + 15).join(''), 2)
      println "Operator packet - Length 0: ${totalLength}"
      def tmp = digits.subList(currPos + 15, currPos + 15 + totalLength)
      def sz = readPacket(tmp, level + 1)
      while (sz > 0) {
        tmp = tmp.subList(sz, tmp.size())
        sz = readPacket(tmp, level + 1)
      }
      currPos += 15 + totalLength
    } else {
      def numPackets = Integer.parseInt(digits.subList(currPos, currPos + 11).join(''), 2)
      println "Operator Packet - Num packets ${numPackets}"
      currPos += 11
      for (int zz = 0; zz < numPackets; zz++) {
        currPos += readPacket(digits.subList(currPos, digits.size()), level + 1)
      }
      
    }
  }
  return currPos
}

def done = false
while (!done) {
  def xxx = readPacket(digits, 1)
  if (xxx == 0)
    done = true
  else
    digits = digits.subList(xxx, digits.size())  
}
println "Sum ${versionSum}"
