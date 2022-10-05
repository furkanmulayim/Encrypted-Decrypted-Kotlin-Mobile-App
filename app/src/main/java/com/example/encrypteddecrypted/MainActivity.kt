/**
 *  Created by Furkan Mülayim
 *   185541043
 *
 * */
package com.example.encrypteddecrypted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.encrypteddecrypted.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var keys =
        "abcçdefgğhıijklmnoöprsştuüwvxqyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜWVXQYZ ?!.,:;-*/+_123456789'"
    var metin: String = ""
    var sifrelenenMetin: String = ""
    var cozulenMetin: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sifrelenecekVeriParcasi()
        sifrelenenVeriparcasi()
    }

    /** Şifrelenecek veriyi alt parçalara bölerek sifreleme fonksiyonuna gönderir */
    fun sifrelenecekVeriParcasi() {
        binding.buttonSifrele.setOnClickListener {
            metin = binding.sifrelenecekMetin.text.toString()
            for (i in metin) {
                sifrelenenMetin = sifrelenenMetin + algoEncrypt(i)
            }
            binding.cozulecekMetin.setText(sifrelenenMetin)
            Toast.makeText(applicationContext,"Metin Şifrelendi: ${sifrelenenMetin}",Toast.LENGTH_LONG).show()
            binding.sifrelenecekMetin.setText("")
            metin = ""
            sifrelenenMetin = ""
        }
    }

    /**  Şifreleme Fonksiyonu */
    fun algoEncrypt(x: Char): String {
        var y: String = "00"
        var sayac = 10
        for (i in keys) {
            if (x.equals(i)) {
                y = sayac.toString()
            }
            sayac++
        }
        return y
    }

    /** Şifrelenen veriyi alt parçalara bölerek deşifreleme fonksiyonuna gönderir */
    fun sifrelenenVeriparcasi() {
        binding.buttonCozme.setOnClickListener {
            metin = binding.cozulecekMetin.text.toString()
            var altParca = ""
            for (k in metin) {
                altParca = altParca + k.toString()
                if (altParca.length == 2) {
                    cozulenMetin = cozulenMetin + algoDecrypt(altParca)
                    altParca = ""
                }
            }
            binding.sifrelenecekMetin.setText(cozulenMetin)
            Toast.makeText(applicationContext,"Metin Çözüldü: ${cozulenMetin}",Toast.LENGTH_LONG).show()
            binding.cozulecekMetin.setText("")
            cozulenMetin = ""
            metin = ""
        }
    }

    /**  Deşifreleme fonksiyonu*/
    fun algoDecrypt(x: String): String {
        var sayac = 10
        var y: String = ""
        for (a in keys) {
            if (x == sayac.toString()) {
                y = a.toString()
                break
            } else {
                sayac++
            }
        }
        return y
    }

}