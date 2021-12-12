package com.tuanmhoang.groovy

openBracket = "("
closeBracket = ")"

def calculate(formulaString) {
  def calculatedString = formulaString.replace(" ", "")
  def numbersStack = []
  def operatorsStack = []
  StringBuilder number = new StringBuilder();
  def result = 0;
  def shouldCalculate = false
  for (i in 0..calculatedString.length()-1) {
    final def charAt = calculatedString.charAt(i)
    if (i == calculatedString.length() - 1) {
      number.append(charAt)
      numbersStack.push(number.toString().toInteger())
      while (! operatorsStack.isEmpty()) {
        result = calculateValue(numbersStack.pop(), operatorsStack.pop(), numbersStack.pop())
        numbersStack.push(result)
      }
      break
    }
    if (charAt.isDigit()) {
      number.append(calculatedString.charAt(i))
    } else {
      numbersStack.push(number.toString().toInteger())
      if(shouldCalculate){
        result = result + calculateValue(numbersStack.pop(), operatorsStack.pop(), numbersStack.pop())
        numbersStack.push(result)
        shouldCalculate = false
      }
      if(isHighPriority(charAt)){
        shouldCalculate = true
      }
//      if (!operatorsStack.isEmpty()) {
//        result = result + calculateValue(numbersStack.pop(), operatorsStack.pop(), numbersStack.pop())
//        numbersStack.push(result)
//      }
      number = new StringBuilder()
      operatorsStack.push(charAt)
    }
  }
  result
}

def isHighPriority(operator){
  if(operator == '*' || operator == '/') {
    return true
  }
  return false
}

def calculateValue(number2, operator, number1) {
  def result
  switch (operator) {
    case '+':
      result = number1 + number2
      break
    case '-':
      result = number1 - number2
      break
    case '*':
      result = number1 * number2
      break
    case '/':
      result = number1 / number2
      break
    default:
      throw new RuntimeException("cannot calculate")
  }
  result
}

assert 5 == calculate("2+ 3")
assert 2 == calculate("5 - 3")
assert 15 == calculate("   5 * 3")
assert 2 == calculate("   6 / 3")
assert 6 == calculate(" 2 + 3 + 1 ")
assert 30 == calculate(" 12 + 3 + 15 ")
assert 4 == calculate("2 + 3 - 1")
assert 5 == calculate("2 + 3 * 1")
assert 11 == calculate("2 + 3 * 6 / 2")

//TODO
//assert 15 == calculate("(2 + 3) * 6 / 2")
//assert 30 == calculate("(2 + 3) * 2 * (1+ 2)")
