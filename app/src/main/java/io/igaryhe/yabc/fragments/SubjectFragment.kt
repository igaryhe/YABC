package io.igaryhe.yabc.fragments


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.adapters.CrtCardAdapter
import io.igaryhe.yabc.adapters.StaffCardAdapter
import io.igaryhe.yabc.viewModels.SubjectMediumViewModel
import io.igaryhe.yabc.util.SubjectViewModelFactory
import kotlinx.android.synthetic.main.fragment_subject.*
import java.lang.Exception
import io.igaryhe.yabc.databinding.FragmentSubjectBinding
import io.igaryhe.yabc.models.*
import kotlinx.android.synthetic.main.fragment_subject.view.*


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
        binding.root.crt_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        binding.root.crt_list.adapter = CrtCardAdapter()
        binding.root.staff_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        binding.root.staff_list.adapter = StaffCardAdapter()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.subjectViewModel = mSubjectViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id
        imageView.transitionName = "$id"
        postponeEnterTransition()
        if (args.image != null) {
            Picasso.get().load(args.image).into(imageView, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }
            })
        } else {
            startPostponedEnterTransition()
        }
        setScoreDistribution()
        val adapter1 = crt_list.adapter as CrtCardAdapter
        mSubjectViewModel.crt.observe(this, Observer<List<Crt>> {
            adapter1.submitList(it)
        })
        val adapter2 = staff_list.adapter as StaffCardAdapter
        mSubjectViewModel.staff.observe(this, Observer<List<Staff>> {
            adapter2.submitList(it)
        })
    }

    private fun setScoreDistribution() {

        val xAxisValues = ArrayList<String>()
        xAxisValues.add("1")
        xAxisValues.add("2")
        xAxisValues.add("3")
        xAxisValues.add("4")
        xAxisValues.add("5")
        xAxisValues.add("6")
        xAxisValues.add("7")
        xAxisValues.add("8")
        xAxisValues.add("9")
        xAxisValues.add("10")

        barchart.xAxis.apply {
            granularity = 1f
            isGranularityEnabled = true
            setCenterAxisLabels(false)
            textSize = 9f
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = IndexAxisValueFormatter(xAxisValues)
            labelCount = 10
            mAxisMaximum = 10f
            setCenterAxisLabels(true)
            setAvoidFirstLastClipping(true)
            spaceMin = 4f
            spaceMax = 4f
            axisMinimum = 0f
            axisMaximum = 9f
            setDrawGridLines(false)
        }
        barchart.apply {
            axisLeft.setDrawGridLines(false)
            axisRight.setDrawGridLines(false)
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            axisLeft.axisMinimum = 0f
            axisRight.axisMinimum = 0f
            legend.isEnabled = false
            isDragEnabled = true
            description = null
            setFitBars(true)
        }

        val yValues = ArrayList<BarEntry>()

        // draw the graph
        var barDataSet: BarDataSet
        mSubjectViewModel.rating.observe(this, Observer<Rating> {
            t ->
            if (t != null) {
                yValues.add(BarEntry(0.5f, t.count._1.toFloat() / t.total.toFloat()))
                yValues.add(BarEntry(1.5f, t.count._2.toFloat() / t.total.toFloat()))
                yValues.add(BarEntry(2.5f, t.count._3.toFloat() / t.total.toFloat()))
                yValues.add(BarEntry(3.5f, t.count._4.toFloat() / t.total.toFloat()))
                yValues.add(BarEntry(4.5f, t.count._5.toFloat() / t.total.toFloat()))
                yValues.add(BarEntry(5.5f, t.count._6.toFloat() / t.total.toFloat()))
                yValues.add(BarEntry(6.5f, t.count._7.toFloat() / t.total.toFloat()))
                yValues.add(BarEntry(7.5f, t.count._8.toFloat() / t.total.toFloat()))
                yValues.add(BarEntry(8.5f, t.count._9.toFloat() / t.total.toFloat()))
                yValues.add(BarEntry(9.5f, t.count._10.toFloat() / t.total.toFloat()))
                barDataSet = BarDataSet(yValues, "")
                barDataSet.setColors(Color.GRAY)
                barDataSet.setDrawIcons(false)
                barDataSet.setDrawValues(false)
                val barData = BarData(barDataSet)
                barData.setValueFormatter(LargeValueFormatter())
                barData.barWidth = 0.8f
                barchart.apply {
                    data = barData
                    data.isHighlightEnabled = false
                    setVisibleYRange(0f, 1f, YAxis.AxisDependency.LEFT)
                    invalidate()
                }
            }
        })

    }
}