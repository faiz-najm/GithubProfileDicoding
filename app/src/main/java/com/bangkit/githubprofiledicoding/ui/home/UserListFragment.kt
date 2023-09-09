package com.bangkit.githubprofiledicoding.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.githubprofiledicoding.adapters.UserListAdapter
import com.bangkit.githubprofiledicoding.data.remote.response.User
import com.bangkit.githubprofiledicoding.data.remote.retrofit.ApiStatus
import com.bangkit.githubprofiledicoding.databinding.FragmentUserListBinding
import com.bangkit.githubprofiledicoding.viewmodels.UserListViewModel

class UserListFragment : Fragment(), UserListAdapter.OnItemClickListener {

    private val userListViewModel: UserListViewModel by lazy {
        ViewModelProvider(this)[UserListViewModel::class.java]
    }

    private lateinit var myAdapter: UserListAdapter

    private var _binding: FragmentUserListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        userListViewModel.apiSatus.observe(viewLifecycleOwner) {
            updateProgress(it)
        }

        userListViewModel.listUser.observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = UserListAdapter()
        myAdapter.setOnItemClickListener(this)

        with(binding) {
            rvUsers.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            rvUsers.adapter = myAdapter
            rvUsers.setHasFixedSize(true)

            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->

                    if (actionId == 3) {
                        userListViewModel.searchUser(textView.text.toString())
                        true
                    } else

                        false
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUserListData(userList: List<User>) {
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }

            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onItemClick(user: User) {
        TODO("Not yet implemented")
    }
}