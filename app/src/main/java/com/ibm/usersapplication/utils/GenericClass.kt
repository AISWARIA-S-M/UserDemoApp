package com.ibm.usersapplication.utils

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibm.usersapplication.app.UserDetailsInfo
import com.ibm.usersapplication.model.UserDetails
import com.ibm.usersapplication.retrofit.RetrofitApi
import com.ibm.usersapplication.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GenericClass {

    companion object {
        private var genericClass: GenericClass? = null

        fun getInstance(): GenericClass {
            if (genericClass == null)
                genericClass = GenericClass()
            return genericClass!!
        }
    }
    var service: RetrofitApi = RetrofitInstance.builder.create(RetrofitApi::class.java)


//
//    fun getResponse() : LiveData<UserDetails> {
//        val data = MutableLiveData<UserDetails>()
//        service.getUserList().enqueue(object : Callback<UserDetails> {
//
//    override fun onResponse(
//            call: Call<UserDetails>, response: Response<UserDetails>
//            ){
//                if(response.code()==200){
//                    data.postValue(response.body())
//                    Toast.makeText(UserDetailsInfo.mInstance,"Success!!!",Toast.LENGTH_LONG).show()
//              }
//               else if(response.isSuccessful && response.body()==null){
//                    Toast.makeText(UserDetailsInfo.mInstance,"No data available",Toast.LENGTH_LONG).show()
//                }
//                else{
//                  Toast.makeText(UserDetailsInfo.mInstance,"Sorry! Something went wrong!",Toast.LENGTH_LONG).show()
//              }
//                val udata = response.body()
//                Log.d("data1",udata?.users.toString())
//            }
//        override fun onFailure(call: Call<UserDetails>, t: Throwable) {
//                Toast.makeText(UserDetailsInfo.mInstance,"Hmm.. Can't reach the page",Toast.LENGTH_LONG).show()
//                Log.d("onFailure", t.toString())
//            }
//        })
//        return data
//    }
}