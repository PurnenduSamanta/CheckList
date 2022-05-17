package com.purnendu.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var taskEditText: EditText
    private lateinit var add: Button
    private lateinit var linearLayout: LinearLayout
    private lateinit var checkBoxList: MutableList<CheckBox>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskEditText = findViewById(R.id.editTextTask)
        add = findViewById(R.id.add)
        linearLayout = findViewById(R.id.linearLayout)

        checkBoxList = mutableListOf()

        add.setOnClickListener {
            val cb = CheckBox(applicationContext)
            val text = taskEditText.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this@MainActivity, "Empty field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            cb.text = text
            linearLayout.addView(cb)
            checkBoxList.add(cb)
            taskEditText.setText("")
            cb.setOnCheckedChangeListener(this)
        }


    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        for (i in checkBoxList.indices) {
            val checkBox = checkBoxList[i]
            if (checkBox.isChecked) {
                linearLayout.removeView(checkBox)
//                Toast.makeText(
//                    this@MainActivity,
//                    "Deleting thr ${checkBox.text}",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }
    }
}