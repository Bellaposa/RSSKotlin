package posabella.antonio.rssreader.Model

/**
 * Created by antonioposabella on 03/06/17.
 */

/*
    title { get; set; }
    pubDate { get; set; }
    link { get; set; }
    guide { get; set; }
    author { get; set; }
    thumbnail { get; set; }
    description { get; set; }
    content { get; set; }
    Enclosure enclosure { get; set; }
    List<string> categories { get; set; }

*/


data class Item(val title:String, val pubDate :String, val link:String,val guide:String,
                val author:String, val thumbnail:String, val description:String, val content:String,
                val enclosure:Object,val categories:List<String>)