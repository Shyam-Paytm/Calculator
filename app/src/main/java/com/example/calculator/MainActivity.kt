package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.functions.Calculator

class MainActivity : AppCompatActivity() {

    private var result: String = "0"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textResult = binding.result

        val calculator = Calculator()
        calculator.setContext(this)

        // Handle All Numeric Operations here
        for (index in 0..9) {
            val button = when (index) {
                0 -> binding.num0
                1 -> binding.num1
                2 -> binding.num2
                3 -> binding.num3
                4 -> binding.num4
                5 -> binding.num5
                6 -> binding.num6
                7 -> binding.num7
                8 -> binding.num8
                else -> binding.num9
            }
            button.setOnClickListener {
                result = calculator.numeric(textResult.text.toString(), index)
                textResult.text = result
            }
        }


        // Handle Non numeric operation here
        for (index in 1..8) {
            val operationButton = when (index) {
                1 -> binding.sum
                2 -> binding.diff
                3 -> binding.multiply
                4 -> binding.divide
                5 -> binding.dot
                6 -> binding.delete
                7 -> binding.clear
                else -> binding.equal
            }
            // Add Event Listener on different operations
            operationButton.setOnClickListener {
                val value = textResult.text.toString()
                with(calculator) {
                    result = when (index) {
                        1 -> add(value)
                        2 -> subtract(value)
                        3 -> multiply(value)
                        4 -> divide(value)
                        5 -> dot(value)
                        6 -> deleteText(value)
                        7 -> clearText()
                        else -> evaluate(value)
                    }
                    textResult.text = result
                }
            }
        }

        // On Clicking History button,move to new Page
        binding.history.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

    }

    // When Orientation change, value will be sustained

    // Save Instance in Bundle
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", result)
    }

    // Get Instance from Bundle
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        result = savedInstanceState.getString("result", "0")
        findViewById<TextView>(R.id.result).text = result
    }
}

/*
1. Handled Basic Add, subtract, divide and multiply operation
2. Validate expression
3. Evaluate only if last char is numeric
4. On Orientation change, save value
5. Show Toast on Error during Evaluating

 */