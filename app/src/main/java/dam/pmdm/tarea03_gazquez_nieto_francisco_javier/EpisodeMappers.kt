

package dam.pmdm.tarea03_gazquez_nieto_francisco_javier

import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.model.EpisodeDto

fun EpisodeDto.toEpisodio(): Episodio {
    return Episodio(
        id = id,
        nombre = name,
        codigoEpisodio = episode,
        fechaEmision = air_date,
        visto = false
    )
}
