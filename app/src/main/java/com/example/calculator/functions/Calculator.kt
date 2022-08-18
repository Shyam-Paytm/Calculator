package com.example.calculator.functions

import android.content.Context
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class Calculator {

    // If LastNumeric false, disable all operations except numeric
    private var lastNumeric: Boolean = true

    // If last is dot, then disable all operations except numeric
    private var lastDot: Boolean = false

    private var context: Context? = null

    fun setContext(con: Context) {
        context = con
    }

    fun clearText(): String {
        lastNumeric = true
        lastDot = false
        return "0"
    }

    fun deleteText(value: String): String {
        // To check if dot is present in last number
        val tempLastDot = if (value.last() == '.') false else lastDot
        lastNumeric = false
        lastDot = false
        val newValue = when (value.length) {
            0 -> "0"
            else -> value.substring(0, value.length - 1)
        }
        // Change constraints as per Last char
        if (newValue.last().isDigit()) lastNumeric = true
        // If last numeric like 77.999 then also do not allow dot
        else if (newValue.last() == '.' || tempLastDot) lastDot = true
        return newValue
    }

    fun evaluate(value: String): String {
        // If last char is Digit, then only Evaluate
        try {
            if (lastNumeric) {
                lastNumeric = true
                lastDot = true
                val expression = ExpressionBuilder(value).build()
                return expression.evaluate().toString()
            }
        } catch (err: ArithmeticException) {
            // Show Toast on Error
            Toast(context).apply {
                setText("Enter Right Expression")
                duration = Toast.LENGTH_SHORT
                show()
            }
        }
        return value
    }

    fun divide(value: String): String {
        // if last is not numeric, do not add sign
        if (!lastNumeric) return value
        lastDot = false
        lastNumeric = false
        return "$value/"
    }

    fun multiply(value: String): String {
        // if last is not numeric, do not add sign
        if (!lastNumeric) return value
        lastDot = false
        lastNumeric = false
        return "$value*"
    }

    fun add(value: String): String {
        // if last is not numeric, do not add sign
        if (!lastNumeric) return value
        lastDot = false
        lastNumeric = false
        return "$value+"
    }

    fun subtract(value: String): String {
        // if last is not numeric, do not add sign
        if (!lastNumeric) return value
        lastDot = false
        lastNumeric = false
        if (value == "0") return "-"
        return "$value-"
    }

    fun dot(value: String): String {
        // If last is dot then do not add in value
        if (lastDot) return value
        lastDot = true
        lastNumeric = false
        return "$value."
    }

    fun numeric(value: String, num: Int): String {
        lastNumeric = true
        if (value == "0") return num.toString()
        return value + num.toString()
    }
}