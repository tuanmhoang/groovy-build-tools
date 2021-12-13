package com.tuanmhoang.groovy

openBracket = '('
closeBracket = ')'

def calculate(formulaString) {
  def calculatedString = formulaString.replace(" ", "")
  def numbersStack = []
  def operatorsStack = []
  StringBuilder number = new StringBuilder();
  def result = 0;
  def shouldCalculate = false
  def parenthesisCount = formulaString.length() - formulaString.replaceAll("\\)", "").length()
  for (i in 0..calculatedString.length() - 1) {
    final def charAt = calculatedString.charAt(i)
    if (charAt == closeBracket) {

      //StringBuilder insideParenthesisString = new StringBuilder()
      numbersStack.push(number.toString().toInteger())
      def finalResult = []
      if (numbersStack.size() > 1) {
        char op = operatorsStack.pop()
        //numbersStack.push(number.toString().toInteger())
        number = new StringBuilder()
//        insideParenthesisString <<= numbersStack.pop()
        finalResult.push(numbersStack.pop())
        while (op != openBracket) {
//          insideParenthesisString <<= op
//          insideParenthesisString <<= numbersStack.pop()
          finalResult.push(op)
          finalResult.push(numbersStack.pop())
          if (operatorsStack.size() > 0) {
            op = operatorsStack.pop()
          }
        }
//        def toBeCalculatedString = insideParenthesisString.reverse().toString()
        def toBeCalculatedString = finalResult.join("")
        def r = calculateWithSimpleInput(toBeCalculatedString)
        numbersStack.push(r)
        parenthesisCount--
        continue
      }
//      numbersStack.push(number.toString().toInteger())
//      char op = operatorsStack.pop()
//      while (!operatorsStack.isEmpty() && op != openBracket) {
//        result = calculateValue(numbersStack.pop(), op, numbersStack.pop())
//        numbersStack.push(result)
//        op = operatorsStack.pop()
//      }
//      number = new StringBuilder()
//      parenthesisCount --
//      continue
    }
    if (i == calculatedString.length() - 1) {
      number <<= charAt
      numbersStack.push(number.toString().toInteger())
      while (!operatorsStack.isEmpty()) {
        result = calculateValue(numbersStack.pop(), operatorsStack.pop(), numbersStack.pop())
        numbersStack.push(result)
      }
      break
    }
    if (charAt.isDigit()) {
      number <<= calculatedString.charAt(i)
    } else {
      if (charAt != openBracket && charAt != closeBracket) {
        if (!number.toString().isEmpty()) {
          numbersStack.push(number.toString().toInteger())
        }
      }
      if (shouldCalculate) {
        result = calculateValue(numbersStack.pop(), operatorsStack.pop(), numbersStack.pop())
        numbersStack.push(result)
        shouldCalculate = false
      }
      if (parenthesisCount == 0 && isHighPriority(charAt)) {
        shouldCalculate = true
      }
      number = new StringBuilder()
      operatorsStack.push(charAt)
    }
  }

  if (numbersStack.size() == 1) {
    return numbersStack.pop()
  }

  def finalResult = []
  if (numbersStack.size() > 1) {
    char op = operatorsStack.pop()
    finalResult.push(numbersStack.pop())
    while (!numbersStack.isEmpty()) {
      finalResult.push(op)
      finalResult.push(numbersStack.pop())
      if (operatorsStack.size() > 0) {
        op = operatorsStack.pop()
      }
    }
    def toBeCalculatedString = finalResult.join("")
    return calculateWithSimpleInput(toBeCalculatedString)
  }
  return result
}

def reverseNumbersAndOperators(stringToReverse) {

}

def calculateWithSimpleInput(formulaString) {
  def numbersStack = []
  def operatorsStack = []
  StringBuilder number = new StringBuilder();
  def result = 0;
  def shouldCalculate = false
  for (i in 0..formulaString.length() - 1) {
    // get current character
    final def charAt = formulaString.charAt(i)
    if (i == formulaString.length() - 1) {
      number.append(charAt)
      numbersStack.push(number.toString().toInteger())
      while (!operatorsStack.isEmpty()) {
        result = calculateValue(numbersStack.pop(), operatorsStack.pop(), numbersStack.pop())
        numbersStack.push(result)
      }
      break
    }
    if (charAt.isDigit()) {
      number.append(formulaString.charAt(i))
    } else {
      if (!number.toString().isEmpty()) {
        numbersStack.push(number.toString().toInteger())
      }
      if (shouldCalculate) {
        result = calculateValue(numbersStack.pop(), operatorsStack.pop(), numbersStack.pop())
        numbersStack.push(result)
        shouldCalculate = false
      }

      if (isHighPriority(charAt)) {
        shouldCalculate = true
      }
      number = new StringBuilder()
      operatorsStack.push(charAt)
    }
  }
  result
}

def isHighPriority(operator) {
  if (operator == '*' || operator == '/') {
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
assert 9 == calculate("2 * 3 + 6 / 2")
assert 15 == calculate("(2 + 3) * 6 / 2")
assert 81 == calculate("(1 + 2) * 3 * (4+ 5)")
assert 700 == calculate("(1 * 2 + 3) * 4 * (5+ 5*6)")
