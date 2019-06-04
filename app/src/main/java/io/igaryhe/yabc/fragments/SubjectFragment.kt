package io.igaryhe.yabc.fragments


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
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
        binding.lifecycleOwner = viewLifecycleOwner
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
        setScoreDistribution()
        val adapter = crt_list.adapter as CrtCardAdapter
        mSubjectViewModel.crt.observe(this, Observer<List<Crt>> {
            adapter.submitList(it)
        })
    }

    private fun setScoreDistribution() {

        var barChartView = barchart

        var xAxisValues = ArrayList<String>()
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


        var yValueGroup1 = ArrayList<BarEntry>()

        // draw the graph
        var barDataSet1: BarDataSet

        barChartView.xAxis.axisMinimum = 0f
        barChartView.xAxis.axisMaximum = 9f
        barChartView.setFitBars(true)
        mSubjectViewModel.rating.observe(this, Observer<Rating> {
            t ->
            yValueGroup1.add(BarEntry(0.5f, t.count._1.toFloat()/t.total.toFloat()))
            yValueGroup1.add(BarEntry(1.5f, t.count._2.toFloat()/t.total.toFloat()))
            yValueGroup1.add(BarEntry(2.5f, t.count._3.toFloat()/t.total.toFloat()))
            yValueGroup1.add(BarEntry(3.5f, t.count._4.toFloat()/t.total.toFloat()))
            yValueGroup1.add(BarEntry(4.5f, t.count._5.toFloat()/t.total.toFloat()))
            yValueGroup1.add(BarEntry(5.5f, t.count._6.toFloat()/t.total.toFloat()))
            yValueGroup1.add(BarEntry(6.5f, t.count._7.toFloat()/t.total.toFloat()))
            yValueGroup1.add(BarEntry(7.5f, t.count._8.toFloat()/t.total.toFloat()))
            yValueGroup1.add(BarEntry(8.5f, t.count._9.toFloat()/t.total.toFloat()))
            yValueGroup1.add(BarEntry(9.5f, t.count._10.toFloat()/t.total.toFloat()))
            barDataSet1 = BarDataSet(yValueGroup1, "")
            barDataSet1.setColors(Color.GRAY)
            barDataSet1.setDrawIcons(false)
            barDataSet1.setDrawValues(false)

            var barData = BarData(barDataSet1)
            barData.setValueFormatter(LargeValueFormatter())
            barChartView.data = barData
            barChartView.barData.barWidth = 0.8f
            // barChartView.setVisibleXRange(0f, 10f)
            barChartView.setVisibleYRange(0f,1f, YAxis.AxisDependency.LEFT)
            barChartView.data.isHighlightEnabled = false
            barChartView.axisLeft.setDrawGridLines(false)
            barChartView.axisRight.setDrawGridLines(false)
            barChartView.xAxis.setDrawGridLines(false)
            barChartView.axisLeft.isEnabled = false
            barChartView.axisRight.isEnabled = false
            barChartView.axisLeft.axisMinimum = 0f
            barChartView.axisRight.axisMinimum = 0f
            // barChartView.setVisibleXRangeMaximum(10f)
            // barChartView.setVisibleXRangeMinimum(10f)
            barChartView.isDragEnabled = true
            barChartView.description = null
            barChartView.legend.isEnabled = false
            barChartView.invalidate()
        })

        val xAxis = barChartView.xAxis
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setCenterAxisLabels(false)
        xAxis.textSize = 9f
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)
        xAxis.labelCount = 10
        xAxis.mAxisMaximum = 10f
        xAxis.setCenterAxisLabels(true)
        xAxis.setAvoidFirstLastClipping(true)
        xAxis.spaceMin = 4f
        xAxis.spaceMax = 4f

    }


}