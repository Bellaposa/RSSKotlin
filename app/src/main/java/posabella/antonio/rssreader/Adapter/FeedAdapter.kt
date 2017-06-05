package posabella.antonio.rssreader.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import posabella.antonio.rssreader.Interface.ItemClickListener
import posabella.antonio.rssreader.Model.RSSObject
import posabella.antonio.rssreader.R

/**
 * Created by antonioposabella on 03/06/17.
 */

class FeedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener,View.OnLongClickListener {

    var txtTitle:TextView
    var txtPubdate:TextView
    var txtContent:TextView

    private var itemClickListener : ItemClickListener?=null

    init {
        txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
        txtPubdate = itemView.findViewById(R.id.txtPubdate) as TextView
        txtContent = itemView.findViewById(R.id.txtContent) as TextView

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onClick(p0: View?) {
            itemClickListener!!.onClick(p0,adapterPosition,false)
    }

    override fun onLongClick(p0: View?): Boolean {
        itemClickListener!!.onClick(p0,adapterPosition,true)
        return true
    }


}


class FeedAdapter(private val rssObject: RSSObject,private val mContext:Context): RecyclerView.Adapter<FeedViewHolder>(){

    private val inflater:LayoutInflater

    init {
        inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedViewHolder {
        val itemView = inflater.inflate(R.layout.row,parent,false)
        return FeedViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return rssObject.items.size

    }

    override fun onBindViewHolder(holder: FeedViewHolder?, position: Int) {
        holder?.txtTitle?.text = rssObject.items[position].title
        holder?.txtContent?.text = rssObject.items[position].content
        holder?.txtPubdate?.text = rssObject.items[position].pubDate


        holder?.setItemClickListener(ItemClickListener { view, position, isLongClick ->

            if(!isLongClick){

                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.items[position].link))
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(browserIntent)
            }


        })

    }


}