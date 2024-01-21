package com.example.examen_ib

import android.os.Parcel
import android.os.Parcelable

class UniversidadesFacultades(
    val idUniversidadesFacultades: Int,
    val idUniversidad: Int,
    val idFacultad: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idUniversidadesFacultades)
        parcel.writeInt(idUniversidad)
        parcel.writeInt(idFacultad)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UniversidadesFacultades> {
        override fun createFromParcel(parcel: Parcel): UniversidadesFacultades {
            return UniversidadesFacultades(parcel)
        }

        override fun newArray(size: Int): Array<UniversidadesFacultades?> {
            return arrayOfNulls(size)
        }
    }
}