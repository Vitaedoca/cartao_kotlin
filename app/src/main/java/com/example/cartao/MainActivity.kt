package com.example.cartao

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cartao.R

class MainActivity : AppCompatActivity() {

    private lateinit var editCreditCardNumber: EditText // Declara o EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editCreditCardNumber = findViewById(R.id.editCreditCardNumber) // Inicializa o EditText

        // Configura a escuta de insets para o layout principal
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Adiciona TextWatcher ao EditText do número do cartão de crédito
        editCreditCardNumber.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val space = "   "

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Não faz nada
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Não faz nada
            }

            override fun afterTextChanged(s: Editable?) {
                // Verifica se a entrada não está nula
                if (s == null) return

                val str = s.toString().replace(" ", "") // Remove espaços
                val formatted = StringBuilder()

                // Formata o texto com espaços a cada 4 dígitos
                for (i in str.indices) {
                    if (i > 0 && i % 4 == 0) {
                        formatted.append(space)
                    }
                    formatted.append(str[i])
                }

                // Atualiza o EditText apenas se o texto formatado for diferente do atual
                if (formatted.toString() != current) {
                    current = formatted.toString()
                    editCreditCardNumber.setText(current)
                    editCreditCardNumber.setSelection(current.length) // Mantenha o cursor no final
                }
            }
        })
    }
}
