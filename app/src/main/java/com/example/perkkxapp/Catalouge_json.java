package com.example.perkkxapp;

import java.util.ArrayList;

/**
 * Created by wadi_123 on 5/22/2015.
 *
 * rating: 3.8,
 close_time: "21:00",
 desc2: "max limit rs 500",
 deal: "300 off on 1499 and above",
 other_offers: 7,
 cat: 1,
 phone: "+919818261929",
 address: {
 text: "Saket, New Delhi",
 lat: 28.5245084,
 lng: 77.2228797
 },
 open_time: "09:00",
 vendor_name: "Barbeque Nation",
 type: "Single",
 desc: "25% off on drinks",
 followers: 786,
 img: "http://d305u1xvzud3tg.cloudfront.net/images/rest/chains/barbeque-nation/Barbeque_nation.jpg",
 cuisine: [
 "Chinese",
 "Indian",
 "Mughlai"
 ],
 price: 800,
 icons: {
 wifi: 1,
 parking: 0,
 smoking: 0,
 outside: 1,
 cards: 1,
 drinks: 0,
 delivery: 1
 },
 reservation: 0,
 menu: [
 "https://a.zmtcdn.com/data/menus/322/308322/b88c2c7282b7bb1c2d7e887df7f06de1.jpg",
 "https://a.zmtcdn.com/data/menus/322/308322/de6eebdf37237e03e161770cc0bc024a.jpg",
 "https://a.zmtcdn.com/data/menus/322/308322/fefe82b3c9a08817116129b515a37af8.jpg"
 ],
 pics: [
 "https://a.zmtcdn.com/data/pictures/2/308322/e651c7be51b5b270e0c044b71869a634.jpg",
 "https://c.zmtcdn.com/data/pictures/2/308322/9e4b127b701f41ae5cda0912c3f2f2ed.jpg"
 ],
 rcode: "WX64356PD"
 },
 {
 rating: 5.8,
 close_time: "21:00",
 desc2: "max limit rs 500",
 deal: "300 off on 1499 and above",
 other_offers: 7,
 cat: 1,
 phone: "+919818261929",
 address: {
 text: "Saket, New Delhi",
 lat: 28.5245084,
 lng: 77.2228797
 },
 open_time: "09:00",
 vendor_name: "Big Chill",
 type: "Single",
 desc: "25% off on drinks",
 followers: 786,
 img: "http://d305u1xvzud3tg.cloudfront.net/images/rest/chains/barbeque-nation/Barbeque_nation.jpg",
 cuisine: [
 "Chinese",
 "Indian",
 "Mughlai"
 ],
 price: 1800,
 icons: {
 wifi: 1,
 parking: 1,
 smoking: 1,
 outside: 0,
 cards: 1,
 drinks: 0,
 delivery: 1
 },
 reservation: 0,
 menu: [
 "https://a.zmtcdn.com/data/menus/322/308322/b88c2c7282b7bb1c2d7e887df7f06de1.jpg",
 "https://a.zmtcdn.com/data/menus/322/308322/de6eebdf37237e03e161770cc0bc024a.jpg",
 "https://a.zmtcdn.com/data/menus/322/308322/fefe82b3c9a08817116129b515a37af8.jpg"
 ],
 pics: [
 "https://a.zmtcdn.com/data/pictures/2/308322/e651c7be51b5b270e0c044b71869a634.jpg",
 "https://c.zmtcdn.com/data/pictures/2/308322/9e4b127b701f41ae5cda0912c3f2f2ed.jpg"
 ],
 rcode: "WX64356PD"
 */
class keyValuePair <K, V> implements java.util.Map.Entry<K,V>
{

    K key;
    V value;

    public keyValuePair(K key,V value)
    {
        this.key=key;
        this.value=value;

    }


    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V v) {
        value=v;
        return v;
    }
}


public class Catalouge_json
{

    keyValuePair<String,String>rating=new keyValuePair<String, String>("rating","0");
    keyValuePair<String,String>close_time=new keyValuePair<String, String>("close_time","0");
    keyValuePair<String,String>desc2=new keyValuePair<String, String>("desc2","0");
    keyValuePair<String,String>deal=new keyValuePair<String, String>("deal","0");
    keyValuePair<String,String>cat=new keyValuePair<String, String>("cat","0");
    keyValuePair<String,String>phone=new keyValuePair<String, String>("phone","0");
    keyValuePair<String,String>other_offers=new keyValuePair<String, String>("other_offers","0");
    keyValuePair<String,String>open_time=new keyValuePair<String, String>("open_time","0");
    keyValuePair<String,String>vendor_name=new keyValuePair<String, String>("vendor_name","0");
    keyValuePair<String,String>type=new keyValuePair<String, String>("type","0");
    keyValuePair<String,String>desc=new keyValuePair<String, String>("desc","0");
    keyValuePair<String,String>followers=new keyValuePair<String, String>("followers","0");
    keyValuePair<String,String>img=new keyValuePair<String, String>("img","0");
    keyValuePair<String,String>reservation=new keyValuePair<String, String>("reservation","0");
    keyValuePair<String,String>rcode=new keyValuePair<String, String>("rcode","0");
    keyValuePair<String,String>price=new keyValuePair<String, String>("price","0");



    keyValuePair<String,address>address=new keyValuePair<String, address>("address",new address());
    keyValuePair<String,ArrayList<String>>cuisine=new keyValuePair<String, ArrayList<String>>("cuisine",new ArrayList<String>());
    keyValuePair<String,ArrayList<String>>menu=new keyValuePair<String, ArrayList<String>>("menu",new ArrayList<String>());
    keyValuePair<String,ArrayList<String>>pics=new keyValuePair<String, ArrayList<String>>("pics",new ArrayList<String>());


    keyValuePair<String,icons>icons=new keyValuePair<String, icons>("icons",new icons());

}

class address
{
    keyValuePair<String,String>text=new keyValuePair<String, String>("text","0");
    keyValuePair<String,String>lat=new keyValuePair<String, String>("lat","0");
    keyValuePair<String,String>lng=new keyValuePair<String, String>("lng","0");

}

class icons
{

   keyValuePair<String,String>reservation=new keyValuePair<String, String>("reservation","0");
    keyValuePair<String,String>wifi=new keyValuePair<String, String>("wifi","0");
    keyValuePair<String,String>parking=new keyValuePair<String, String>("parking","0");
    keyValuePair<String,String>smoking=new keyValuePair<String, String>("smoking","0");
    keyValuePair<String,String>outside=new keyValuePair<String, String>("outside","0");
    keyValuePair<String,String>cards=new keyValuePair<String, String>("cards","0");
    keyValuePair<String,String>drinks=new keyValuePair<String, String>("drinks","0");
    keyValuePair<String,String>delivery=new keyValuePair<String, String>("delivery","0");
    keyValuePair<String,String>veg=new keyValuePair<String,String>("veg", "0");


}