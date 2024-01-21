package com.example.examen_ib

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class Facultad(
    var idFacultad: Int,
    var nombreFacultad: String?,
    var numeroDepartamentos: Int?,
    var presupuestoAnual: Double?,
    var ofertaInvestigacion: Boolean?

) : Parcelable{
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readBoolean()
    ) {
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idFacultad)
        parcel.writeString(nombreFacultad)
        parcel.writeInt(numeroDepartamentos!!)
        parcel.writeDouble(presupuestoAnual!!)
        parcel.writeBoolean(ofertaInvestigacion!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "$nombreFacultad"
    }

    companion object CREATOR : Parcelable.Creator<Facultad> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Facultad {
            return Facultad(parcel)
        }

        override fun newArray(size: Int): Array<Facultad?> {
            return arrayOfNulls(size)
        }
    }

}