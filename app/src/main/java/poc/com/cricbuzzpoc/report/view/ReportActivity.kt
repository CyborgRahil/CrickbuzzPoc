package poc.com.cricbuzzpoc.report.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_report.*
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.base.BaseActivity
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.data.local.DrugReportEntity
import poc.com.cricbuzzpoc.utility.MedicalAppConstants
import javax.inject.Inject

class ReportActivity:BaseActivity(),ReportContract.ReportView {

    lateinit var mReportAdapter: ReportAdapter

    private var mIsMedicineIntake:Boolean = false

    @Inject
    lateinit var mReportPresenter: ReportPresenter


    override fun populateRecyclerView(list: List<DrugEntity>) {
        mReportAdapter = ReportAdapter(this,list,isMedicineTaken = mIsMedicineIntake)
        rv_report.adapter = mReportAdapter
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this,errorMessage, Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBaliActivityComponent().inject(this)
        setContentView(R.layout.activity_report)
        mIsMedicineIntake = intent.getBooleanExtra(MedicalAppConstants.IS_MEDICINE_INTAKE,false)
        initRecyclerView()
        mReportPresenter.getDrugList()

    }

    private fun initRecyclerView() {
        rv_report.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }

    override fun onStop() {
        super.onStop()
        mReportPresenter.dropView()
    }

    override fun onStart() {
        super.onStart()
        mReportPresenter.takeView(this)

    }
}
