package com.tasha.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.tasha.data.local.entity.PPVTable
import com.tasha.data.local.entity.People
import com.tasha.data.local.entity.Planet
import com.tasha.data.local.entity.Vehicle

@Dao
abstract class PPVDao {

    @Transaction
    public open fun addToPPVTableForVehicle(list: List<Vehicle>) {
        var listItems: List<PPVTable>? = listOf()

        list.forEach {
            it.pilots?.forEach {people ->
                var id:Long = people.split("/")[5].toLong()
                it.id.let {tempId ->
                    listItems = getPPVItem(vehicleId = tempId, peopleId = id)
                }

                val ppvTable = PPVTable(vehicle_id = it.id, people_id = id,)
                if(listItems.isNullOrEmpty()) {
                    addPPVItem(ppvTable)
                    return
                }

                if(!listItems!!.contains(ppvTable)){
                    addPPVItem(ppvTable)
                }else{
                    updatePPVItem(listItems!!.filter {table ->
                        table.people_id == ppvTable.people_id || table.planet_id == ppvTable.planet_id || table.vehicle_id == ppvTable.vehicle_id
                    })
                }

            }
        }
    }

    @Transaction
    public open fun addToPPVTableForPlanet(list: List<Planet>) {
        var listItems: List<PPVTable>? = listOf()

        list.forEach {
            it.residents?.forEach {people ->
                var id:Long = people.split("/")[5].toLong()
                it.id.let {tempId ->
                    listItems = getPPVItem(planetId = tempId, peopleId = id)
                }

                val ppvTable = PPVTable(planet_id = it.id, people_id = id,)
                if(listItems.isNullOrEmpty()) {
                    addPPVItem(ppvTable)
                    return
                }

                if(!listItems!!.contains(ppvTable)){
                    addPPVItem(ppvTable)
                }else{
                    updatePPVItem(listItems!!.filter {table ->
                        table.people_id == ppvTable.people_id || table.planet_id == ppvTable.planet_id || table.vehicle_id == ppvTable.vehicle_id
                    })
                }

            }
        }

    }

    @Transaction
    public open fun addToPPVTableForPeople(list: List<People>) {
        var listItems: List<PPVTable>? = listOf()

        list.forEach {
            it.starships?.forEach {vehicle ->
                var id:Long = vehicle.split("/")[5].toLong()
                it.id.let {tempId ->
                    listItems = getPPVItem(peopleId = tempId, vehicleId =  id)
                }

                val ppvTable = PPVTable(people_id = it.id, vehicle_id = id,)
                if(listItems.isNullOrEmpty()) {
                    addPPVItem(ppvTable)
                    return
                }

                if(!listItems!!.contains(ppvTable)){
                    addPPVItem(ppvTable)
                }else{
                    updatePPVItem(listItems!!.filter {table ->
                        table.people_id == ppvTable.people_id || table.planet_id == ppvTable.planet_id || table.vehicle_id == ppvTable.vehicle_id
                    })
                }

            }
        }

    }
    @Query("SELECT * FROM ppv where people_id = :peopleId or vehicle_id = :vehicleId or planet_id = :planetId")
    abstract fun getPPVItem(peopleId: Long? = null, vehicleId: Long? = null, planetId: Long? = null): List<PPVTable>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addPPVItem(ppvTable: PPVTable)

    @Update
    abstract fun updatePPVItem(ppvTable: List<PPVTable>)
}