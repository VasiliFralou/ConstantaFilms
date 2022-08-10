package by.vfdev.constantafilms

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.constantafilms.databinding.FragmentFilmsBinding
import by.vfdev.constantafilms.ui.FilmsListAdapter
import by.vfdev.constantafilms.viewmodel.FilmsViewModel

class FilmsFragment : Fragment(R.layout.fragment_films) {

    private val filmsVM: FilmsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentFilmsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FilmsListAdapter(
            onClick = {
                filmsVM.onSelectFilms(it)
                val clickFilm = "Фильм \"${filmsVM.selectFilmsLD.value?.title.toString()}\" был нажат"
                viewDialog(clickFilm)
            }
        )

        binding.filmsRV.adapter = adapter
        binding.filmsRV.layoutManager = LinearLayoutManager(requireActivity())

        filmsVM.filmsLive.observe(viewLifecycleOwner) { list ->
            // Сортировка фильмов по году выпуска
            list.sortBy { it.releaseYear }
            adapter.updateData(list)
        }
    }

    private fun viewDialog(text: String) {
        AlertDialog.Builder(context)
            .setMessage(text)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}