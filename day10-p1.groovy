boolean openingChar(char ch) {
  return ((ch == '<') || (ch == '(') || (ch == '{') || (ch == '['))
}
boolean closingChar(char ch) {
  return ((ch == '>') || (ch == ')') || (ch == '}') || (ch == ']'))
}
char matchingChar(char ch) {
  if (ch == '>')
    return '<'
  else if (ch == ')')
    return '('
  else if (ch == '}')
    return '{'
  else if (ch == ']')
    return '['
  else
    return ''
}
int score(char ch) {
  if (ch == ')')
    return 3
  else if (ch == ']')
    return 57
  else if (ch == '}')
    return 1197
  else if (ch == '>')
    return 25137
}
int parseLine(line) {
  def sequence = []
  def sc = 0
  line.getChars().each { ch ->
    if (sc == 0) {
      if (closingChar(ch)) {
        if (sequence.last() == matchingChar(ch)) {
          sequence.removeLast()
        } else {
          sc = score(ch)
        }
      } else {
        sequence.add(ch)
      }
    }
  }
  return sc
}

long start = System.nanoTime()

def lines = new File('day10-02.txt').collect()
def score = 0L
lines.each { line ->
  println "Parse line ${line}"
  score += parseLine(line)
}
println "Score: ${score}"
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
