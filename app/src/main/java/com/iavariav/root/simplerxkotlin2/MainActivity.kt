package com.iavariav.root.simplerxkotlin2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        TODO cara kedua pakai RXBinding menambahkan Library di gradle
                RxTextView.textChanges(edtEmail)
                        .map{ t: CharSequence -> t.toString() }
                        .debounce(1000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { t: String? -> tvHasil.text = t }



//        TODO cara PERTAMA

//        Observable.create<String> { e ->
//
//        edtEmail.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(p0: Editable?) {
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
////                e.onNext(p0.toString())
//                if (edtEmail.text.isEmpty()){
//                    edtEmail.setError("kosong")
//                }
//                else if (edtEmail.text.toString().trim().length < 5){
//                    edtEmail.setError("Kurang")
//                }
//                else {
//                    e.onNext(p0.toString())
//                    tvHasil.setText("")
//
//                }
//            }
//
//        })
//        }
//                .debounce(1000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe { t: String? -> tvHasil.text = t }






        Observable.create<String> { t ->
            edtPassword.addTextChangedListener(object  : TextWatcher{
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (edtPassword.text.isEmpty()){
                        edtPassword.setError("kosong")
                    }
                    else if (edtPassword.text.toString().trim().length < 5){
                        edtPassword.setError("Kurang")
                    }
                    else {
                        t.onNext(p0.toString())
                        tvHasil.setText("")

                    }
                }

            })
        }.debounce(1000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe { t: String? -> tvHasil.text = t }

    }
}

