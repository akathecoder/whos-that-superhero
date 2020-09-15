package me.sparshagarwal.whosthatsuperhero.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_superhero.*
import me.sparshagarwal.whosthatsuperhero.R

class SuperheroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superhero)

        if (intent.hasExtra(SearchActivity.SUPERHERO_ID)){
            superhero_id.text =intent.getSerializableExtra(SearchActivity.SUPERHERO_ID) as String
        }

    }
}