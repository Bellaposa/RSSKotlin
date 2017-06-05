package posabella.antonio.rssreader.Model

/**
 * Created by antonioposabella on 03/06/17.
 */

/*
*   url { get; set; }
*   title { get; set; }
*   link { get; set; }
*   author { get; set; }
*   description { get; set; }
*   image { get; set; }
 */

data class Feed(val url:String,val title:String,val link:String,val author:String,val description:String,val image:String)