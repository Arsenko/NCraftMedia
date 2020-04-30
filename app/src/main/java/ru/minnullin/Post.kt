package ru.minnullin

import java.util.*

class Post (
    authorName:String,
    authorDrawable:Int,
    bodyText:String,
    postDate:Date=Date(),
    likeCounter:Int,
    commentCounter:Int,
    shareCounter:Int
){
    val author=authorName
    get
    val drawable=authorDrawable
    get
    val text=bodyText
    get
    val date=postDate
    get
    var like=likeCounter
    get
    var comment=commentCounter
    get
    var share=shareCounter
    get
    fun likeIncrease(){
        like++
    }
    fun likeDecrease(){
        like--
    }
    fun commentIncrease(){
        comment++
    }
    fun shareIncrease(){
        share++
    }
}
