package com.ibm.usersapplication.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibm.usersapplication.model.UserDetails
import com.ibm.usersapplication.utils.GenericClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class UserViewModel : ViewModel() {

    val data = MutableLiveData<UserDetails>()

    fun fetchuserdata()  {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                try {
                    val response = GenericClass.getInstance().service.getUserList().awaitResponse()
                    if (response.isSuccessful) {
                        data.postValue(response.body())
                    } else {
                        GenericClass.getInstance().getResponse()
                        Log.d("Http Exception try block", "Something Went wrong!")
                    }
                }
                catch (e:Exception){
                    Log.d("Exception catch block",e.toString())
                    GenericClass.getInstance().getResponse()
                }
                }
            }

        }
}


