package org.techtown.moback.server

import androidx.annotation.WorkerThread
import org.json.JSONArray
import org.json.JSONObject
import org.techtown.moback.model.StoreModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class ServerLibrary {

    private val TAG = "ServerLibrary"

    companion object{

        private val ip_address = "http://121.182.37.7:80"

        @WorkerThread
        public fun searchStoresInRadius(lat : Double, lng: Double, radius : Double, token : String) : List<StoreModel>
        {
            var url = URL("$ip_address/api/stores/search/$radius")

            var conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.doOutput = true
            conn.doInput = true

            var data = JSONObject()
            data.put("latitude", lat)
            data.put("longitude", lng)

            var outputStream = OutputStreamWriter(conn.outputStream)
            outputStream.write(data.toString())
            outputStream.flush()

            var responseCode = conn.responseCode

            var result : ArrayList<StoreModel> = ArrayList()
            //정상
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                var br = BufferedReader(InputStreamReader(conn.inputStream))

                var jsonArray = JSONArray(br.readLine())

                for(i in 0 until jsonArray.length())
                {
                    var jsonObject = jsonArray.getJSONObject(i)
                    var store = StoreModel(jsonObject.getString("category"), jsonObject.getInt("id"), jsonObject.getDouble("latitude"), jsonObject.getDouble("longitude")
                    , jsonObject.getString("name"), jsonObject.getString("owner"))

                    result.add(store)
                }

                br.close()
            }

            conn.disconnect()
            return result
        }

        @WorkerThread
        public fun login(email: String, password: String) : String?
        {
            var result : String? = null

            var url = URL("$ip_address/api/login")

            var conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type","application/json")
            conn.setRequestProperty("Accept", "application/json")
            conn.doOutput = true
            conn.doInput = true

            var data = JSONObject()
            data.put("email", email)
            data.put("password", password)

            var outputStream = OutputStreamWriter(conn.outputStream)
            outputStream.write(data.toString())
            outputStream.flush()

            var responseCode = conn.responseCode

            //정상
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                var br = BufferedReader(InputStreamReader(conn.inputStream))
                result = JSONObject(br.readLine()).getString("token")

                br.close()
            }

            conn.disconnect()
            return result
        }

        @WorkerThread
        public fun registerUser(email : String, firstName:  String, lastName : String, password : String) : Boolean
        {
            var result = false
            var url = URL("$ip_address/api/users/register")

            var conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type","application/json")
            conn.setRequestProperty("Accept", "application/json")
            conn.doOutput = true
            conn.doInput = true

            var data = JSONObject()
            data.put("email", email)
            data.put("firstName", firstName)
            data.put("lastName", lastName)
            data.put("password", password)

            var outputStream = OutputStreamWriter(conn.outputStream)
            outputStream.write(data.toString())
            outputStream.flush()

            var responseCode = conn.responseCode

            //정상
            if(responseCode == HttpURLConnection.HTTP_OK)
                result = true


            conn.disconnect()

            return result
        }
    }
}