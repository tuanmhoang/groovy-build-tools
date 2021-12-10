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
}

assert 5 == calculate("2+ 3")
assert 2 == calculate("5 - 3")