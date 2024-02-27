package kr.cosine.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import kr.cosine.bmicalculator.databinding.ActivityResultBinding
import java.text.DecimalFormat
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {

    private companion object {
        val decimalFormat = DecimalFormat("#,##0.###")
    }

    private val binding: ActivityResultBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val height = intent.getDoubleExtra("height", 0.0)
        val weight = intent.getDoubleExtra("weight", 0.0)

        val result = weight / (height / 100.0).pow(2.0)
        val resultMessage = when {
            result < 18.5 -> "저체중입니다!"
            18.5 <= result && result < 23.0 -> "정상입니다!"
            23.0 <= result && result < 25.0 -> "과체중입니다!"
            25.0 <= result && result < 30.0 -> "경도 비만입니다!"
            30.0 <= result && result < 35.0 -> "중정도 비만입니다!"
            else -> "고도 비만입니다!"
        }
        binding.result.text = decimalFormat.format(result)
        binding.resultMessage.text = resultMessage
    }
}