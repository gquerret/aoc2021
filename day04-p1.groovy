List createGrille(String str) {
  return str.split(' ').findAll(it -> it != "").collect { it -> [number: Integer.parseInt(it), marked: false] }
}
boolean isFull(List grille) {
  for (int zz = 0; zz < 5; zz++) {
    if (grille[(5 * zz)].marked && grille[(5 * zz) + 1].marked && grille[(5 * zz) + 2].marked && grille[(5 * zz) + 3].marked && grille[(5 * zz) + 4].marked)
       return true
    if (grille[zz].marked && grille[zz + 5].marked && grille[zz + 10].marked && grille[zz + 15].marked && grille[zz + 20].marked)
       return true
  }
  return false
}
void mark(List grille, int number) {
  grille.each { if (it.number == number) it.marked = true }
}
int sum(List grille) {
  grille.findAll { !it.marked }.collect { it.number }.sum()
}

def lines = new File('day04-02.txt').text.readLines()
def tirage = lines.get(0).split(',').collect { Integer.parseInt(it) }
def grilles = []
for (int zz = 2; zz < lines.size(); zz += 6) {
  def numbers = []
  numbers.addAll(createGrille(lines[zz]))
  numbers.addAll(createGrille(lines[zz + 1]))
  numbers.addAll(createGrille(lines[zz + 2]))
  numbers.addAll(createGrille(lines[zz + 3]))
  numbers.addAll(createGrille(lines[zz + 4]))
  grilles.add([ won: false, grille: numbers ])
}

def winner
for (int zz = 0; zz < tirage.size(); zz++) {
  grilles.each { it ->
    mark(it.grille, tirage[zz])
    it.won = isFull(it.grille)
  }
  def losers = grilles.findAll{ !it.won }
  println "Tirage: ${tirage[zz]} -- ${losers.size()} grilles perdantes"
  if (losers.size() == 1) {
    winner = losers[0]
  }
  if (losers.size() == 0) {
    println "Winner: ${sum(winner.grille)} -- ${sum(winner.grille) * tirage[zz]}"
    return
  }
}
