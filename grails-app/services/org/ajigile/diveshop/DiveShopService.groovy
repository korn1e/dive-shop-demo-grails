package org.ajigile.diveshop

import grails.gorm.transactions.Transactional

@Transactional
class DiveShopService {

    def diveShopUtil


    def get(id){
        DiveShop.get(id)
    }

    def getFeatures(diveShop){

        Feature.findAllByDiveShop(diveShop)
    }

    def getCourses(diveShop){
        Course.findAllByDiveShop(diveShop)
    }

    def list() {
        log.debug("listing")
        DiveShop.list()
    }

    def save(entity, insert){
        println 'DiveShopService save: ' + entity
        if(insert){
            entity.save(insert: true)
        } else {
            entity.save()
        }
    }

    def save(entity){
       save(entity, false)
    }

    def delete(id){
        DiveShop.get(id).delete()
    }

    def deleteFeature(Feature feature){
        feature.delete()
    }

    def deleteCourse(Course course){
        course.delete()
    }

    byte[] showImage1(id, imageUploadPath, assetResourceLocatorX){
        File file = new File("${imageUploadPath}/${id}_image1")
        file.bytes

    }

    byte[] showImage2(id, imageUploadPath, assetResourceLocatorX){
        File file = new File("${imageUploadPath}/${id}_image2")
        file.bytes
    }


    def deleteDiveShop(Long id){
        DiveShop diveShop = get(id)
        getCourses(diveShop).each {e->
            deleteCourse(e)
        }

        getFeatures(diveShop).each {e->
            deleteFeature(e)
        }

        delete(id)
    }


    DiveShop registerDiveShop(DiveShopCommand diveShopCommand){

        def diveShop = diveShopUtil.mapDiveShopBasicData(diveShopCommand)
        //def diveShop = mapDiveShopBasicData()


        diveShopUtil.dayLabelMap.eachWithIndex{ key, val, index ->
            def openHour = null
            def closeHour = null
            if(!(Boolean)diveShopCommand.properties.get('day'+key+'Close')){
                openHour = diveShopCommand.properties.get('day'+key+'Start')
                closeHour = diveShopCommand.properties.get('day'+key+'End')
            }

            def operationalDay = new OperationalDay(
                    dayIndex: key,
                    dayLabel: diveShopUtil.dayLabelMap.get(key),
                    closed: diveShopCommand.properties.get('day'+key+'Close'),
                    openHour: openHour,
                    closeHour: closeHour,
                    diveShop: diveShop
            )
            diveShop.addToOperationalDays(operationalDay)

        }

        save(diveShop, true)
        println "diveShop saved as id: " + diveShop.id

        applyFeatures(diveShopCommand, diveShop)
        applyCourses(diveShopCommand, diveShop)

        diveShop
    }

    DiveShop updateDiveShopData(DiveShopCommand diveShopCommand){
        def diveShop = diveShopUtil.mapDiveShopBasicData(diveShopCommand)
        diveShop.getOperationalDays().each {opDay->
            def isClosed = diveShopCommand.properties.get('day'+opDay.dayIndex+'Close')
            opDay.setClosed(isClosed)
            if(isClosed){
                opDay.setOpenHour(null)
                opDay.setCloseHour(null)
            } else {
                opDay.setOpenHour(diveShopCommand.properties.get('day'+opDay.dayIndex+'Start'))
                opDay.setCloseHour(diveShopCommand.properties.get('day'+opDay.dayIndex+'End'))
            }
        }

        save(diveShop)
        println "diveShop saved as id: " + diveShop.id

        applyFeatures(diveShopCommand, diveShop)
        applyCourses(diveShopCommand, diveShop)
        diveShop
    }

    def applyFeatures(DiveShopCommand diveShopCommand, DiveShop diveShop){

        def currentFeatures = getFeatures(diveShop)

        if(!currentFeatures){
            // currently new features
            if(diveShopCommand.features?.trim()){
                diveShopCommand.features.split(',').each { name ->
                    println "feature name: " + name
                    def feature = new Feature(label: name, diveShop: diveShop)

                    save(feature)
                    println "feature saved with id: " + feature.id
                }
            }
        } else {
            // currently features are available, updates
            def nFeatures = []
            if(diveShopCommand.features?.trim()){
                // update feature set (with new ones)
                diveShopCommand.features.split(',').each { name ->
                    def feature = new Feature(label: name, diveShop: diveShop)
                    nFeatures.add(feature)
                }

                nFeatures.eachWithIndex{ nFeature, int i ->
                    if(i<currentFeatures.size()){
                        // reuse current feature (update label)
                        def currentFeature = currentFeatures.get(i)
                        currentFeature.setLabel(nFeature.label)
                        save(currentFeature)
                    } else {
                        // append features
                        save(nFeature)
                    }
                }

                int oldFCount = currentFeatures.size() - nFeatures.size()
                for(int i=0; i<oldFCount; i++){
                    def x = currentFeatures.size() - 1 - i
                    deleteFeature(currentFeatures.get(x))
                }


            } else {
                // remove all features
                def fCount = currentFeatures.size()
                fCount.times {
                    deleteFeature(currentFeatures.remove(0))
                }
            }
        }

    }

    def applyCourses(DiveShopCommand diveShopCommand, DiveShop diveShop){

        def currentCourses = getCourses(diveShop)

        if(!currentCourses){
            // currently new courses
            if(diveShopCommand.courses?.trim()){
                diveShopCommand.courses.split(',').each { name ->
                    println "courses name: " + name
                    def courses = new Course(label: name, diveShop: diveShop)

                    save(courses)
                    println "courses saved with id: " + courses.id
                }
            }
        } else {
            // currently courses are available, updates
            def nCourses = []
            if(diveShopCommand.courses?.trim()){
                // update feature set (with new ones)
                diveShopCommand.courses.split(',').each { name ->
                    def courses = new Course(label: name, diveShop: diveShop)
                    nCourses.add(courses)
                }

                nCourses.eachWithIndex{ nCourse, int i ->
                    if(i<currentCourses.size()){
                        // reuse current courses (update label)
                        def currentCourse = currentCourses.get(i)
                        currentCourse.setLabel(nCourse.label)
                        save(currentCourse)
                    } else {
                        // append features
                        save(nCourse)
                    }
                }

                int oldCCount = currentCourses.size() - nCourses.size()
                for(int i=0; i<oldCCount; i++){
                    def x = currentCourses.size() - 1 - i
                    deleteCourse(currentCourses.get(x))
                }

            } else {
                // remove all features
                currentCourses.size().times {
                    deleteCourse(currentCourses.remove(0))
                }
            }
        }
    }

}
