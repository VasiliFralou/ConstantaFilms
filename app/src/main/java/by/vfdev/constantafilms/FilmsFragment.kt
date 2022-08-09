package by.vfdev.constantafilms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.constantafilms.databinding.FragmentFilmsBinding
import by.vfdev.constantafilms.viewmodel.FilmsViewModel

class FilmsFragment : Fragment(R.layout.fragment_films) {

    private val filmsVM: FilmsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentFilmsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}