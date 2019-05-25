package io.igaryhe.yabc.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.R
import io.igaryhe.yabc.entities.SubjectMedium
import io.igaryhe.yabc.viewModels.SubjectMediumViewModel
import io.igaryhe.yabc.viewModels.SubjectViewModelFactory
import kotlinx.android.synthetic.main.fragment_subject.*


class SubjectFragment : Fragment() {
    val args: SubjectFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_subject, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id
        Picasso.get().load(args.image).into(imageView)
        val mSubjectMediumViewModel = ViewModelProviders
            .of(this, SubjectViewModelFactory(id))
            .get(SubjectMediumViewModel::class.java)
        mSubjectMediumViewModel.subjectMedium.observe(this, Observer<SubjectMedium>{
        })
    }
}