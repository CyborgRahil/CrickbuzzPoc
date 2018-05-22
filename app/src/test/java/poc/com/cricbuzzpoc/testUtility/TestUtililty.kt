package poc.com.cricbuzzpoc.testUtility

import poc.com.cricbuzzpoc.data.local.DrugEntity

import java.util.ArrayList

open class TestUtililty {
  companion object {


      fun createEntity(): DrugEntity {
          val entity = DrugEntity()
          entity.drugName = "Sample"
          entity.drugDescription = "Sample"
          entity.drugFrequency = "EveryDay"
          entity.drugDoseQuantity = "2"
          entity.drugAlarmTime = "20:30"
          entity.drugId = "123"
          entity.drugTakenOrIgnoreDateList = ArrayList()


          return entity

      }


  }


}
