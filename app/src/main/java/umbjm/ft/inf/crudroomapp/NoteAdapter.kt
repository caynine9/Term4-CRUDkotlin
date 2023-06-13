package umbjm.ft.inf.crudroomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import umbjm.ft.inf.crudroomapp.room.Note

class NoteAdapter(private var notes: ArrayList<Note>, private val listener: OnAdapterListener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.textNIM.text = note.NIM

        holder.icEdit.setOnClickListener {
            listener.onUpdate(note)
        }

        holder.icDelete.setOnClickListener {
            listener.onDelete(note)
        }

        holder.itemView.setOnClickListener {
            listener.onRead(note)
        }
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textNIM: TextView = view.findViewById(R.id.text_nim)
        val icEdit: ImageView = itemView.findViewById(R.id.icon_edit)
        val icDelete: ImageView = itemView.findViewById(R.id.icon_delete)
    }


    fun setData(newList: List<Note>) {
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onRead(note: Note)
        fun onUpdate(note: Note)
        fun onDelete(note: Note)
    }
}
