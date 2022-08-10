package by.vfdev.constantafilms.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.constantafilms.R
import by.vfdev.constantafilms.databinding.ItemLayoutFilmsBinding
import by.vfdev.constantafilms.remotemodel.Actor
import by.vfdev.constantafilms.remotemodel.Films

class FilmsListAdapter(private val onClick: (films: Films) -> Unit) :
    RecyclerView.Adapter<FilmsListAdapter.ViewHolder>() {

    private val list: MutableList<Films> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding by viewBinding(ItemLayoutFilmsBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_layout_films, parent, false)

        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        val nameYears = "${item.title} " + "(${item.releaseYear} г.)"
        holder.binding.titleTV.text = nameYears

        // Иван Иванович Иванов
        val director = getFormatNameActor(item.directorName!!)
        holder.binding.directorNameTV.text = "Режиссёр: $director"

        val actor = getStringToList(item.actors)
        holder.binding.actorNameTV.text = "Актёры: $actor"
        holder.itemView.setOnClickListener {
            onClick.invoke(
                list[holder.bindingAdapterPosition]
            )
        }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Films>) {
        list.clear()
        list.addAll(newList)
        // Перерисовываем список т.к. данные поменялись
        notifyDataSetChanged()
    }

    private fun getStringToList(actors: List<Actor>): String {

        // Переобразование элементов списка
        val cityNames = actors.map { c -> c.actorName }

        // Удаление дубликатов списка
        val newList = cityNames.toSet().toList()

        // Перевод списка в строку и возврат строки
        return newList.joinToString(separator = ", ")
    }

    // nameList = Иван Иванович Иванов. Получиние в формате: Иванов И.И.
    private fun getFormatNameActor(nameList: String): String {

        val list = nameList.split(" ")

        val firstName = list[2]
        val secondName = list[1][0] + "."
        val theeName = list[0][0] + "."

        return "$firstName $secondName $theeName"
    }
}