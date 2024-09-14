package com.example.checkcount.screens.filters

import com.example.checkcount.model.Obj

fun searchBeachesByDescription(
    objs: MutableList<Obj>,
    query: String
):List<Obj>{
    val regex = query.split(" ").joinToString(".*"){
        Regex.escape(it)
    }.toRegex(RegexOption.IGNORE_CASE)
    return objs.filter { obj ->
        regex.containsMatchIn(obj.description)
    }
}
