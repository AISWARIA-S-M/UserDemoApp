package com.ibm.usersapplication.viewmodel


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibm.usersapplication.app.UserDetailsInfo
import com.ibm.usersapplication.model.UserDetails
import com.ibm.usersapplication.utils.GenericClass
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    var job: Job? = null
    val data = MutableLiveData<UserDetails>()
    val errorMessage = MutableLiveData<String>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun fetchuserdata()  {
            job = viewModelScope.launch {
                withContext(Dispatchers.IO + exceptionHandler) {
                  //  val response = GenericClass.getInstance().service.getUserList().awaitResponse()
                    GenericClass.getInstance().service.getUserList().enqueue(object : Callback<UserDetails> {

                        override fun onResponse(
                            call: Call<UserDetails>, response: Response<UserDetails>
                        ){
                            if(response.code()==200){
                                data.postValue(response.body())
                                Toast.makeText(UserDetailsInfo.mInstance,"Success!!!", Toast.LENGTH_LONG).show()
                            }
                            else if(response.isSuccessful && response.body()==null){
                                Toast.makeText(UserDetailsInfo.mInstance,"No data available", Toast.LENGTH_LONG).show()
                            }
                            else{
                                Toast.makeText(
                                    UserDetailsInfo.mInstance,"Sorry! Something went wrong!",
                                    Toast.LENGTH_LONG).show()
                            }
                        }
                        override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                            Toast.makeText(
                                UserDetailsInfo.mInstance,"Hmm.. Can't reach the page",
                                Toast.LENGTH_LONG).show()
                            Log.d("onFailure", t.toString())
                        }
                    })
//                    try
//                    {
//                    if () {
//                        data.postValue(response.body())
//                    } else {
//                        onError("Error : ${response.message()} ")
//                        GenericClass.getInstance().getResponse()
//                        Log.d("Http Exception try block", "Something Went wrong!")
//                    }
//                }
//                catch (e:Exception){
//                    Log.d("Exception catch block",e.toString())
//                    GenericClass.getInstance().getResponse()
//                    onError("Error : ${response.message()} ")
//                }
                }

            }
        }
    private fun onError(message: String) {
        errorMessage.value = message
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}


