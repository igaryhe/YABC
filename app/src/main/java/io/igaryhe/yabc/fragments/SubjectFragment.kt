package io.igaryhe.yabc.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.R
import io.igaryhe.yabc.entities.SubjectMedium
import io.igaryhe.yabc.viewModels.SubjectMediumViewModel
import io.igaryhe.yabc.util.SubjectViewModelFactory
import kotlinx.android.synthetic.main.fragment_subject.*
import java.lang.Exception
import io.igaryhe.yabc.databinding.FragmentSubjectBinding


class SubjectFragment : Fragment() {
    private lateinit var binding: FragmentSubjectBinding
    private lateinit var mSubjectViewModel: SubjectMediumViewModel
    val args: SubjectFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubjectBinding.inflate(inflater, container, false)
        mSubjectViewModel = ViewModelProviders
            .of(this, SubjectViewModelFactory(args.id))
            .get(SubjectMediumViewModel::class.java)
        binding.lifecycleOwner = this@SubjectFragment
        binding.subjectViewModel = mSubjectViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id
        imageView.transitionName = "$id"
        postponeEnterTransition()
        Picasso.get().load(args.image).into(imageView, object: Callback {
            override fun onSuccess() {
                startPostponedEnterTransition()
            }
            override fun onError(e: Exception?) {
                startPostponedEnterTransition()
            }
        })
    }
}