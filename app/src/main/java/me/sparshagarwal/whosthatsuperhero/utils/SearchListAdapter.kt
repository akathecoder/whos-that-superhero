package me.sparshagarwal.whosthatsuperhero.utils

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_result_recycler_view_style.view.*
import me.sparshagarwal.whosthatsuperhero.R
import me.sparshagarwal.whosthatsuperhero.models.SuperheroDataForList

class SearchListAdapter(val items: ArrayList<SuperheroDataForList>, val context: Context) :
    RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.iv_search_results
        val title = view.tv_song_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.search_result_recycler_view_style, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model: SuperheroDataForList = items[position]

        holder.title.text = model.name
        Picasso.get()
            .load(Uri.parse(model.image.url.toString()))
            .into(holder.image)


    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(position: Int, model: SuperheroDataForList)
    }

}