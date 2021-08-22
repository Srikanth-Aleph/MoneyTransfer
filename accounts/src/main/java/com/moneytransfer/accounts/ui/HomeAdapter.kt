package com.moneytransfer.accounts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moneytransfer.accounts.R
import java.text.SimpleDateFormat
import java.util.*

import kotlinx.android.synthetic.main.item_account.view.*

class HomeAdapter() :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder<HomeItem>>() {

    var items = listOf<HomeItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    @Throws(RuntimeException::class)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder<HomeItem> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_account, parent, false)

        return AccountViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder<HomeItem>, position: Int) {
        holder.bind(items[position]) {
            //onItemClickListener?.invoke(it)
        }
    }

    abstract class HomeViewHolder<in T : HomeItem>(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T, onItemClickListener: ((String) -> Unit)? = null)
    }

    class AccountViewHolder(itemView: View) : HomeViewHolder<HomeItem>(itemView) {
        override fun bind(item: HomeItem, onItemClickListener: ((String) -> Unit)?) {
            val sdf = SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault())
            itemView.titleTextView.text = (item.accountName)
            itemView.descriptionTextView.text = (item.accountNumber)
            itemView.dateTextField.text = (item.availableBalance)
           // itemView.setOnClickListener { onItemClickListener?.invoke(item.account.id) }
        }
    }

}
