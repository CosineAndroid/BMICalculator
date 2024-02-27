package kr.cosine.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import kr.cosine.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.generate.setOnClickListener {
            val heightText = getStringOrNullWithMessage(binding.heightInput, "신장") ?: return@setOnClickListener
            val weightText = getStringOrNullWithMessage(binding.weightInput, "체중") ?: return@setOnClickListener
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", heightText.toDouble())
            intent.putExtra("weight", weightText.toDouble())
            startActivity(intent)
        }
    }

    private fun getStringOrNullWithMessage(editText: EditText, type: String): String? {
        val text = editText.text.toString()
        if (text.isEmpty()) {
            Toast.makeText(this, "${type}을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return null
        }
        return text
    }
}