package dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.model

data class EpisodeListResponse(
    val info: InfoDto,
    val results: List<EpisodeDto>
)

data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class EpisodeDto(
    val id: Int,
    val name: String,
    val episode: String,
    val air_date: String,
    val characters: List<String>
)
data class CharacterDto(
    val id: Int,
    val name: String,
    val image: String
)
