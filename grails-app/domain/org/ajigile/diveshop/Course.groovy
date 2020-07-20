package org.ajigile.diveshop

class Course {

    String label

    static belongsTo = [diveShop: DiveShop]

    static constraints = {
    }
}
