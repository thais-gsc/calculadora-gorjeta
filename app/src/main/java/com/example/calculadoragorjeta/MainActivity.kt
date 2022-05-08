package com.example.calculadoragorjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    private lateinit var txtTotalConta : EditText
    private lateinit var txtPessoas : EditText
    private lateinit var skGorjeta : SeekBar
    private val formatador = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTotalConta = this.findViewById<EditText>(R.id.txtTotalConta)
        txtTotalConta.setOnFocusChangeListener(this)

        txtPessoas = this.findViewById<EditText>(R.id.txtPessoas)
        txtPessoas.setOnFocusChangeListener(this)

        skGorjeta = this.findViewById<SeekBar>(R.id.skGorjeta)
        skGorjeta.setOnSeekBarChangeListener(this)

    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        this.atualizaDadosConta()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        var lblGorjeta = this.findViewById<TextView>(R.id.lblGorjeta)
        lblGorjeta.setText(skGorjeta.progress.toString() + "%")

        this.atualizaDadosConta()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    private fun atualizaDadosConta() {
        if (txtTotalConta.text.toString().isNotEmpty() && txtPessoas.text.toString().isNotEmpty()) {

            var valorConta = txtTotalConta.text.toString().toDouble()
            var qtdPessoas = txtPessoas.text.toString().toInt()

            var lblValorGorjeta = this.findViewById<TextView>(R.id.lblValorGorjeta)
            var valorGorjeta = valorConta * skGorjeta.progress / 100
            lblValorGorjeta.setText(formatador.format(valorGorjeta))

            var lblValorTotal = this.findViewById<TextView>(R.id.lblValorTotal)
            lblValorTotal.setText(formatador.format(valorConta + valorGorjeta))
            var lblTotalPessoa = this.findViewById<TextView>(R.id.lblTotalPessoa)
            lblTotalPessoa.setText(formatador.format((valorConta + valorGorjeta) / qtdPessoas))
        }
    }
}

