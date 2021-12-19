result = 0
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
def readPacket(digits, level) {
  if (digits.size() < 11)
    return [0, 0]
  int version = readVersion(digits, 0)

  int typeId = readTypeId(digits, 3)
  int currPos = 6
  long yyy = 0L
  if (typeId == 4) {
    def done = false
    def tmp = []
    while (!done) {
      done = digits[currPos] == 0
      (1..4).each { tmp.add(digits[currPos + it])}
      currPos += 5
    }
    yyy = Long.parseLong(tmp.join(''), 2)
    println "Literal Value: ${yyy}"
  } else {
    def subPackets = []
    int lengthTypeId = digits[currPos++]
    if (lengthTypeId == 0) {
      // Read next 15 bytes
      def totalLength = Integer.parseInt(digits.subList(currPos, currPos + 15).join(''), 2)
      println "Operator packet - Length 0: ${totalLength}"
      def tmp = digits.subList(currPos + 15, currPos + 15 + totalLength)
      def (sz, rslt) = readPacket(tmp, level + 1)
      while (sz > 0) {
        subPackets.add(rslt)
        tmp = tmp.subList(sz, tmp.size())
        (sz, rslt) = readPacket(tmp, level + 1)
      }
      currPos += 15 + totalLength
    } else {
      def numPackets = Integer.parseInt(digits.subList(currPos, currPos + 11).join(''), 2)
      println "Operator Packet - Num packets ${numPackets}"
      currPos += 11
      for (int zz = 0; zz < numPackets; zz++) {
        (zzz, rslt) = readPacket(digits.subList(currPos, digits.size()), level + 1)
        currPos += zzz
        subPackets.add(rslt)
      }
    }
    println "SubPackets: ${subPackets}"
    if (typeId == 0) {
      println "Sum: ${subPackets.sum()}"
      yyy = subPackets.sum()
    } else if (typeId == 1) {
      println "Mult:"
      yyy = 1
      subPackets.each { yyy *= it }
    } else if (typeId == 2) {
      println "Min: ${subPackets.min()}"
      yyy = subPackets.min()
    } else if (typeId == 3) {
      println "Max: ${subPackets.max()}"
      yyy = subPackets.max()
    } else if (typeId == 5) {
      println "GT: "
      yyy = subPackets[0] > subPackets[1] ? 1 : 0
    } else if (typeId == 6) {
      println "LT: "
      yyy = subPackets[0] < subPackets[1] ? 1 : 0
    } else if (typeId == 7) {
      println "EQ: "
      yyy = subPackets[0] == subPackets[1] ? 1 : 0
    }
  }
  return [currPos, yyy]
}

(xxx, yyy) = readPacket(digits, 1)
println "Result: ${yyy}"
