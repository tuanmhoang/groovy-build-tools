package com.tuanmhoang.groovy

operatorsAndBracket = ["\\+", "-", "*", "/", "(", ")"]
operators = ["+", "-", "*", "/"]
openBracket = "("
closeBracket = ")"

def calculate(formulaString) {
  operator = operators.find(o -> formulaString.contains(o));
  print(operator)
  if(operator == "+"){
    operator = "\\+"
    numbers = formulaString.trim().split(operator)
    return numbers[0].toInteger() + numbers[1].toInteger()
  }
  if(operator == "-"){
    numbers = formulaString.trim().split(operator)
    return numbers[0].toInteger() - numbers[1].toInteger()
  }
  if(operator == "*"){
    operator = "\\*"
    numbers = formulaString.trim().split(operator)
    return numbers[0].toInteger() * numbers[1].toInteger()
  }
  if(operator == "/"){
    numbers = formulaString.trim().split(operator)
    return numbers[0].toInteger() / numbers[1].toInteger()
  }
}

assert 5 == calculate("2+ 3")
assert 2 == calculate("5 - 3")
assert 15 == calculate("   5 * 3")
assert 2 == calculate("   6 / 3")

//TODO
//assert 6 == calculate("2 + 3 + 1")
//assert 4 == calculate("2 + 3 - 1")
//assert 5 == calculate("2 + 3 * 1")
//assert 11 == calculate("2 + 3 * 6 / 2")
//assert 15 == calculate("(2 + 3) * 6 / 2")
//assert 30 == calculate("(2 + 3) * 2 * (1+ 2)")
