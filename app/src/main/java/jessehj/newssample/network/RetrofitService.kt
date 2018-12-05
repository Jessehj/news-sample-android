package jessehj.newssample.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by jessehj on 01/12/2018.
 */

interface RetrofitRequest<T> {
    fun request(call: Call<T>, completion: RetrofitService.Completion)
}

class RetrofitService<T> : RetrofitRequest<T> {

    interface Completion {
        fun onSuccess(response: Any?)
        fun onError(error: ResError)
    }

    override fun request(call: Call<T>, completion: Completion) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                completion.onError(ResError(t.localizedMessage))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        completion.onSuccess(it)
                    } ?: run {
                        completion.onSuccess(null)
                    }
                } else {
                    val error = ResError.parseError(response)
                    completion.onError(error)
                }
            }
        })
    }
}