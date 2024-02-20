package com.example.examen_ib

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class Universidad(
    var idUniversidad: Int,
    var nombreUniversidad: String?,
    var fechaFundacion: String?,
    var tipo: String?,
    var numeroEstudiantes: Int?,
    var tienePostgrado: Boolean?
) :Parcelable{
    override fun toString(): String {
        return "${nombreUniversidad}"
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readBoolean()
    ) {
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idUniversidad)
        parcel.writeString(nombreUniversidad)
        parcel.writeString(fechaFundacion)
        parcel.writeString(tipo)
        parcel.writeInt(numeroEstudiantes!!)
        parcel.writeBoolean(tienePostgrado!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Universidad> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Universidad {
            return Universidad(parcel)
        }

        override fun newArray(size: Int): Array<Universidad?> {
            return arrayOfNulls(size)
        }
    }
}