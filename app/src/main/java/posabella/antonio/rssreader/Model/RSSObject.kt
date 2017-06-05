package posabella.antonio.rssreader.Model

/**
 * Created by antonioposabella on 03/06/17.
 */

/*
        status { get; set; }
        Feed feed { get; set; }
        List<Item> items { get; set; }
*/



data class RSSObject(val status:String,val feed:Feed,val items:List<Item>)