package org.ajigile.diveshop

import java.nio.file.Files
import java.nio.file.Paths


class DiveShopController {

    static allowedMethods = [index: 'GET', show: 'GET', save: 'POST',
                             update: 'POST', delete: 'POST']

    def assetResourceLocator
    def diveShopService
    def diveShopUtil

    def index() {
        def allDiveShops = diveShopService.list()
        [allDiveShops: allDiveShops]
    }


    def create() {

        def operationalDays = []
        diveShopUtil.dayLabelMap.eachWithIndex{ key, val, index ->
            operationalDays.add(
                    new OperationalDay(
                            dayIndex: key,
                            dayLabel: diveShopUtil.dayLabelMap.get(key),
                            openHour: '09:00',
                            closeHour: '17:00',
                            closed: false)
            )
        }
        ((OperationalDay)operationalDays.get(operationalDays.size()-1)).openHour = null
        ((OperationalDay)operationalDays.get(operationalDays.size()-1)).closeHour = null
        ((OperationalDay)operationalDays.get(operationalDays.size()-1)).closed = true

        def diveShopCommand =
                new DiveShopCommand(
                        langIso: 'en',
                        name: 'Dive Shop X',
                        address1: 'Jalan Mekar Saluyu 13C',
                        address2: 'Kecamatan Setia Budi, RW1/RT14',
                        address3: 'Jakarta, Indonesia',
                        email: 'info@xdiveshop.com',
                        website: 'http://www.xdiveshop.com',
                        region: 'Jakarta',
                        postalCode: 12345,
                        phone: '0987654321',
                        lon: '106.8129297',
                        lat: '-6.2464471')

        def allDiveShops = diveShopService.list()

        [
                diveShop: diveShopCommand,
                languages: diveShopUtil.languages,
                operationalDays: operationalDays,
                allDiveShops: allDiveShops
        ]
    }

    def save(DiveShopCommand diveShopCommand) {
        println "controller save: " + diveShopCommand.name + " language: "+diveShopCommand.langIso

        def diveShop = diveShopService.registerDiveShop(diveShopCommand)

        String imageUploadPath=grailsApplication.config.imageUpload.path
        diveShopUtil.updateImage(request, imageUploadPath, diveShop)

        redirect action:"index", controller:"diveShop", method:"GET"
    }

    def show(Long id) {

        log.info("show: " + id)

        def allDiveShops = diveShopService.list()
        DiveShop diveShop
        println "allDiveShops: " + allDiveShops
        if(allDiveShops && !allDiveShops.isEmpty()){
            for(int i=0;i<allDiveShops.size(); i++){
                println "diveShop id:"+allDiveShops.get(i).id
                if(allDiveShops.get(i).id.equals(id)){
                    diveShop = allDiveShops.get(i)
                    break
                }
            }
        }

        println "diveShop: " + diveShop

        if(!diveShop){
            redirect action:"index", method:"GET"
            return
        }

        String imageUploadPath=grailsApplication.config.imageUpload.path
        boolean image1Exist = Files.exists(Paths.get("${imageUploadPath}/${id}_image1"))
        boolean image2Exist = Files.exists(Paths.get("${imageUploadPath}/${id}_image2"))


        String features = ""
        diveShopService.getFeatures(diveShop).each {e->
            if(features.length()>1){
                features = features.concat(",")
            }
            features = features.concat(e.getLabel())
        }

        String courses = ""
        diveShopService.getCourses(diveShop).each {e->
            if(courses.length()>1){
                courses = courses.concat(",")
            }
            courses = courses.concat(e.getLabel())
        }

        println "operationalDays size: " + diveShop.operationalDays.size()
        def opDays = []
        opDays.addAll(diveShop.operationalDays)
        diveShop.operationalDays.each {opDay ->
            opDays.set(opDay.dayIndex, opDay)
        }


        [
                diveShop: diveShop,
                operationalDays: opDays,
                allDiveShops: allDiveShops,
                features: features,
                courses: courses,
                languages: diveShopUtil.languages,
                image1Exist: image1Exist,
                image2Exist: image2Exist
        ]
    }

    def update(DiveShopCommand diveShopCommand){

        def diveShop = diveShopService.updateDiveShopData(diveShopCommand)

        String imageUploadPath=grailsApplication.config.imageUpload.path
        diveShopUtil.updateImage(request, imageUploadPath, diveShop)

        redirect action:"index", controller:"diveShop", method:"GET"
    }

    def delete(Long id) {
        diveShopService.deleteDiveShop(id)

        String imageUploadPath=grailsApplication.config.imageUpload.path
        diveShopUtil.deleteImages(id, imageUploadPath)

        redirect action:"index", method:"GET"
    }


    def showImage1(Long id){
        String imageUploadPath=grailsApplication.config.imageUpload.path

        byte[] imageInBytes = diveShopService.showImage1(id, imageUploadPath, assetResourceLocator)
        response.with{
            setHeader('Content-length', imageInBytes.length.toString())
            contentType = 'image/jpg' // or the appropriate image content type
            outputStream << imageInBytes
            outputStream.flush()
        }
    }

    def showImage2(Long id){
        String imageUploadPath=grailsApplication.config.imageUpload.path
        byte[] imageInBytes = diveShopService.showImage2(id, imageUploadPath, assetResourceLocator)
        response.with{
            setHeader('Content-length', imageInBytes.length.toString())
            contentType = 'image/jpg' // or the appropriate image content type
            outputStream << imageInBytes
            outputStream.flush()
        }
    }

    def display(Long id){

        def diveShop = diveShopService.get(id)

        String imageUploadPath=grailsApplication.config.imageUpload.path
        boolean image1Exist = Files.exists(Paths.get("${imageUploadPath}/${id}_image1"))
        boolean image2Exist = Files.exists(Paths.get("${imageUploadPath}/${id}_image2"))

        String googleAPIKey = grailsApplication.config.google.APIKey

        println "operationalDays size: " + diveShop.operationalDays.size()
        def opDays = []
        opDays.addAll(diveShop.operationalDays)
        diveShop.operationalDays.each {opDay ->
            opDays.set(opDay.dayIndex, opDay)
        }


        [
                diveShop: diveShop,
                operationalDays: opDays,
                courses: diveShopService.getCourses(diveShop),
                features: diveShopService.getFeatures(diveShop),
                image1Exist: image1Exist,
                image2Exist: image2Exist,
                googleAPIKey: googleAPIKey
        ]

    }







}


class DiveShopCommand {

    Long id = 0
    String langIso
    String name
    byte[] image1
    byte[] image2
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

    String courses = "Beginner,Advanced"
    String features = "PADI,SNI"

    String day0Start
    String day1Start
    String day2Start
    String day3Start
    String day4Start
    String day5Start
    String day6Start

    String day0End
    String day1End
    String day2End
    String day3End
    String day4End
    String day5End
    String day6End

    Boolean day0Close
    Boolean day1Close
    Boolean day2Close
    Boolean day3Close
    Boolean day4Close
    Boolean day5Close
    Boolean day6Close


    static boolean defaultNullable() {
        true
    }


    static constraints = {
        langIso (blank: false)
        name (blank: false)
        address1  (blank: false)
        postalCode (blank: false)
        phone (blank: false)
        lon (blank: true)
        lat (blank: true)
        region (blank: true)
        courses (blank: true)
        features (blank: true)
    }
}

