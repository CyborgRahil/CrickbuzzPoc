package poc.com.cricbuzzpoc.drugList.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import poc.com.cricbuzzpoc.R
import poc.com.cricbuzzpoc.base.BaseActivity
import poc.com.cricbuzzpoc.data.local.DrugEntity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_drug_list.view.*
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_drug_list.*
import poc.com.cricbuzzpoc.addDrug.view.AddDrugActivity
import poc.com.cricbuzzpoc.drugDescription.view.DrugDescriptionActivity
import poc.com.cricbuzzpoc.drugList.listener.DrugItemListener
import poc.com.cricbuzzpoc.report.view.ReportActivity
import poc.com.cricbuzzpoc.utility.MedicalAppConstants

class DrugListActivity : BaseActivity(), DrugListContract.DrugListView,DrugItemListener {



    lateinit var mDrugAdapter: DrugListAdapter

    @Inject
    lateinit var mDrugListPresenter: DrugListPresenter

    /**
     * Show error message while getting any error while frtching data from database
     */
    override fun showError(errorMessage: String) {
        val snackbar = Snackbar.make(findViewById(R.id.coordinator_layout), errorMessage, Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

    /**
     *  Populate Recycler view after getting element from DB
     *  @list It contains list of drug entity
     */
    override fun populateRecyclerView(list: List<DrugEntity>) {
        mDrugAdapter = DrugListAdapter(list,this)
        drugs_recycler_view.adapter = mDrugAdapter
    }

    /**
     * Handle fab item click listener
     */
    override fun fabItemClick() {
         var intent = Intent(this,AddDrugActivity::class.java)
        startActivity(intent)
    }

    /**
     * Handle Item click on Drug list
     */
    override fun onDrugItemClick(drugEntity: DrugEntity) {
           mDrugListPresenter.openDrugListItem(drugEntity)
    }

    /**
     * Show drug description ui by using intent
     */
    override fun showDrugDetailsUi(drugId: String) {
        var intent = Intent(this, DrugDescriptionActivity::class.java)
        intent.putExtra(MedicalAppConstants.DRUG_ID,drugId)
        startActivity(intent)
    }

    override fun drugReportUi(isMedicineIntake: Boolean) {
        var intent = Intent(this, ReportActivity::class.java)
        intent.putExtra(MedicalAppConstants.IS_MEDICINE_INTAKE,isMedicineIntake)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBaliActivityComponent().inject(this)
        setContentView(R.layout.activity_drug_list)
        initRecyclerView()
        fabItemClickListener();

    }

    private fun fabItemClickListener() {
        add_drug_fab.setOnClickListener { fabItemClick() }
    }

    private fun initRecyclerView() {
        drugs_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }

    override fun onStop() {
        super.onStop()
        mDrugListPresenter.dropView()
    }

    override fun onStart() {
        super.onStart()
        mDrugListPresenter.takeView(this)

    }

    override fun onResume() {
        super.onResume()
        mDrugListPresenter.getDrugList()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_drug_intake_report -> mDrugListPresenter.drugReportGen(true)
            R.id.menu_drug_missed_report -> mDrugListPresenter.drugReportGen(false)

        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.drug_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}