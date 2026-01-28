package dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.api

import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.model.CharacterDto
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.model.EpisodeDto
import dam.pmdm.tarea03_gazquez_nieto_francisco_javier.data.model.EpisodeListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int = 1): EpisodeListResponse



    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): EpisodeDto

    // batch: /character/[1,2,3]
    @GET("character/{ids}")
    suspend fun getCharacters(@Path("ids") ids: String): List<CharacterDto>
}







