package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.acbutton.setOnClickListener {
            binding.input.text = ""
            binding.output.text = ""
        }

        binding.btn0.setOnClickListener {
            binding.input.text = addToInputText("0")
        }

        binding.btn1.setOnClickListener {
            binding.input.text = addToInputText("1")
        }

        binding.btn2.setOnClickListener {
            binding.input.text = addToInputText("2")
        }

        binding.btn3.setOnClickListener {
            binding.input.text = addToInputText("3")
        }

        binding.btn4.setOnClickListener {
            binding.input.text = addToInputText("4")
        }

        binding.btn5.setOnClickListener {
            binding.input.text = addToInputText("5")
        }

        binding.btn6.setOnClickListener {
            binding.input.text = addToInputText("6")
        }

        binding.btn7.setOnClickListener {
            binding.input.text = addToInputText("7")
        }

        binding.btn8.setOnClickListener {
            binding.input.text = addToInputText("8")
        }

        binding.btn9.setOnClickListener {
            binding.input.text = addToInputText("9")
        }

        binding.btndot.setOnClickListener {
            binding.input.text = addToInputText(".")
        }

        binding.plus.setOnClickListener {
            binding.input.text = addToInputText("+")
        }

        binding.minus.setOnClickListener {
            binding.input.text = addToInputText("-")
        }

        binding.multiply.setOnClickListener {
            binding.input.text = addToInputText("x")
        }

        binding.divide.setOnClickListener {
            binding.input.text = addToInputText("÷")
        }

        binding.percentage.setOnClickListener {
            binding.input.text = addToInputText("%")
        }

        binding.backspace.setOnClickListener {
            backspace()
        }

        binding.plusorminus.setOnClickListener {
            plusOrMinus()
        }

        binding.equal.setOnClickListener {
            showResults()
        }
    }

    private fun backspace() {

        if (binding.input.text.isNotBlank()) {
           binding.input.text = binding.input.text.dropLast(1)
        }
    }

    private fun plusOrMinus() {

        var expression = getInputExpression()
        val isFirstCharMinus = binding.input.text.startsWith("-")
        expression = if (isFirstCharMinus) {
            expression.removePrefix(("-"))
        } else {
            "-($expression)"
        }
        binding.input.text = expression
    }

    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("x"), "*")
        return expression
    }

    private fun showResults() {

        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                binding.output.text = "Error"
            } else {
                binding.output.text = DecimalFormat("0.############").format(result).toString()
            }
        } catch(e: Exception) {
           binding.output.text = "Error"
        }
    }

    private fun addToInputText(buttonValue: String): String {
        return "${binding.input.text}$buttonValue"
    }
}