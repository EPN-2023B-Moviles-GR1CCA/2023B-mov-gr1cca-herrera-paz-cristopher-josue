package com.example.examen_ib

class Registers {
    companion object{
        var arregloUniversidadesFacultades = arrayListOf<UniversidadesFacultades>()

        init{
            //Universidades
            UBDD.TablaUniversidad!!.crearUniversidad(1,"Escuela Politécnica Nacional (EPN)","30-08-1869","Pública","10750","true")

            UBDD.TablaUniversidad!!.crearUniversidad(2,"Escuela Politécnica del Ejército (ESPE)","19-06-1922","Pública","22230","true")

            // Facultades
            UBDD.TablaUniversidad!!.crearFacultad(1,"Facultad de Ingeniería de Sistemas (FIS)","2","10000","true")
            UBDD.TablaUniversidad!!.crearFacultad(2,"Facultad de Ingeniería Eléctrica y Electrónica (FIEE)","3","20000","true")
            UBDD.TablaUniversidad!!.crearFacultad(3,"Facultad de Ingeniería Mecánica (FIM)","4","30000","true")

            UBDD.TablaUniversidad!!.crearFacultad(4,"Departamento de Ciencias de la Energía y Mecánica","4","40000","true")

            // Universidades - Facultades
            arregloUniversidadesFacultades.add(UniversidadesFacultades(1,1,1))
            arregloUniversidadesFacultades.add(UniversidadesFacultades(1,1,2))
            arregloUniversidadesFacultades.add(UniversidadesFacultades(1,1,3))
            arregloUniversidadesFacultades.add(UniversidadesFacultades(2,2,4))



        }
    }
}