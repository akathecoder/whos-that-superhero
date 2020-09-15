package me.sparshagarwal.whosthatsuperhero.activities

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_search.*
import me.sparshagarwal.whosthatsuperhero.R
import me.sparshagarwal.whosthatsuperhero.models.SuperheroDataForList
import me.sparshagarwal.whosthatsuperhero.models.SuperheroDataList
import me.sparshagarwal.whosthatsuperhero.network.SuperheroApiService
import me.sparshagarwal.whosthatsuperhero.utils.Constants
import me.sparshagarwal.whosthatsuperhero.utils.SearchListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    private var superheroDataList: SuperheroDataList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        /** Hides Soft Keyboard When Focus Changed */
        search_bar_edit_text.setOnFocusChangeListener { view, b ->
            if (!search_bar_edit_text.hasFocus()) {
                val inputMethodManager =
                    ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        /** Action Listener for Search Bar */
        search_bar_edit_text.setOnEditorActionListener { _, action_id, _ ->
            if (action_id == EditorInfo.IME_ACTION_SEARCH) {

                getSuperheroList(search_bar_edit_text.text.toString())

            }
            false
        }


    }

    /** Function to set the System UI FULLSCREEN */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    /** Function to hide the System UI */
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

    }

    /** Function to show the System UI */
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    /** Function to get the Result(Superhero Data List) from the  API */
    fun getSuperheroList(query: String) {

        tv_search_results.visibility = View.GONE
        loadingBar.visibility = View.VISIBLE
        loadingBar.animation = AlphaAnimation(0f, 1f)
        search_bar_edit_text.isFocusable = false

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SuperheroApiService::class.java)
        val call: Call<SuperheroDataList> = service.getDataBySearch(query)


        call.enqueue(object : Callback<SuperheroDataList> {
            override fun onResponse(
                call: Call<SuperheroDataList>,
                response: Response<SuperheroDataList>
            ) {
                superheroDataList = response.body()
                search_bar_edit_text.isFocusable = true
                search_bar_edit_text.isFocusable = true
                search_bar_edit_text.isFocusableInTouchMode = true
                loadingBar.visibility = View.GONE
                loadingBar.animation = AlphaAnimation(1f, 0f)
                if (response.isSuccessful) {
                    Log.i("Data", superheroDataList.toString())
                    tv_search_results.visibility = View.VISIBLE
                    setupSetupRecyclerView()

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


            override fun onFailure(call: Call<SuperheroDataList>, t: Throwable) {
                Log.e("API Response Fail", t!!.message.toString())
            }
        })
    }

    /** Function to setup Recycler View */
    private fun setupSetupRecyclerView() {
        rv_search_results.layoutManager = GridLayoutManager(
            this, 2, GridLayoutManager.VERTICAL, false
        )


        Log.i("Next", "Got here")


        if (superheroDataList!!.response == "success") {
            tv_search_results.visibility = View.GONE
            rv_search_results.visibility = View.VISIBLE
            val superheroList: ArrayList<SuperheroDataForList> = superheroDataList!!.results

            val adapter = SearchListAdapter(superheroList, this)
            rv_search_results.adapter = adapter

//            rv_search_results.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener{
//                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//                    TODO("Not yet implemented")
//                }
//
//            })

            adapter.setOnClickListener(object : SearchListAdapter.OnClickListener {
                override fun onClick(position: Int, model: SuperheroDataForList) {
                    Log.i("SuperheroClicked", model.id.toString())
                    val intent = Intent(this@SearchActivity, SuperheroActivity::class.java)
                    intent.putExtra(SUPERHERO_ID, model.id.toString())
                    startActivity(intent)
                }
            })
        } else {
            tv_search_results.visibility = View.VISIBLE
            rv_search_results.visibility = View.INVISIBLE
        }


    }

    companion object {
        var SUPERHERO_ID = "superhero_id"
    }
}


// TODO("Find Why This is being Used")
private fun TextInputEditText.setOnEditorActionListener(setTextInputEditText: (TextView, Int, KeyEvent) -> Unit) {

}

