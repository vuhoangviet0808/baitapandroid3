package com.example.baitap2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView

    // Biến lớp
    var state: Int = 1  // 1 = đang nhập số đầu tiên, 2 = đang nhập số thứ hai, 3 = đang nhập số mới sau khi tính toán
    var op: Int = 0     // 1 = +, 2 = -, 3 = *, 4 = /
    var op1: Double = 0.0    // Số đầu tiên
    var op2: Double = 0.0    // Số thứ hai
    var result: Double = 0.0  // Lưu kết quả cho tính toán liên tục

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.textView6)

        // Thiết lập click listener cho các nút
        findViewById<Button>(R.id.button19).setOnClickListener(this)
        findViewById<Button>(R.id.button20).setOnClickListener(this)
        findViewById<Button>(R.id.button21).setOnClickListener(this)
        findViewById<Button>(R.id.button22).setOnClickListener(this)

        // Thiết lập các nút số và phép toán
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)

        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)
        findViewById<Button>(R.id.button11).setOnClickListener(this)
        findViewById<Button>(R.id.button12).setOnClickListener(this)
        findViewById<Button>(R.id.button13).setOnClickListener(this)
        findViewById<Button>(R.id.button15).setOnClickListener(this)
        findViewById<Button>(R.id.button16).setOnClickListener(this)
        findViewById<Button>(R.id.button17).setOnClickListener(this)

        findViewById<Button>(R.id.button18).setOnClickListener(this)
        findViewById<Button>(R.id.button14).setOnClickListener(this)
        findViewById<Button>(R.id.button10).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)

        findViewById<Button>(R.id.button22).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button19).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.button20 -> addDigit(0)
            R.id.button15 -> addDigit(1)
            R.id.button16 -> addDigit(2)
            R.id.button17 -> addDigit(3)
            R.id.button7 -> addDigit(7)
            R.id.button8 -> addDigit(8)
            R.id.button9 -> addDigit(9)
            R.id.button11 -> addDigit(4)
            R.id.button12 -> addDigit(5)
            R.id.button13 -> addDigit(6)

            R.id.button18 -> assignOperator(1)
            R.id.button14 -> assignOperator(2)
            R.id.button10 -> assignOperator(3)
            R.id.button6 -> assignOperator(4)

            R.id.button22 -> calculateResult()
            R.id.button4 -> clearAll()
            R.id.button3 -> clearEntry()
            R.id.button5 -> backspace()
            R.id.button19 -> toggleSign()
        }
    }

    private fun assignOperator(operator: Int) {
        if (state == 2) {
            calculateResult()
        }
        op = operator
        state = 2
    }

    private fun addDigit(digit: Int) {
        if (state == 1 ) {
            op1 = op1 * 10 + digit
            displayResult(op1)
            state = 1
        } else if(state == 2){
            op2 = op2 * 10 + digit
            displayResult(op2)
            state = 2
        } else {
            op1 = 0.0 + digit
            displayResult(op1)
            state = 1
        }
    }

    private fun calculateResult() {
        if(state == 1) result = op1
        else {
            when (op) {
                1 -> result = op1 + op2
                2 -> result = op1 - op2
                3 -> result = op1 * op2
                4 -> {
                    if (op2 != 0.0) {
                        result = op1 / op2
                    } else {
                        textResult.text = "Error"
                        return
                    }
                }
            }
        }
        displayResult(result)
        op1 = result
        op2 = 0.0
        state = 3
    }

    private fun displayResult(value: Double) {
        if (value == value.toInt().toDouble()) {
            textResult.text = "${value.toInt()}"
        } else {
            textResult.text = "$value"
        }
    }

    private fun clearAll() {
        op1 = 0.0
        op2 = 0.0
        op = 0
        state = 1
        textResult.text = "0"
        result = 0.0
    }

    private fun clearEntry() {
        if (state == 1) {
            op1 = 0.0
            textResult.text = "0"
        } else {
            op2 = 0.0
            textResult.text = "0"
        }
    }

    private fun backspace() {
        if (state == 1) {
            op1 = (op1 / 10).toInt().toDouble()
            displayResult(op1)
        } else {
            op2 = (op2 / 10).toInt().toDouble()
            displayResult(op2)
        }
    }

    private fun toggleSign() {
        if (state == 1) {
            op1 = -op1
            displayResult(op1)
        } else {
            op2 = -op2
            displayResult(op2)
        }
    }
}
