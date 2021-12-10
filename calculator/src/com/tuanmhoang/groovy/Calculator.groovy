package com.tuanmhoang.groovy

def operatorsAndBracket = ["+", "-", "*", "/", "(", ")"]
def operators = ["+", "-", "*", "/"]
def openBracket = "("
def closeBracket = ")"

def calculate(formulaString) {
  numbers = formulaString.split("\\+")
  numbers[0].toInteger() + numbers [1].toInteger()
}

assert 5 == calculate("2+3")