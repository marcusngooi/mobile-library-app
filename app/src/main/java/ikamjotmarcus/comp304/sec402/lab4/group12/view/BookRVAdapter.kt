package ikamjotmarcus.comp304.sec402.lab4.group12.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ikamjotmarcus.comp304.sec402.lab4.group12.R
import ikamjotmarcus.comp304.sec402.lab4.group12.entity.Book

class BookRVAdapter(
    val context: Context,
    private val bookClickDeleteInterface: BookClickDeleteInterface,
    private val bookClickInterface: BookClickInterface
) : RecyclerView.Adapter<BookRVAdapter.ViewHolder>() {
    private val allBooks = ArrayList<Book>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookNameTextView: TextView = itemView.findViewById(R.id.tv_book_name)
        val authorNameTextView: TextView = itemView.findViewById(R.id.tv_author_name)
        val deleteButtonImageView: ImageView = itemView.findViewById(R.id.iv_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_book_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bookNameTextView.text = allBooks[position].bookName
        holder.authorNameTextView.text = allBooks[position].authorName
        holder.deleteButtonImageView.setOnClickListener{
            bookClickDeleteInterface.onDeleteIconClick(allBooks[position])
        }

        holder.itemView.setOnClickListener{
            bookClickInterface.onBookClick(allBooks[position])
        }
    }

    override fun getItemCount(): Int {
        return allBooks.size
    }

    fun updateList(newList: List<Book>) {
        allBooks.clear()
        allBooks.addAll(newList)
        notifyDataSetChanged()
    }
}

interface BookClickDeleteInterface {
    fun onDeleteIconClick(book: Book)
}

interface BookClickInterface {
    fun onBookClick(book: Book)
}