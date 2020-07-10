package ru.minnullin.authorization

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.ktor.utils.io.errors.IOException
import kotlinx.android.synthetic.main.activity_reg_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast
import ru.minnullin.R

class RegistrationActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_login)

        btn_register.setOnClickListener {
            val password = edt_registration_password.text.toString()
            val repeatedPassword = edt_registration_repeat_password.text.toString()
            if (password != repeatedPassword) {
                toast(getString(R.string.samePass))
            } else if (!isValid(password)) {
                toast(getString(R.string.IncorrectPass))
            } else {
                launch {
                    dialog =
                        indeterminateProgressDialog(
                            message = R.string.please_wait,
                            title = R.string.authentication
                        ) {
                            setCancelable(false)
                        }

                    try {
                        val responce =
                            Repository.register(
                                edt_registration_login.toString(),
                                password
                            )
                        if (responce.isSuccessful) {
                            toast(R.string.success)
                            setUserAuth(responce.body()!!.token)
                            finish()
                        } else {
                            toast(R.string.registration_failed)
                        }
                    } catch (e: IOException) {
                        toast(getString(R.string.noInternet))
                    } finally {
                        dialog?.dismiss()
                    }
                }
            }
        }
    }

    private fun setUserAuth(token: String) =
        getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE)
            .edit()
            .putString(AUTHENTICATED_SHARED_KEY, token)
            .commit()

    override fun onStop() {
        super.onStop()
        dialog?.dismiss()
        cancel()
    }
}