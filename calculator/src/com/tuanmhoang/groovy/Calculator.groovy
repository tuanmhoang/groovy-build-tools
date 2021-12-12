package com.tuanmhoang.groovy

operatorsAndBracket = ["\\+", "-", "*", "/", "(", ")"]
operators = ['+', '-', '*', '/']
openBracket = "("
closeBracket = ")"

def calculate(formulaString) {
  def calculatedString = formulaString.replace(" ", "")
  def numbersStack = []
  def operatorsStack = []
  StringBuilder number = new StringBuilder();
  def result = 0;
  for (i in 0..calculatedString.length()-1) {
    final def charAt = calculatedString.charAt(i)
    if (i == calculatedString.length() - 1) {
      number.append(charAt)
      result = calculateValue(numbersStack.pop(), operatorsStack.pop(), number.toString().toInteger())
      break
    }
    if (charAt.isDigit()) {
      number.append(calculatedString.charAt(i))
    } else {
      numbersStack.push(number.toString().toInteger())
      if (!operatorsStack.isEmpty()) {
        result = result + calculateValue(numbersStack.pop(), operatorsStack.pop(), numbersStack.pop())
        numbersStack.push(result)
      }
      number = new StringBuilder()
      operatorsStack.push(charAt)
    }
  }
  result
}

def calculateValue(number1, operator, number2) {
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

//TODO
//assert 4 == calculate("2 + 3 - 1")
//assert 5 == calculate("2 + 3 * 1")
//assert 11 == calculate("2 + 3 * 6 / 2")
//assert 15 == calculate("(2 + 3) * 6 / 2")
//assert 30 == calculate("(2 + 3) * 2 * (1+ 2)")
