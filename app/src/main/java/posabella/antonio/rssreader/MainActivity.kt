package posabella.antonio.rssreader

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import posabella.antonio.rssreader.Adapter.FeedAdapter
import posabella.antonio.rssreader.Common.HTTPDataHandler
import posabella.antonio.rssreader.Model.RSSObject

class MainActivity : AppCompatActivity() {

    private val RSS_link = "http://www.nytimes.com/services/xml/rss/nyt/Science.xml"
    private val RSS_to_JSON = "https://api.rss2json.com/v1/api.json?rss_url="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "NEWS"
        setSupportActionBar(toolbar)

        val linearLayoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)

        recyclerView.layoutManager = linearLayoutManager

        loadRss()
    }

    private fun loadRss() {

        val loadRssAsync = object:AsyncTask<String,String,String>(){

            internal  var mDialog = ProgressDialog(this@MainActivity)

            override fun onPreExecute() {
                mDialog.setMessage("Please Wait...")
                mDialog.show()
            }

            override fun onPostExecute(result: String?) {
                mDialog.dismiss()
                var rssObject:RSSObject
                rssObject = Gson().fromJson<RSSObject>(result,RSSObject::class.java!!)
                val adapter = FeedAdapter(rssObject,baseContext)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun doInBackground(vararg p0: String): String {

                val result:String
                val http = HTTPDataHandler()
                result = http.GetHTTPDataHandler(p0[0])
                return result
            }





        }

        val url_get_data = StringBuilder(RSS_to_JSON)
        url_get_data.append(RSS_link)
        loadRssAsync.execute(url_get_data.toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_refresh)
            loadRss()
        return true
    }
}
