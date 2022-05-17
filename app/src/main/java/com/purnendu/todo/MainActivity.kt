package com.purnendu.todo

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var taskEditText: EditText
    private lateinit var add: Button
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskEditText = findViewById(R.id.editTextTask)
        add = findViewById(R.id.add)
        linearLayout = findViewById(R.id.linearLayout)

        add.setOnClickListener {
            val cb = CheckBox(this@MainActivity)
            val text = taskEditText.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this@MainActivity, "Empty field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            cb.text = text
            linearLayout.addView(cb)
            taskEditText.setText("")
            cb.setOnCheckedChangeListener(this)
        }
    }

    override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            object : CountDownTimer(500, 100) {
                override fun onFinish() {
                    linearLayout.removeView(p0)
                    Toast.makeText(
                        this@MainActivity,
                        "Deleting thr ${p0?.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onTick(millisUntilFinished: Long) {
                    // millisUntilFinished    The amount of time until finished.
                }
            }.start()
            p0?.setOnCheckedChangeListener(null)
        }
    }
}