package com.example.kotlinnavigation.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.kotlinnavigation.ArtsAdapter
import com.example.kotlinnavigation.Models.Arts
import com.example.kotlinnavigation.Models.ArtsDao
import com.example.kotlinnavigation.Models.ArtsDatabase
import com.example.kotlinnavigation.R
import com.example.kotlinnavigation.databinding.FragmentAddBinding
import com.example.kotlinnavigation.databinding.FragmentHomeBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io


class HomeFragment : Fragment() {
    lateinit var  db : ArtsDatabase
    var compositeDisposable=CompositeDisposable();
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        db = databaseBuilder(
            requireContext().applicationContext,
            ArtsDatabase::class.java, "Arts"
        ).fallbackToDestructiveMigration()
            .build();

        val artsDao=db.artsDao();


        compositeDisposable.add(
            artsDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)

        )

        return view;
    }

    private fun handleResponse(artList : List<Arts>){
        binding.artsRecycler.layoutManager=LinearLayoutManager(activity)
        val adapter=ArtsAdapter(artList)
        binding.artsRecycler.adapter=adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }


}