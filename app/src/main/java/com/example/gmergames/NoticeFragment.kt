package com.example.gmergames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.example.gmergames.databinding.FragmentNoticeBinding

//FRAMGENTO QUE CONTIENE EL VIEWPAGER.
//EN LA PARTE INFERIOR ESTÁ DECLARADA LA CLASE ADAPTADOR DEL VIEWPAGER
class NoticeFragment : Fragment() {

    private var _binding : FragmentNoticeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    //se crean todos los elementos por primera vez
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNoticeBinding.inflate(inflater,container,false)
        return binding.root
    }

    //todos los elementos ya están creados previamente
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpNotice.adapter = NoticeAdapter(this)
	
	//ACTIVAR LAS PESTAÑAS CON SCROLL
	//binding.tabNotice.tabMode = TabLayout.MODE_SCROLLABLE

	//GENERA UNA PESTAÑA POR CADA ELEMENTO DEL VIEWPAGER. EN ESTE CASO: 2.
        TabLayoutMediator(binding.tabNotice, binding.vpNotice) { tab, position ->
            tab.text = when (position) {
                0 -> "Primero"
                else -> "Segundo"
            }
        }.attach()
    }
}

//ADAPTADOR PARA EL VIEWPAGER.
class NoticeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

	//NUMERO DE ELEMENTOS O FRAGMENTOS A CARGAR. EN ESTE CASO:2.
	//PODRÍA TOMAR LOS VALORES DEL TAMAÑO DE UNA LISTA, POR EJEMPLO.
    override fun getItemCount(): Int = 2

	//FUNCIÓN PARA CREAR LOS FRAGMENTOS (ASOCIAR). SEGÚN LA POSICIÓN RETORNARÁ UN FRAGMENTO U OTRO.
	//SE PODRÍA AUTOMATIZAR Y QUE SE LLAME A UN MISMO FRAGMENTO AL QUE LE LLEGAN DATOS QUE VARÍAN.
    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = if(position==0)
            Tab1NoticeFragment()
        else
            Tab2NoticeFragment()

        return fragment
    }
}
