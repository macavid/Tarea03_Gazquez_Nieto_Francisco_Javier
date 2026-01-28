package dam.pmdm.tarea03_gazquez_nieto_francisco_javier

object EpisodiosMock {
    val lista: MutableList<Episodio> =mutableListOf(
       Episodio(1,"Pilot","S01E01"," 2 de Diciembre, 2013",false) ,
    Episodio(2,"Lawnmower","S01E02"," 9 de Diciembre, 2013",false),

    Episodio(3,"Anatomy Park","S01E03"," 16 de Diciembre, 2013",false)


    )

    fun total(): Int = lista.size
    fun vistos(): Int= lista.count(){it.visto}
    fun porcentaje(): Int= if (total()==0)0
    else (vistos()*100/total())



}