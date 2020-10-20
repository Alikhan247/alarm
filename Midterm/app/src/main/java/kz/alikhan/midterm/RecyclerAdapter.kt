package kz.alikhan.midterm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(
    private var titles: List<String>,
    private var details: List<String>,
    private var status: List<Boolean>
) :
RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.tv_title)
        val itemDetails: TextView = itemView.findViewById(R.id.tv_description)
        val itemStatus: SwitchCompat = itemView.findViewById(R.id.tv_status)

        init {
            itemView.setOnClickListener{ v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "${position + 1}", Toast.LENGTH_SHORT).show()

            }
            itemView.setOnLongClickListener{

                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "${position + 1}", Toast.LENGTH_SHORT).show()

                return@setOnLongClickListener true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_lyout,
            parent,
            false
        )
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetails.text = details[position]
        holder.itemStatus.isChecked = status[position]

        holder.itemStatus.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            holder.itemTitle.text = isChecked.toString()
        })

    }

    override fun getItemCount(): Int {
        return titles.size
    }

}