package com.example.data.repository

import com.example.model.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    // we'll use this function in all
    // repos to handle api errors.
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): UIState<T> {

        // Returning api response
        // wrapped in Resource class
        return withContext(Dispatchers.IO) {
            try {

                // Here we are calling api lambda
                // function that will return response
                // wrapped in Retrofit's Response class
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    // In case of success response we
                    // are returning Resource.Success object
                    // by passing our data in it.
                    UIState.Success(data = response.body()!!)
                } else {
                    // parsing api's own custom json error
                    // response in ExampleErrorResponse pojo
//                    val errorResponse: ExampleErrorResponse? =
//                        convertErrorBody(response.errorBody())
                    // Simply returning api's own failure message
                    UIState.Error(
                        error = response.errorBody().toString() ?: "Something went wrong"
                    )
                }

            } catch (e: HttpException) {
                // Returning HttpException's message
                // wrapped in Resource.Error
                UIState.Error(error = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                // Returning HttpException's message
                // wrapped in Resource.Error
                UIState.Error(error = e.message ?: "Something went wrong")
            } catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                UIState.Error(error = "Something went wrong")
            }
        }
    }

    // If you don't wanna handle api's own
    // custom error response then ignore this function
//    private fun convertErrorBody(errorBody: ResponseBody?): ExampleErrorResponse? {
//        return try {
//            errorBody?.source()?.let {
//                val moshiAdapter = Moshi.Builder().build().adapter(ExampleErrorResponse::class.java)
//                moshiAdapter.fromJson(it)
//            }
//        } catch (exception: Exception) {
//            null
//        }
//    }
}