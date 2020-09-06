package me.sparshagarwal.whosthatsuperhero.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import me.sparshagarwal.whosthatsuperhero.R
import me.sparshagarwal.whosthatsuperhero.models.SuperheroData
import me.sparshagarwal.whosthatsuperhero.network.SuperheroApiService
import me.sparshagarwal.whosthatsuperhero.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        testFun()

        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun testFun() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SuperheroApiService::class.java)
        val call: Call<SuperheroData> = service.getDataById("69")

        call.enqueue(object : Callback<SuperheroData> {
            override fun onResponse(call: Call<SuperheroData>, response: Response<SuperheroData>) {
                if (response.isSuccessful) {
                    val superhero = response.body()
                    Log.i("Data", superhero.toString())
                } else {
                    when (response.code()) {
                        400 -> {
                            Log.e("Error 400", "Bad Request")
                        }
                        404 -> {
                            Log.e("Error 404", "Not Found")
                        }
                        else -> {
                            Log.e("Error", "Generic Error")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<SuperheroData>, t: Throwable) {
                Log.e("Errorrrrr", t!!.message.toString())
            }

        })

    }

}