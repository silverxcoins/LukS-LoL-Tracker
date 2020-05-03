package ru.mobile.lukslol.domain.dto

enum class Region(val code: String, val regionName: String) {
    RU("ru", "RUSSIA"),
    EUW("euw", "EUROPE WEST"),
    EUNE("eune", "EUROPE NORD EAST"),
    BR("br", "BRAZIL"),
    KR("kr", "KOREA"),
    LAN("lan", "LATIN AMERICA NORTH"),
    LAS("las", "LATIN AMERICA SOUTH"),
    NA("na", "NORTH AMERICA"),
    OCE("oce", "OCEANIA"),
    TR("tr", "TURKEY"),
    JP("jp", "JAPAN"),
}