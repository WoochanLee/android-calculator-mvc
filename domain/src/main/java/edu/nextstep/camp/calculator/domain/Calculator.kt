package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.ArithmeticSign.Companion.calculate

object Calculator {

    fun calculateFormula(formula: String): Double {
        if (formula.isEmpty()) {
            throw IllegalArgumentException()
        }

        val initialOperandWithOperations = InputFormulaClassifier.makeInitialOperandWithOperationsFromFormula(formula)

        var firstOperand = initialOperandWithOperations.first
        val operations = initialOperandWithOperations.second

        operations.forEach { (sign, secondOperand) ->
            val arithmeticSign = ArithmeticSign.convertToArithmeticOperation(sign)
            firstOperand = arithmeticSign.calculate(
                firstOperand,
                secondOperand.toDouble()
            )
        }

        return firstOperand
    }

    fun isRoundedNumber(number: Double): Boolean {
        val remainder = number.rem(-1)
        return remainder == 0.0
    }

    fun isNumber(content: String): Boolean {
        return content.toDoubleOrNull()?.let {
            true
        } == true
    }
}
