package com.example.kotlinnavigation.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.kotlinnavigation.Models.Arts
import com.example.kotlinnavigation.Models.ArtsDatabase
import com.example.kotlinnavigation.R
import com.example.kotlinnavigation.databinding.FragmentAddBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class AddFragment : Fragment() {
    lateinit var  db : ArtsDatabase
    private var _binding: FragmentAddBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    var compositeDisposable= CompositeDisposable();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        db = Room.databaseBuilder(
            requireContext().applicationContext,
            ArtsDatabase::class.java, "Arts"
        ).fallbackToDestructiveMigration()
            .build();
        addEvent();

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
    private  fun addEvent(){
        binding.addBtn.setOnClickListener {
            val art= Arts(binding.editTextName.text.toString(),binding.editTextDescription.text.toString(),binding.editTextDate.text.toString())
            val artsDao=db.artsDao();
            compositeDisposable.add(
                artsDao.insert(art)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleAddResponse)
            )
        }
    }
    private  fun handleAddResponse(){
        findNavController().navigate(R.id.action_addFragment_to_home)
    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }


}