package org.ajigile.diveshop

class OperationalDay {

    static mapping = {
        sort: 'dayIndex'
        order: 'asc'
    }

    Integer dayIndex
    String dayLabel
    String openHour
    String closeHour
    Boolean closed

    static belongsTo = [diveShop: DiveShop]
    //static belongsTo = DiveShop

    static constraints = {
        openHour nullable: true
        closeHour nullable: true
        closed nullable: true
    }
}
