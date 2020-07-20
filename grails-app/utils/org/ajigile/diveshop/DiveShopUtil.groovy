package org.ajigile.diveshop

import grails.web.api.ServletAttributes
import groovy.util.logging.Slf4j
import org.apache.commons.logging.Log

import javax.servlet.http.HttpServletRequest
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Slf4j
class DiveShopUtil {

    DiveShopService diveShopService
    def dayLabelMap = [:]
    def languages = []

    //DiveShopCommand diveShopCommand
    //Log log


    Map getDayLabelMap(){
        if(dayLabelMap || dayLabelMap.isEmpty()){
            populateReferenceData()
        }
        dayLabelMap
    }

    List getLanguages(){
        if(languages || languages.isEmpty()){
            populateReferenceData()
        }
        languages
    }

    DiveShop mapDiveShopBasicData(DiveShopCommand diveShopCommand){
        System.out.println("diveShopCommand.id: " + diveShopCommand.id)
        def diveShop = diveShopService.get(diveShopCommand.id == null ? 0: diveShopCommand.id)

        def map = [:]
        diveShopCommand.properties.eachWithIndex { key, val, index ->
            if(!key.startsWith('day') && !key.startsWith('image') && !key.equals('id')){
                if(diveShop){
                    log.debug("updating diveShop attributes")
                    diveShop.getProperties().put(key, val)
                } else {
                    log.debug("preparing attributes map for new diveShop")
                    map.put(key, val)
                }
            }
        }

        if(!map.isEmpty()){
            log.debug("create new diveShop")
            diveShop = new DiveShop(map)
        }

        diveShop
    }





    def updateImage(HttpServletRequest request, String imageUploadPath, DiveShop diveShop){
        def file1=request.getFile('image1')
        def file2=request.getFile('image2')

        try{
            if(file1 && !file1.empty){
                file1.transferTo(new File("${imageUploadPath}/${diveShop.id}_${file1.name}"))
            }
            if(file2 && !file2.empty){
                file2.transferTo(new File("${imageUploadPath}/${diveShop.id}_${file2.name}"))
            }
        }
        catch(Exception e){
            log.error("update image fail updateImage",e)
        }
    }

    def deleteImages(Long id, String imageUploadPath){
        Files.deleteIfExists(Paths.get("${imageUploadPath}/${id}_image1"))
        Files.deleteIfExists(Paths.get("${imageUploadPath}/${id}_image2"))
    }



    def populateReferenceData(){
        log.debug("populateReferenceData...")
        if(languages || languages.isEmpty()){
            languages.add(new Language(lang: 'Bahasa', langIso: 'id'))
            languages.add(new Language(lang: 'English', langIso: 'en'))
            languages.add(new Language(lang: 'French', langIso: 'fr'))
            languages.add(new Language(lang: 'Spanish', langIso: 'es'))
        }
        if(dayLabelMap || dayLabelMap.isEmpty()){
            dayLabelMap.put(0, 'Monday')
            dayLabelMap.put(1, 'Tuesday')
            dayLabelMap.put(2, 'Wednesday')
            dayLabelMap.put(3, 'Thursday')
            dayLabelMap.put(4, 'Friday')
            dayLabelMap.put(5, 'Saturday')
            dayLabelMap.put(6, 'Sunday')

        }
    }
}
