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
long score2(char ch) {
  if (ch == '(')
    return 1
  else if (ch == '[')
    return 2
  else if (ch == '{')
    return 3
  else if (ch == '<')
    return 4
}

long parseLine(line) {
  def sequence = []
  def sc = 0L
  line.getChars().each { ch ->
    if (sc == 0) {
      if (closingChar(ch)) {
        if (sequence.last() == matchingChar(ch)) {
          sequence.removeLast()
        } else {
          sc = - score(ch)
        }
      } else {
        sequence.add(ch)
      }
    }
  }
  if (sc == 0) {
    // println "Remaining: ${sequence.reverse()}"
    sequence.reverse().each { ch ->
      sc = (sc * 5) + score2(ch)
    }
    return sc
  } else    
    return 0
}

long start = System.nanoTime()

def lines = new File('day10-02.txt').collect()
def scores = []
lines.each { line ->
  def tmp = parseLine(line)
  if (tmp != 0)
    scores.add(tmp)
  println "Parse line ${line}: ${tmp}"
}
Collections.sort(scores)
println scores
println "Middle: ${scores.get( (int) ((scores.size() - 1)/ 2))}"
println "Time: ${(System.nanoTime() - start) / 1000000} ms"
