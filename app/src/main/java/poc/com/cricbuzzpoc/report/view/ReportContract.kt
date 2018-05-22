package poc.com.cricbuzzpoc.report.view

import poc.com.cricbuzzpoc.base.BasePresenter
import poc.com.cricbuzzpoc.base.BaseView
import poc.com.cricbuzzpoc.data.local.DrugEntity
import poc.com.cricbuzzpoc.data.local.DrugReportEntity

interface ReportContract {

    interface ReportView : BaseView<Presenter> {
        fun populateRecyclerView(list:List<DrugEntity>)
    }

    interface Presenter : BasePresenter<ReportView> {
        fun getDrugList()
    }
}