long start = System.nanoTime()

def lines = []
def inputs = []
def outputs = []
new File('day08-02.txt').each { it ->
  def input = it.tokenize('|')[0].tokenize(' ')
  def output = it.tokenize('|')[1].tokenize(' ')
  lines.add([input: input, output: output])
  inputs.addAll(input)
  outputs.addAll(output)
}
// lines.each { println it }
// outputs.findAll { it.size() == 2 }.each { println it }

def sum1 = outputs.findAll { it.size() == 2 }.size()
def sum7 = outputs.findAll { it.size() == 3 }.size()
def sum4 = outputs.findAll { it.size() == 4 }.size()
def sum8 = outputs.findAll { it.size() == 7 }.size()
println "Total: ${sum1 + sum7 + sum4 + sum8}"
