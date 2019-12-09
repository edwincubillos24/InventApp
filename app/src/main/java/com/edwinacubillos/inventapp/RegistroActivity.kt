package com.edwinacubillos.inventapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edwinacubillos.inventapp.utils.Constantes.Companion.EMPTY
import com.edwinacubillos.inventapp.utils.Constantes.Companion.INTERLIN
import com.edwinacubillos.inventapp.utils.Constantes.Companion.SPACE
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*

class RegistroActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()
    private lateinit var fecha : String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        var sexo = getString(R.string.masculino)
        //     val btn_registro : Button = findViewById(R.id.bt_registrar)

        rb_masculino.setOnClickListener {
            sexo = getString(R.string.masculino)
        }

        rb_femenino.setOnClickListener {
            sexo = getString(R.string.femenino)
        }

        val dataSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set( Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fecha = sdf.format(cal.time).toString()
                tv_showPicker.text = fecha
            }
        }

        tv_showPicker.setOnClickListener {
            DatePickerDialog(
                this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        bt_registrar.setOnClickListener {
            val nombre = et_nombre.text.toString()
            val correo = et_correo.text.toString()
            val password = et_password.text.toString()
            val repPassword = et_reppassword.text.toString()
            var pasatiempos = EMPTY

            if (cb_cine.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.cine)
            if (cb_gimnasio.isChecked) pasatiempos =
                pasatiempos + SPACE + getString(R.string.gimnasio)
            if (cb_leer.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.leer)
            if (cb_series.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.series)

            /*    if (rb_masculino.isChecked) sexo = "Masculino"
                else sexo = "Femenino"*/

            if (nombre.isEmpty() ||
                correo.isEmpty() ||
                et_telefono.text.toString().isEmpty() ||
                password.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    getString(R.string.msg_error_campos_vacios),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val telefono = et_telefono.text.toString().toInt()
                tv_resultado.text = getString(R.string.nombre_lb) + SPACE + nombre +
                        INTERLIN + getString(R.string.correo) + SPACE + correo +
                        INTERLIN + getString(R.string.telefono) + SPACE + telefono +
                        INTERLIN + getString(R.string.password_lb) + SPACE + password +
                        INTERLIN + getString(R.string.sexo) + SPACE + sexo +
                        INTERLIN + getString(R.string.pasatiempo) + SPACE + pasatiempos +
                        INTERLIN + getString(R.string.fecha_nacimiento) + SPACE + fecha
            }
        }
    }


}
