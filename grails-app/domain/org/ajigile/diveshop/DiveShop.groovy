package org.ajigile.diveshop

class DiveShop {


    static hasMany = [
            operationalDays: OperationalDay
    ]

    static mappedBy = [
            operationalDays: "diveShop"
    ]



    String langIso
    String name
    String address1
    String address2
    String address3
    Integer postalCode
    String phone
    String email
    String website
    String lon
    String lat
    String region

    String description




    static constraints = {
        langIso nullable: true
        name nullable: true
        address1 nullable: true
        address2 nullable: true
        address3 nullable: true
        postalCode nullable: true
        phone nullable: true
        email nullable: true
        website nullable: true
        lon nullable: true
        lat nullable: true
        region nullable: true
        description nullable: true
    }
}
